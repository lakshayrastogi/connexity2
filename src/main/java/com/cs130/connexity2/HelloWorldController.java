package com.cs130.connexity2;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @RequestMapping("/")
    public String index() {
        return "redirect:/hello";
    }
    
    @RequestMapping("/hello")
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
    	return "hello";
    }
 
}