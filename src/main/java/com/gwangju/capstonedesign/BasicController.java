package com.gwangju.capstonedesign;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BasicController {
    private final ItemRepository itemRepository;
    ApiExplorer apiExplorer;
    @GetMapping("/apicall")
    String apicall(Model model) throws IOException, ParserConfigurationException, SAXException {
        LocalDate today = LocalDate.now();
        String  enddate = today.format(DateTimeFormatter.ofPattern("YYYYMMdd"));
        String startdate = today.minusDays(3).format(DateTimeFormatter.ofPattern("YYYYMMdd"));
        List<Map<String, Object>> resultitem =  apiExplorer.apicall(startdate,enddate);
        for (Map<String, Object> strMap : resultitem) {
            List<Item> result = itemRepository.findBytmEqk(String.valueOf(strMap.get("tmEqk")));
            if(result.isEmpty()) {
                Item item = new Item();
                item.img = String.valueOf(strMap.get("img"));
                item.loc = String.valueOf(strMap.get("loc"));
                item.tmEqk = String.valueOf(strMap.get("tmEqk"));
                itemRepository.save(item);

            }else {
                continue;
            }
        }
        return "";
    }

    @GetMapping("/")
    String home(Model model) throws IOException, ParserConfigurationException, SAXException {

        List<Item> result = itemRepository.findAll();

        String img = result.get(0).img;
        String loc = result.get(0).loc;
        String tmEqk = result.get(0).tmEqk;
        model.addAttribute("img", img);
        model.addAttribute("loc", loc);
        model.addAttribute("tmEqk", tmEqk);
        return "index.html";
    }

}
