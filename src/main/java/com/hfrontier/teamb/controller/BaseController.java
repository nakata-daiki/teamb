package com.hfrontier.teamb.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.hfrontier.teamb.repository.Users;
import com.hfrontier.teamb.ui.CommonModel;



abstract class BaseController {
	@Autowired
	private HttpSession session;
	protected void base(Object model ,Class<?> c) {
		// セッションから値を取得
		Users users = (Users) session.getAttribute(SessionKeyConstant.LOGIN_MEMBER_DATA);

		// 親クラスに値を詰め直す
		((CommonModel) model)
		((CommonModel) model).setUserfName(users.getfName());
		((CommonModel) model).setUserlName(users.getlName());
	}

}
