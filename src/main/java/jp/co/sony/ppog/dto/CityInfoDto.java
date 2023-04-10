package jp.co.sony.ppog.dto;

import jp.co.sony.ppog.entity.CityView;

public class CityInfoDto extends CityView {

	private static final long serialVersionUID = 9053927948255512241L;

	private String language;

	public CityInfoDto(final String language) {
		super();
		this.language = language;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(final String language) {
		this.language = language;
	}

}
