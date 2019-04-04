package com.hfrontier.teamb.service;


import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hfrontier.teamb.mapper.MembersMapper;
import com.hfrontier.teamb.repository.Members;


@Service
@Scope("prototype")
@Configuration
@ConfigurationProperties(prefix = "login")
public class LoginService {


	@Autowired
	private MembersMapper membersMapper;

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
	public String login(String userId, String inputPass) {
		// IDが空かどうか
		if (!StringUtils.isEmpty(userId)) {
			return "IDがない";
		}

		// 登録情報取得
		Members members = membersMapper.selectByPrimaryKey(userId);

		// 取得できたか
		if (Objects.isNull(members)) {
			return "登録されてない";
		}

		// パスワードが一致するか
		if (!inputPass.equals(members.getPassword())) {
			return "パスワードが違う";
		}

		// セッションに会員情報を保存

		return null;

	}

	/**
	 * 登録処理
	 */
	public boolean regist(String userId, String password) {

		// 入力されたIDがDBにあるか
		Members resultMember = membersMapper.selectByPrimaryKey(userId);
		// IDがDBにないならDBにIDとパスワードをインサート
		if (Objects.isNull(resultMember)) {
			Members member = new Members();
			member.setId(userId);
			member.setPassword(password);
			membersMapper.insert(member);
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

		return null;




	}
}
