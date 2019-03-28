package com.hfrontier.teamb.common.validation;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;





public final class Validator {
	private Validator() {

	}

	private static final Pattern HALF_WIDTH_PATTERN = Pattern
			.compile("^[ -~｡-ﾟ]+$");

	private static final Pattern HALF_ALPHABET_NUMERIC_PATTERN = Pattern
			.compile("^[0-9a-zA-Z]+$");

	private static final Pattern HALF_ALPHABET_NUMBER_PATTERN = Pattern
			.compile("^[0-9]+$");

	private static final Pattern PASSWORD_PATTERN = Pattern.compile("^\\w+$");

	/** 引数の文字列が半角英数字のみで構成されているかを検証する */
	public static boolean isHalfWidth(String s) {
		// nullと空文字は判定しない(trueで返す)
		if (StringUtils.isEmpty(s)) {
			return true;
		}
		return HALF_WIDTH_PATTERN.matcher(s).matches();
	}

	/** 引数の文字列がパスワードで使用可能な文字で構成されているかを検証 */
	public static boolean isPassword(String s) {
		// nullと空文字は判定しない(trueで返す)
		if (StringUtils.isEmpty(s)) {
			return true;
		}
		return PASSWORD_PATTERN.matcher(s).matches();
	}

	/**
	 * 全角文字チェック
	 *
	 * @param s
	 */
	public static boolean judgeFullWidthCharacter(String comment) {

		char[] chars = comment.toCharArray();
		// nullと空文字は判定しない(trueで返す)
		if (StringUtils.isEmpty(comment)) {
			return true;
		}
		for (int i = 0; i < chars.length; i++) {
			if (String.valueOf(chars[i]).getBytes().length < 2) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 環境依存文字チェック
	 *
	 * @param comment
	 * @return
	 * @throws IOException
	 * @throws FontFormatException
	 */
	public static boolean checkPlatformDependentCharacters(String comment)
			throws FontFormatException, IOException {
		// nullと空文字は判定しない(trueで返す)
		if (StringUtils.isEmpty(comment)) {
			return true;
		}
		List<Font> fonts = new ArrayList<Font>();
		fonts.add(new Font("ＭＳ 明朝", Font.PLAIN, 12));
		fonts.add(new Font("メイリオ", Font.PLAIN, 12));
		// ファイル問題
		fonts.add(Font.createFont(Font.TRUETYPE_FONT, new File("ipaexg.ttf")));

		String str = "𠮷野家";

		char[] arrayChar = str.toCharArray(); // charに変換
		int len = arrayChar.length;

		for (int i = 0, codePoint; i < len; i += Character.charCount(codePoint)) {
			codePoint = Character.codePointAt(arrayChar, i);
			boolean canDisp = true;
			for (Font font : fonts) {
				if (!font.canDisplay(codePoint)) {
					canDisp = false;
					break;
				}
			}
			char[] chs = Character.toChars(codePoint);
			return true;
		}
		return true;
	}
}
