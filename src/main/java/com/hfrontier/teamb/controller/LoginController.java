package com.hfrontier.teamb.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hfrontier.teamb.service.LoginService;
import com.hfrontier.teamb.ui.LoginModel;

@Controller
public class LoginController {

	public final String LOGIN_USER_ID = "LOGIN_USER_ID";
	public final String LOGIN_PASSWORD = "LOGIN_PASSWORD";
	public final String ERROR_MESSAGE = "ERROR_MESSAGE";
	public final String INPUT_USER_ID = "INPUT_USER_ID";
	public final String INPUT_PASSWORD = "INPUT_PASSWORD";

	@Autowired
	private ApplicationContext context;

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = {"Regist/Login"}, method = RequestMethod.GET)
	/**
	 * ログイン画面
	 *
	 * @param loginModel
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView login(@ModelAttribute("LoginModel") LoginModel loginModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {

		// ログイン状態チェック
		HttpSession session = request.getSession();
		if (!Objects.isNull(session.getAttribute(LOGIN_USER_ID)))  {
			model.setViewName("/to-ko-itiran");

		}
		// エラーメッセージがあるならモデルにセット
		if (!Objects.isNull(session.getAttribute(ERROR_MESSAGE))) {
			loginModel.setMessage(ERROR_MESSAGE);
			session.removeAttribute(ERROR_MESSAGE);
		}
		// セッションにログイン情報があるならモデルにセット
		if (!Objects.isNull(session.getAttribute(INPUT_USER_ID))) {
			loginModel.setUserId(INPUT_USER_ID);
		}
		if (!Objects.isNull(session.getAttribute(INPUT_PASSWORD))) {
			loginModel.setPassword(INPUT_PASSWORD);
		}
		// 画面表示処理
		model.addObject(loginModel);
		return model;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = {"Regist/Login"}, method = RequestMethod.POST)
	/**
	 * ログインボタン押下
	 *
	 * @param loginModel
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView postLogin(@ModelAttribute("LoginModel") LoginModel loginModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {

		LoginService loginService = context.getBean(LoginService.class);

		// 入力内容のチェック（ID、パスワード）



		// ログイン処理
		loginService.login(loginModel.getUserId(), loginModel.getPassword());

		// 画面遷移処理


		return model;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@RequestMapping(value = {"Regist/AddUser"}, method = RequestMethod.POST)
	/**
	 * レジストボタン押下
	 *
	 * @param loginModel
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView postRegist(@ModelAttribute("LoginModel") LoginModel loginModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {

		LoginService loginService = context.getBean(LoginService.class);
		// 入力内容チェック（ID、パスワード）


		// 登録処理
		loginService.regist();

		// 画面遷移処理（リダイレクト）

		return model;
	}
}
