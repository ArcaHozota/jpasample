package jp.co.sony.ppog.entity;

import java.io.Serializable;

/**
 * 言語テーブル複数プライマリーキーの永続化するクラス
 *
 * @author Administrator
 */
public class LanguageId implements Serializable {

	private static final long serialVersionUID = 4470395347286329942L;

	/**
	 * This field corresponds to the database column COUNTRY_CODE
	 */
	private String countryCode;

	/**
	 * This field corresponds to the database column LANGUAGE
	 */
	private String language;

	/**
	 * コンストラクタ
	 */
	protected LanguageId() {
		super();
	}

	/**
	 * getter for countryCode
	 *
	 * @return countryCode
	 */
	protected String getCountryCode() {
		return this.countryCode;
	}

	/**
	 * setter of countryCode
	 *
	 * @param countryCode セットする countryCode
	 */
	protected void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * getter for language
	 *
	 * @return language
	 */
	protected String getLanguage() {
		return this.language;
	}

	/**
	 * setter of language
	 *
	 * @param language セットする language
	 */
	protected void setLanguage(final String language) {
		this.language = language;
	}
}
