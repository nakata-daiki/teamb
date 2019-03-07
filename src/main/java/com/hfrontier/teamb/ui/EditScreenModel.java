package com.hfrontier.teamb.ui;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EditScreenModel {

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
    @Size
	private String comment;
	/**
	 * エラー文
	 * ２００文字以上・環境文字
	 */
	@NotEmpty(message = "必須入力です")
    @Size
	private String error;
	
}
