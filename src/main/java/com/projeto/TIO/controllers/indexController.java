package com.projeto.TIO.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {
    
	@RequestMapping("/home")
	public String index() {
		return "home";
		
	}
}
