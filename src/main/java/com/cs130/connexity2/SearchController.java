package com.cs130.connexity2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
	
	@RequestMapping("/")
    public String index() {
        return "redirect:/main";
    }
    
    @RequestMapping(value="/main", method=RequestMethod.GET)
    public String main(Model model){
    	//model.addAttribute("productQuery", new ProductQuery());
    	return "main";
    } 
    
    @RequestMapping(value="/searchResults",method=RequestMethod.GET)
    public String searchResults(@RequestParam(value="keyword",required=true,defaultValue="") String keyword, Model model){
    	System.out.println(keyword);
    	return "searchResults";
    }
}

/*
 * Another way to get the user information that is placed directly into the object
 * 
@RequestMapping(value="/main", method=RequestMethod.GET)
public String main(Model model){
	model.addAttribute("productQuery", new ProductQuery());
	return "main";
} 
@RequestMapping(value="/searchResults",method=RequestMethod.GET)
public String searchResults(@ModelAttribute ProductQuery query, Model model){
	System.out.println(query.getKeyword());
	return "searchResults";
}
*/
