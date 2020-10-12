package com.dnd.rest;

import com.dnd.domain.DataModel;
import com.dnd.json.impl.MapperImpl;
import com.dnd.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HtmlResource {
    private static final String DATA_ID = "data";
    private static final String DEFAULT_REDIRECT = "redirect:/";

    private final MapperImpl mapperImpl;
    private final CharacterService characterService;

    @GetMapping("/")
    public String main(@CookieValue(value = "data", required = false) Cookie data,
                       Model model,
                       HttpServletResponse response) {
        DataModel dataModel = initDataModel(data);
        setResult(model, response, dataModel);
        return "main";
    }

    @GetMapping("/clear-all")
    public String clearAll(HttpServletResponse response) {
        response.addCookie(new Cookie(DATA_ID, null));
        return DEFAULT_REDIRECT;
    }

    @PostMapping("/new-char")
    public String createNewChar(@CookieValue(value = "data", required = false) Cookie data,
                                @RequestParam String name,
                                @RequestParam int hp,
                                Model model,
                                HttpServletResponse response) {
        DataModel dataModel = initDataModel(data);
        characterService.addNewChar(name, hp, dataModel);
        setResult(model, response, dataModel);
        return DEFAULT_REDIRECT;
    }

    @GetMapping("/delete-char/{id}")
    public String deleteCharById(@CookieValue(value = "data", required = false) Cookie data,
                                 @PathVariable UUID id,
                                 Model model,
                                 HttpServletResponse response) {
        DataModel dataModel = initDataModel(data);
        characterService.deleteCharById(id, dataModel);
        setResult(model, response, dataModel);
        return DEFAULT_REDIRECT;
    }

    @PostMapping("/deal-damage/{id}")
    public String dealDamage(@CookieValue(value = "data", required = false) Cookie data,
                             @PathVariable UUID id,
                             @RequestParam int damage,
                             Model model,
                             HttpServletResponse response) {
        DataModel dataModel = initDataModel(data);
        characterService.dealDamage(id, damage, dataModel);
        setResult(model, response, dataModel);
        return DEFAULT_REDIRECT;
    }

    @PostMapping("/create-effect/{id}")
    public String createNewEffect(@CookieValue(value = "data", required = false) Cookie data,
                                  @PathVariable UUID id,
                                  @RequestParam String name,
                                  @RequestParam String description,
                                  @RequestParam int duration,
                                  @RequestParam int hpPerTurn,
                                  Model model,
                                  HttpServletResponse response) {
        DataModel dataModel = initDataModel(data);
        characterService.addNewEffect(id, name, duration, description, hpPerTurn, dataModel);
        setResult(model, response, dataModel);
        return DEFAULT_REDIRECT;
    }

    private void setResult(Model model, HttpServletResponse response, DataModel dataModel) {
        Cookie cookie = new Cookie(DATA_ID, mapperImpl.toJson(dataModel));
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        model.addAttribute(DATA_ID, dataModel);
    }

    private DataModel initDataModel(Cookie data) {
        if (data == null || data.getValue().isEmpty()) {
            DataModel dataModel = new DataModel();
            dataModel.setCharacters(new ArrayList<>());
            return dataModel;
        } else {
            return mapperImpl.toObject(data.getValue());
        }
    }
}
