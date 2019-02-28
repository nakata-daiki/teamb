package com.hfrontier.teamb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hfrontier.teamb.ui.LoginModel;

@Controller
public class LoginController {

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = {"Regist/Login"}, method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute("LoginModel") LoginModel loginModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {


		return model;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = {"Regist/Login"}, method = RequestMethod.POST)
	public ModelAndView postLogin(@ModelAttribute("LoginModel") LoginModel loginModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {

		return model;
	}

}
