package com.hfrontier.teamb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hfrontier.teamb.common.constant.Constant;
import com.hfrontier.teamb.repository.Comments;
import com.hfrontier.teamb.ui.CommentListModel;
import com.hfrontier.teamb.ui.CommentListModel.CommentsData;

@Controller
public class CommentListController {

	@Autowired
	private AbstractApplicationContext context;


	@RequestMapping(value = { Constant.COMMENTLIST }, method = RequestMethod.GET)
	public @ResponseBody ModelAndView getCommentList(
			ModelAndView model,
			HttpServletRequest request,
			HttpServletResponse response) {

		CommentListModel commentListModel = new CommentListModel();

		//すべてのコメント？
		List<Comments> al = new ArrayList<>();

		//
		List<CommentsData> list = new ArrayList<>();

		//すべてのコメントを一つ一つ確認
		for (Comments coment : al) {
			//ユーザー本人のコメントかどうかチェックするメソッド？？？？
			CommentsData cd = new CommentsData();
			cd.setId(coment.getId());
			cd.setComment(coment.getComment());
			cd.setMyComment(coment.getUserId());
		}

		model.addObject("comentlist", list);

		model.setViewName("HTML/board");
		return model;
	}


}
