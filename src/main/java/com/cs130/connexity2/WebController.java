package com.cs130.connexity2;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController 
{
	@RequestMapping("/")
    public String index() {
        return "redirect:/main";
    }
	
    @RequestMapping("/main")
    public String main(Model model)
    {
    	return "main";
    }
}
