package com.hfrontier.teamb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.hfrontier.teamb.mapper.UsersMapper;
import com.hfrontier.teamb.repository.Users;

public class LoginService {

	@Autowired
	private UsersMapper userMapper;

	private final String ERROR_NOT_USER_ID = "ユーザーIDが入力されていません。";
	private final String ERROR_NOT_PASSWORD = "パスワードが入力されていません。";
	private final String ERROR_ORVER_USER_ID = "ユーザーIDの文字数が。";
	private final String ERROR_ORVER_PASSWORD = "ユーザーIDが入力されていません。";

	private final int MAX_USER_ID = 50;
	private final int MAX_PASSWORD = 12;
	private final int MIN_USER_ID = 3;
	private final int MIN_PASSWORD = 3;



	/**
	 * ログイン処理
	 */
	public void login(String userId, String password) {
		// DB接続s


		// 入力されたIDがDBにあるか
		userMapper.selectByUserId(userId);



		// IDに対応するパスワードが同じか

		// セッションに会員情報を保存

		//
	}

	/**
	 * 登録処理
	 */
	public boolean regist(String userId, String password) {

		// 入力されたIDがDBにあるか
		Users users = userMapper.selectByUserId(userId);
		// IDがDBにないならDBにIDとパスワードをインサート
		if (StringUtils.isEmpty(users.getUsersId())) {
			userMapper.insert(users);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * バリデーションチェック処理
	 */
	public String validationCheck(String userId, String password) {
		// IDが空
		if (StringUtils.isEmpty(userId)) return ERROR_NOT_USER_ID;
		// パスワードが空
		if (StringUtils.isEmpty(password)) return ERROR_NOT_PASSWORD;
		// ID文字数超過
		if (MAX_USER_ID < userId.length() || MIN_USER_ID < userId.length()) return ERROR_ORVER_USER_ID;
		// パスワード文字数超過
		if (MAX_PASSWORD < password.length() || MIN_PASSWORD < password.length()) return ERROR_ORVER_PASSWORD;

		return StringUtils.EMPTY;




	}
}
