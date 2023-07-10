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
	private String name;

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
	 * getter for name
	 *
	 * @return name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * setter of name
	 *
	 * @param name セットする name
	 */
	public void setName(final String name) {
		this.name = name;
	}
}
