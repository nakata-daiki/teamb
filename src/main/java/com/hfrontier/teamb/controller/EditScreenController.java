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
import com.hfrontier.teamb.repository.Comments;
import com.hfrontier.teamb.service.CommentsService;
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
		int id = Integer.parseInt(editScreenModel.getPostedSerialNumber()) ;
		CommentsService commentservice = context
				.getBean(CommentsService.class);

		Comments c = commentservice.getComment(id);

		model.setViewName("HTML/screen");
		model.addObject("model",c);
		return model;
	}

	@RequestMapping(value = { Constant.SCREEN }, method = RequestMethod.POST)
	public @ResponseBody ModelAndView PostScreen(
			@ModelAttribute("EditScreenModel") @Valid EditScreenModel editScreenModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(editScreenModel.getPostedSerialNumber()) ;
		CommentsService commentservice = context
				.getBean(CommentsService.class);
		commentservice.updateComments(id, editScreenModel.getComment());
		model.setViewName("redirect:/commentList");
		return model;

		}

	}
