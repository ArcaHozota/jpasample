package jp.co.sony.ppog.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * 言語テーブルWORLD_LANGUAGEのエンティティ
 *
 * @author Administrator
 */
@Entity
@Table(name = "WORLD_LANGUAGE")
@Proxy(lazy = false)
@IdClass(LanguageId.class)
@NamedQuery(name = "Language.findLanguageByCty", query = "select nl from Language as nl where nl.logicDeleteFlg = 'visible' "
		+ "and nl.countryCode =:countryCode order by nl.percentage desc")
public class Language implements Serializable {

	private static final long serialVersionUID = -8085659909634431823L;

	/**
	 * This field corresponds to the database column COUNTRY_CODE
	 */
	@Id
	private String countryCode;

	/**
	 * This field corresponds to the database column LANGUAGE
	 */
	@Id
	private String language;

	/**
	 * This field corresponds to the database column IS_OFFICIAL
	 */
	@Column(nullable = false)
	private String isOfficial;

	/**
	 * This field corresponds to the database column PERCENTAGE
	 */
	@Column(nullable = false)
	private BigDecimal percentage;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	@Column(nullable = false)
	private String logicDeleteFlg;

	/**
	 * コンストラクタ
	 */
	public Language() {
		super();
	}

	/**
	 * getter for countryCode
	 *
	 * @return countryCode
	 */
	public String getCountryCode() {
		return this.countryCode;
	}

	/**
	 * setter of countryCode
	 *
	 * @param countryCode セットする countryCode
	 */
	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
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

	/**
	 * getter for isOfficial
	 *
	 * @return isOfficial
	 */
	public String getIsOfficial() {
		return this.isOfficial;
	}

	/**
	 * setter of isOfficial
	 *
	 * @param isOfficial セットする isOfficial
	 */
	public void setIsOfficial(final String isOfficial) {
		this.isOfficial = isOfficial;
	}

	/**
	 * getter for percentage
	 *
	 * @return percentage
	 */
	public BigDecimal getPercentage() {
		return this.percentage;
	}

	/**
	 * setter of percentage
	 *
	 * @param percentage セットする percentage
	 */
	public void setPercentage(final BigDecimal percentage) {
		this.percentage = percentage;
	}

	/**
	 * getter for logicDeleteFlg
	 *
	 * @return logicDeleteFlg
	 */
	public String getLogicDeleteFlg() {
		return this.logicDeleteFlg;
	}

	/**
	 * setter of logicDeleteFlg
	 *
	 * @param logicDeleteFlg セットする logicDeleteFlg
	 */
	public void setLogicDeleteFlg(final String logicDeleteFlg) {
		this.logicDeleteFlg = logicDeleteFlg;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Language [countryCode=" + this.countryCode + ", language=" + this.language + ", isOfficial="
				+ this.isOfficial + ", percentage=" + this.percentage + ", logicDeleteFlg=" + this.logicDeleteFlg + "]";
	}
}
