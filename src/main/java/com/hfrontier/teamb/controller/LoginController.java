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

		// エラーメッセージがあるならモデルにセット

		// セッションにログイン情報があるならモデルにセット

		// 画面表示処理


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

		// 入力内容のチェック（ID、パスワード）

		// ログイン処理

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

		// 入力内容チェック（ID、パスワード）

		// 登録処理

		// 画面遷移処理（リダイレクト）

		return model;
	}
}
