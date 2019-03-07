package com.hfrontier.teamb.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hfrontier.teamb.mapper.UsersMapper;

public class LoginService {

	@Autowired
	private UsersMapper userMapper;

	/**
	 * ログイン処理
	 */
	private void login(String userId, String password) {
		// DB接続

		// 入力されたIDがDBにあるか

		// IDに対応するパスワードが同じか

		// セッションに会員情報を保存

		//
	}

	/**
	 * 登録処理
	 */
	private void b(String userId, String password) {
		// DB接続

		// 入力されたIDがDBにあるか

		// IDがDBにないならDBにIDとパスワードをインサート

		//

	}

	/**
	 * バリデーションチェック処理
	 */
	private void validationCheck(String userId, String password) {
		// IDが空



	}
}
