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
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class BasicController {
    private final ItemRepository itemRepository;

    @GetMapping("/apicall")
    String apicall() throws IOException, ParserConfigurationException, SAXException {
        LocalDate today = LocalDate.now();
        String  enddate = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String startdate = today.minusDays(3).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        List<Map<String, Object>> resultitem =  ApiExplorer.apicall(startdate,enddate);

        for (Map<String, Object> strMap : resultitem) {
            List<Item> result = itemRepository.findBytmEqk(String.valueOf(strMap.get("tmEqk")));
            if(result.isEmpty()) {
                Item item = new Item();
                item.img = String.valueOf(strMap.get("img"));
                item.loc = String.valueOf(strMap.get("loc"));
                item.tmEqk = String.valueOf(strMap.get("tmEqk"));
                itemRepository.save(item);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/")
    String home(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        return "index.html";
    }

}
