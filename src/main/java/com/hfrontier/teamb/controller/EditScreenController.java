package com.hfrontier.teamb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hfrontier.teamb.common.constant.Constant;
import com.hfrontier.teamb.ui.EditScreenModel;



@Controller
public class EditScreenController {

	@Autowired
	private AbstractApplicationContext context;

	@RequestMapping(value = { Constant.SCREEN }, method = RequestMethod.GET)
	public @ResponseBody ModelAndView GetScreen(@ModelAttribute("EditScreenModel") @Valid  EditScreenModel editScreenModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {
		model.setViewName("HTML/screen");
		return model;
	}
	@RequestMapping(value = { Constant.SCREEN }, method = RequestMethod.POST)
	public @ResponseBody ModelAndView PostScreen(
			@ModelAttribute("EditScreenModel") @Valid EditScreenModel editScreenModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
        /**
         * バリデーションチェックを行うメソッド
         */
//		public static void doValidationCheck(){
		model.setViewName("HTML/screen");
		return model;

		}

	}
