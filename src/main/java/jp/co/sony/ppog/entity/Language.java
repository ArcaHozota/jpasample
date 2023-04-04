package jp.co.sony.ppog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity of Table WORLD_LANGUAGE
 *
 * @author Administrator
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "WORLD_LANGUAGE")
public class Language implements Serializable {

	private static final long serialVersionUID = -8085659909634431823L;

	/**
	 * This field corresponds to the database column COUNTRY_CODE
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COUNTRY_CODE")
	private String countryCode;

	/**
	 * This field corresponds to the database column LANGUAGE
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	private String percentage;

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
