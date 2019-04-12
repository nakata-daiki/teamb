package com.hfrontier.teamb.common.validation;

import org.apache.commons.lang3.StringUtils;

public final class Validator {
	private Validator() {

	}

	/**
	 * 全角文字チェック
	 * @param s
	 */
	public static boolean judgeFullWidthCharacter(String comment) {

		char[] chars = comment.toCharArray();
		if (StringUtils.isEmpty(comment)) {
			return false;
		}
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
	public static boolean judgeNullCharacter(String comment) {
		if (StringUtils.isEmpty(comment)) {
			return false;
		}
		return true;
	}

}
