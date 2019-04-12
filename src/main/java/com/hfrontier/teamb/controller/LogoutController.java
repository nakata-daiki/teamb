package com.hfrontier.teamb.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import com.hfrontier.teamb.ui.LogoutModel;
@Controller
public class LogoutController {
	@Autowired
	private AbstractApplicationContext context;

	@Autowired
	HttpSession session;


	@RequestMapping(value = { Constant.LOGOUT }, method = RequestMethod.GET)
	public @ResponseBody ModelAndView GetLogin(@ModelAttribute("LogoutModel") @Valid LogoutModel logoutModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {
		//セッションの破棄
		HttpSession session = request.getSession(true);
		session.invalidate();
		//ログイン画面に遷移
		model.setViewName("redirect:/login");
		return model;
	}

}
