package com.dnd.rest;

import com.dnd.domain.Character;
import com.dnd.domain.DataModel;
import com.dnd.json.impl.MapperImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class HtmlResource {
    private static final String DATA_ID = "data";

    private final MapperImpl mapperImpl;

    @GetMapping("/")
    public String main(@CookieValue(value = "data", required = false) Cookie data,
                       Model model,
                       HttpServletResponse response) {
        Character test = new Character();
        test.setName("Kirieshkins");
        test.setHp(100);

        DataModel dataModel = createDataModel(data);
        if (CollectionUtils.isEmpty(dataModel.getCharacters())) {
            dataModel.getCharacters().add(test);
        }

        String resultData = mapperImpl.toJson(dataModel);
        response.addCookie(new Cookie(DATA_ID, resultData));
        model.addAttribute(DATA_ID, dataModel);
        return "main";
    }

    @GetMapping("/add-character")
    public String addCharacter(@CookieValue(value = "data", required = false) Cookie data,
                               Model model,
                               HttpServletResponse response) {
        Character character = new Character();
        character.setName(UUID.randomUUID().toString());
        character.setHp(new Random().nextInt());

        DataModel dataModel = createDataModel(data);
        dataModel.getCharacters().add(character);

        response.addCookie(new Cookie(DATA_ID, mapperImpl.toJson(dataModel)));
        model.addAttribute(DATA_ID, dataModel);
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
        Character character = new Character();
        character.setName(name);
        character.setHp(hp);

        DataModel dataModel = createDataModel(data);
        dataModel.getCharacters().add(character);

        response.addCookie(new Cookie(DATA_ID, mapperImpl.toJson(dataModel)));
        model.addAttribute(DATA_ID, dataModel);
        return "main";
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
