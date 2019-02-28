package com.hfrontier.teamb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hfrontier.teamb.ui.NewPostModel;

import jp.co.hfrontier.dakoku.common.constant.Constant;
import jp.co.hfrontier.dakoku.ui.LoginModel;

public class NewPostController {

	@Autowired
	private AbstractApplicationContext context;

	@RequestMapping(value = { Constant.NEWPOST }, method = RequestMethod.GET)
	public @ResponseBody ModelAndView GetLogin(@ModelAttribute("NewPostModel") @Valid  NewPostModel newPostModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {
		model.setViewName("HTML/newpost");
		return model;
	}
	@RequestMapping(value = { Constant.NEWPOST }, method = RequestMethod.POST)
	public @ResponseBody ModelAndView PostLogin(
			@ModelAttribute("NewPostModel") @Valid NewPostModel newPostModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
}
