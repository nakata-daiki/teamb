package com.hfrontier.teamb.ui;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class NewPostModel {
	/**
	 * ユーザーID
	 */
	@NotEmpty(message = "必須入力です")
	private String userID;
	/**
	 * コメント １〜２００文字以内
	 */
	@NotEmpty(message = "必須入力です")
	@Size(min = 1, max = 200, message = "200文字以内で入力してください")
	private String comment;



}
