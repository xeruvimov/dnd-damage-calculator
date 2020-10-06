package com.dnd.rest;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@AllArgsConstructor
public class HtmlResource {
    @GetMapping("/")
    public String main(Map<String, Object> model) {
        return "main";
    }
}
