package jp.co.sony.ppog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

/**
 * 都市テーブルWORLD_CITYのエンティティ
 *
 * @author Administrator
 */
@Entity
@Table(name = "WORLD_CITY")
@Proxy(lazy = false)
@NamedQuery(name = "City.removeById", query = "update City as c set c.logicDeleteFlg = 'removed' where c.id =:id")
public class City implements Serializable {

	private static final long serialVersionUID = 1815689293387304425L;

	/**
	 * This field corresponds to the database column ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * This field corresponds to the database column NAME
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * This field corresponds to the database column COUNTRY_CODE
	 */
	@Column(name = "COUNTRY_CODE", nullable = false)
	private String countryCode;

	/**
	 * This field corresponds to the database column DISTRICT
	 */
	@Column(nullable = false)
	private String district;

	/**
	 * This field corresponds to the database column POPULATION
	 */
	@Column(nullable = false)
	private Long population;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	@Column(name = "LOGIC_DELETE_FLG", nullable = false)
	private String logicDeleteFlg;

	/**
	 * コンストラクタ
	 */
	public City() {
		super();
	}

	/**
	 * getter for id
	 *
	 * @return id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * setter of id
	 *
	 * @param id セットする id
	 */
	public void setId(final Long id) {
		this.id = id;
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
	 * getter for district
	 *
	 * @return district
	 */
	public String getDistrict() {
		return this.district;
	}

	/**
	 * setter of district
	 *
	 * @param district セットする district
	 */
	public void setDistrict(final String district) {
		this.district = district;
	}

	/**
	 * getter for population
	 *
	 * @return population
	 */
	public Long getPopulation() {
		return this.population;
	}

	/**
	 * setter of population
	 *
	 * @param population セットする population
	 */
	public void setPopulation(final Long population) {
		this.population = population;
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
		return "City [id=" + this.id + ", name=" + this.name + ", countryCode=" + this.countryCode + ", district="
				+ this.district + ", population=" + this.population + ", logicDeleteFlg=" + this.logicDeleteFlg + "]";
	}
}