package com.gwangju.capstonedesign;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Controller
public class BasicController {

    ApiExplorer apiExplorer = new ApiExplorer();

    @GetMapping("/")
    String homepage(Model model) throws IOException {
        HashMap<String, String> result = apiExplorer.apicall();
        model.addAttribute("loc", result.get("loc"));
        model.addAttribute("img", result.get("img"));
        model.addAttribute("tmEqk", result.get("tmEqk"));
        return "index.html";
    }

}
