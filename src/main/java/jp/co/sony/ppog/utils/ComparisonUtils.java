package jp.co.sony.ppog.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 判断ツールクラス
 *
 * @author Administrator
 * @date 2022-11-07
 */
public class ComparisonUtils extends StringUtils {

	/**
	 * イコール
	 * 
	 * @param ob1 値
	 * @param ob2 値
	 * @return 結果
	 */
	public static boolean isEqual(final Object ob1, final Object ob2) {
		boolean isEqual = false;
		if (ob1 != null && ob2 != null && ob1.getClass().equals(ob2.getClass())) {
			final String strOb1 = ob1.toString();
			final String strOb2 = ob2.toString();
			isEqual = strOb1.equals(strOb2);
		} else if (ob1 == null && ob2 == null) {
			isEqual = true;
		}
		return isEqual;
	}

	/**
	 * 不イコール
	 * 
	 * @param ob1 値
	 * @param ob2 値
	 * @return 結果
	 */
	public static boolean isNotEqual(final Object ob1, final Object ob2) {
		return !isEqual(ob1, ob2);
	}
}
