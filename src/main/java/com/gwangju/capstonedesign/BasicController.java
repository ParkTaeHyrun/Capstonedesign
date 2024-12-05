package com.gwangju.capstonedesign;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {

    @GetMapping("/")
    String homepage(){
        return "index.html";
    }
}
