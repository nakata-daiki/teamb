package com.hfrontier.teamb.controller;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hfrontier.teamb.common.constant.Constant;
import com.hfrontier.teamb.common.constant.SessionKeyConstant;
import com.hfrontier.teamb.common.validation.Validator;
import com.hfrontier.teamb.service.CommentsService;
import com.hfrontier.teamb.ui.NewPostModel;

@Controller
public class NewPostController {

	@Autowired
	private AbstractApplicationContext context;

	/**
	 * 新規投稿画面(初期)
	 *
	 * @param newPostModel
	 * @param result
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { Constant.NEWPOST }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public @ResponseBody ModelAndView GetLogin(
			@ModelAttribute("NewPostModel") @Valid NewPostModel newPostModel,
			BindingResult result, ModelAndView model,
			HttpServletRequest request, HttpServletResponse response) {

		// セッションからusrIDを取得し、モデルにつめる
		HttpSession session = request.getSession(true);
		String userID = (String) session
				.getAttribute(SessionKeyConstant.LOGIN_MEMBER_DATA);
		if (userID != null) {
			newPostModel.setUserID(userID);
			model.setViewName("HTML/newpost");
		} else {
			// セッションからuserIDを取得できない場合はログイン画面に遷移
			//model.setViewName("HTML/login");
			model.setViewName("HTML/newpost");
		}

		model.addObject("newPostModel", newPostModel);
		return model;
	}
	@RequestMapping(value = { Constant.NEWPOSTREGIST }, method = RequestMethod.POST)
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
			model.setViewName("redirect:/commentList");

		} else {
			// エラーがある場合は新規投稿画面を再描画する。
			model.addObject("errorMap", errorMap);
			model.addObject("newPostModel", newPostModel);
			model.setViewName("/newPostRegist");

		}
		return model;

		}

	/**
	 * バリデーションチェック
	 *
	 * @param result
	 * @param newPostModel
	 * @return
	 * @throws IOException
	 * @throws FontFormatException
	 */
	private Map<String, String> doValidationCheck(BindingResult result,
			@Valid NewPostModel newPostModel) throws FontFormatException,
			IOException {
		Map<String, String> errorMap = new LinkedHashMap<String, String>();

		// コメントのバリデーションチェック
		FieldError commentError = result.getFieldError("comment");

		if (commentError != null) {
			errorMap.put("comment", commentError.getDefaultMessage());
		}
		String comment = newPostModel.getComment();
		if (!Validator.judgeFullWidthCharacter(comment)) {
			errorMap.put("comment", "コメントを全角で入力してください");
		}
//		if (!Validator.checkPlatformDependentCharacters(comment)) {
//			errorMap.put("comment", "コメントに環境依存文字が使用されています");
//
//		}

		return errorMap;

	}

	}

