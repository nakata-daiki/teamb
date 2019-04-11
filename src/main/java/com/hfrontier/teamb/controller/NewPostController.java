package com.hfrontier.teamb.controller;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

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
import com.hfrontier.teamb.service.CommentsService;
import com.hfrontier.teamb.ui.NewPostModel;

@Controller
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
			BindingResult result, ModelAndView model,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, FontFormatException {

		// バリデーションエラー情報を取得
		Map<String, String> errorMap = new LinkedHashMap<String, String>();
		errorMap = doValidationCheck(result, newPostModel);

		// バリデーションエラーがなければコメントをDBに登録
		if (errorMap.isEmpty()) {
			// uerIDをモデルから取得
			String userID = newPostModel.getUserID();
			// commentをモデルから取得
			String comment = newPostModel.getComment();

			CommentsService commentservice = context
					.getBean(CommentsService.class);

			// userIDを元にcommentテーブルから投稿回数を取得

			int countLog = commentservice.getCountLog(userID);
			countLog = countLog+1;
			/**
			 * commentテーブルにコメントを登録 作成日はDB登録時に入るようにしたい
			 */
			commentservice.insertComment(userID, countLog, comment);

			// 投稿一覧画面に遷移
			model.setViewName("HTML/board");

		} else {
			// エラーがある場合は新規投稿画面を再描画する。
			model.addObject("errorMap", errorMap);
			model.addObject("newPostModel", newPostModel);
			model.setViewName("HTML/newpost");

		}
		return model;

		}

	}

