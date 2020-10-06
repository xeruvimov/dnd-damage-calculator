package com.dnd.rest;

import com.dnd.domain.Character;
import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class HtmlResource {
    private static String CHARACTERS_LIST_ID = "characters";

    @GetMapping("/")
    public String main(@CookieValue(value = "char", required = false) Cookie cha, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("our test cookie: " + cha.getValue());
        List<Character> characters = new ArrayList<>();

        Character test = new Character(100, null);
        test.setName("Кириешкинс");
        characters.add(test);
//        model.addAttribute(CHARACTERS_LIST_ID, characters);
        return "main";
    }

    @GetMapping("/add-character")
    public String addCharacter(Model model, HttpServletRequest request, HttpServletResponse response) {
        Character character = new Character(new Random().nextInt(), null);
        character.setName(UUID.randomUUID().toString());

        Cookie cookie = new Cookie("char", character.getName());
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
//        System.out.println(model.asMap().size());
//        ((List<Character>) model.getAttribute(CHARACTERS_LIST_ID)).add(character);
        return "main";
    }
}
