package com.hfrontier.teamb.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hfrontier.teamb.common.constant.Constant;
import com.hfrontier.teamb.service.LoginService;
import com.hfrontier.teamb.ui.LoginModel;

@Controller
@Configuration
@Scope("request")
public class LoginController {

	public final String LOGIN_USER_ID = "LOGIN_USER_ID";
	public final String LOGIN_PASSWORD = "LOGIN_PASSWORD";
	public final String ERROR_MESSAGE = "ERROR_MESSAGE";
	public final String INPUT_USER_ID = "INPUT_USER_ID";
	public final String INPUT_PASSWORD = "INPUT_PASSWORD";

	@Autowired
	private ApplicationContext context;
	@Autowired
	HttpSession session;

	@Transactional(propagation = Propagation.REQUIRED)

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
	@RequestMapping(value = { Constant.LOGIN }, method = RequestMethod.GET)
	public @ResponseBody ModelAndView login(@ModelAttribute("LoginModel") LoginModel loginModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {

		// ログイン状態チェック
		HttpSession session = request.getSession(true);
		if (!Objects.isNull(session.getAttribute(LOGIN_USER_ID))) {
			model.setViewName("redirect:/board");
			return model;
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
		model.addObject("loginModel", loginModel);
		model.setViewName("HTML/login");
		return model;
	}

	@Transactional(propagation = Propagation.REQUIRED)

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
	@RequestMapping(value = { Constant.REGISTLOGIN }, params = "login", method = RequestMethod.POST)
	public @ResponseBody ModelAndView postLogin(@ModelAttribute("LoginModel") LoginModel loginModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {

		String userId = loginModel.getUserId();
		String password = loginModel.getPassword();

		//		// 入力内容のチェック（ID、パスワード）
		//		String errorMessage = loginService.validationCheck(loginModel.getUserId(), loginModel.getPassword());
		//		if (StringUtils.isEmpty(errorMessage)) {
		//			loginModel.setMessage(errorMessage);
		//			model.addObject("LoginModel", loginModel);
		//			model.setViewName("HTML/login");
		//			return model;
		//		}
		LoginService loginService = context.getBean(LoginService.class);

		// ログイン処理
		String message = loginService.login(loginModel.getUserId(), loginModel.getPassword(), session);
		if (!StringUtils.isEmpty(message)) {

			model.setViewName("HTML/login");

		} else {
			// 画面遷移処理

			model.setViewName("redirect:/commentList");
		}
		model.addObject("loginModel", loginModel);
		return model;
	}

	@Transactional(propagation = Propagation.REQUIRED)

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
	@RequestMapping(value = { Constant.REGISTLOGIN }, params = "regist", method = RequestMethod.POST)
	public ModelAndView postRegist(@ModelAttribute("LoginModel") LoginModel loginModel,
			BindingResult result,
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response,RedirectAttributes redirect) {

		LoginService loginService = context.getBean(LoginService.class);
		// 入力内容チェック（ID、パスワード）
		String errorMessage = loginService.validationCheck(loginModel.getUserId(), loginModel.getPassword());
		if (!StringUtils.isEmpty(errorMessage)) {
			loginModel.setMessage(errorMessage);
			redirect.addAttribute("message",errorMessage);
			model.setViewName("redirect:/login");

			return model;
		}

		// 登録処理
		if (!loginService.regist(loginModel.getUserId(), loginModel.getPassword())) {
			loginModel.setMessage("既に登録されています");
			redirect.addAttribute("message","既に登録されています");
			model.setViewName("redirect:/login");

			return model;
		}

		// 画面遷移処理（リダイレクト）
		loginModel.setMessage("登録完了しました");
		redirect.addAttribute("message","登録完了しました");

		model.setViewName("redirect:/login");
		return model;
	}

}
