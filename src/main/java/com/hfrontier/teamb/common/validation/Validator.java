package com.hfrontier.teamb.common.validation;

public class Validator {
	/**
	 * 全角文字チェック
	 * @param s
	 */
	private boolean judgeFullWidthCharacter(String comment) {

		char[] chars = comment.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (String.valueOf(chars[i]).getBytes().length < 2) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 空文字チェック
	 */
	private boolean judgeNullCharacter(String comment) {
		if (comment.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * 文字数チェック
	 */
	private boolean judgeCharacterNumber(String comment) {
		int CharacterNumber = comment.length();
		if (1 <= CharacterNumber && CharacterNumber <= 200) {
			return true;
		}
		return false;
	}
}
