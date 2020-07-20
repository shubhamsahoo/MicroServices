package com.techouts.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(value = { IOException.class, InterruptedException.class, Exception.class })
	public ModelAndView handleException(Exception e) {
		e.printStackTrace();
		ModelAndView mav = new ModelAndView("error_exception");
		mav.addObject("exception", e.getMessage());
		return mav;
	}
}
