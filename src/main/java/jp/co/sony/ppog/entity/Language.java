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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 言語テーブルWORLD_LANGUAGEのエンティティ
 *
 * @author Administrator
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "WORLD_LANGUAGE")
@IdClass(LanguageId.class)
@NamedQuery(name = "Language.findLanguageByCity", query = "select al.countryCode, al.language, al.percentage, al.isOfficial " +
		"from Language as al where al.logicDeleteFlg = 'visible'")
public class Language implements Serializable {

	private static final long serialVersionUID = -8085659909634431823L;

	/**
	 * This field corresponds to the database column COUNTRY_CODE
	 */
	@Id
	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	/**
	 * This field corresponds to the database column LANGUAGE
	 */
	@Id
	private String language;

	/**
	 * This field corresponds to the database column IS_OFFICIAL
	 */
	@Column(name = "IS_OFFICIAL", nullable = false)
	private String isOfficial;

	/**
	 * This field corresponds to the database column PERCENTAGE
	 */
	@Column(nullable = false)
	private BigDecimal percentage;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	@Column(name = "LOGIC_DELETE_FLG", nullable = false)
	private String logicDeleteFlg;

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Language [countryCode=" + this.countryCode + ", language=" + this.language + ", isOfficial="
				+ this.isOfficial + ", percentage=" + this.percentage + ", logicDeleteFlg=" + this.logicDeleteFlg + "]";
	}
}
