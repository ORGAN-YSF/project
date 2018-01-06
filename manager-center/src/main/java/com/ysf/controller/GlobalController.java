package com.ysf.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GlobalController implements ErrorController {
	public String getErrorPath() {
		return "/404";
	}
	
	@RequestMapping("/error")
	public String errorHandle() {
		return getErrorPath();
	}
}
