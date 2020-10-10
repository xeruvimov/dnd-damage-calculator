package com.dnd.rest;

import com.dnd.domain.Character;
import com.dnd.domain.DataModel;
import com.dnd.json.impl.MapperImpl;
import com.dnd.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class HtmlResource {
    private static final String DATA_ID = "data";

    private final MapperImpl mapperImpl;
    private final CharacterService characterService;

    @GetMapping("/")
    public String main(@CookieValue(value = "data", required = false) Cookie data,
                       Model model,
                       HttpServletResponse response) {
        DataModel dataModel = createDataModel(data);
        setResult(model, response, dataModel);
        return "main";
    }

    @GetMapping("/clear-all")
    public String clearAll(HttpServletResponse response) {
        response.addCookie(new Cookie(DATA_ID, null));
        return "main";
    }

    @PostMapping("/new-char")
    public String createNewChar(@CookieValue(value = "data", required = false) Cookie data,
                                @RequestParam String name,
                                @RequestParam int hp,
                                Model model,
                                HttpServletResponse response) {

        DataModel dataModel = createDataModel(data);
        characterService.addNewChar(name, hp, dataModel);
        setResult(model, response, dataModel);
        return "main";
    }

    private void setResult(Model model, HttpServletResponse response, DataModel dataModel) {
        response.addCookie(new Cookie(DATA_ID, mapperImpl.toJson(dataModel)));
        model.addAttribute(DATA_ID, dataModel);
    }

    private DataModel createDataModel(Cookie data) {
        if (data == null || data.getValue().isEmpty()) {
            DataModel dataModel = new DataModel();
            dataModel.setCharacters(new ArrayList<>());
            return dataModel;
        } else {
            return mapperImpl.toObject(data.getValue());
        }
    }
}
