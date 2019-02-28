package com.hfrontier.teamb.ui;

import javax.validation.constraints.NotEmpty;

public class NewPostModel {
	/**
	 * ユーザーID
	 */
	@NotEmpty(message = "必須入力です")
	private String userID;
	/**
	 * 投稿連番
	 */
	private String postedSerialNumber;
	/**
	 * コメント
	 * １〜２００文字以内
	 */
	@NotEmpty(message = "必須入力です")
	private String comment;

}
