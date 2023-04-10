package jp.co.sony.ppog.dto;

import jp.co.sony.ppog.entity.CityView;

public class CityInfoDto extends CityView {

	private static final long serialVersionUID = 9053927948255512241L;

	private String language;

	/**
	 * コンストラクタ
	 */
	public CityInfoDto() {
		super();
	}

	/**
	 * getter for language
	 *
	 * @return language
	 */
	public String getLanguage() {
		return this.language;
	}

	/**
	 * setter of language
	 *
	 * @param language セットする language
	 */
	public void setLanguage(final String language) {
		this.language = language;
	}
}
