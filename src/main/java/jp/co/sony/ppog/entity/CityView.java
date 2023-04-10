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
 * 都市情報ビューWORLD_CITY_VIEWのエンティティ
 *
 * @author Administrator
 */
@Entity
@Table(name = "WORLD_CITY_VIEW")
@Proxy(lazy = false)
@NamedQuery(name = "CityView.findByNations", query = "select cv from CityView as cv where cv.nation =:nation")
@NamedQuery(name = "CityView.getByNations", query = "select cv from CityView as cv where cv.nation =:nation order by cv.id asc")
@NamedQuery(name = "CityView.getByNames", query = "select cv from CityView as cv where cv.name like concat('%', :name, '%') order by cv.id asc")
public class CityView implements Serializable {

	private static final long serialVersionUID = 6678964783710878220L;

	/**
	 * This field corresponds to the database column ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	/**
	 * This field corresponds to the database column NAME
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * This field corresponds to the database column CONTINENT
	 */
	@Column(nullable = false)
	private String continent;

	/**
	 * This field corresponds to the database column NATION
	 */
	@Column(nullable = false)
	private String nation;

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
	 * コンストラクタ
	 */
	public CityView() {
		super();
	}

	/**
	 * getter for id
	 *
	 * @return id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * setter of id
	 *
	 * @param id セットする id
	 */
	public void setId(final Integer id) {
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
	 * getter for continent
	 *
	 * @return continent
	 */
	public String getContinent() {
		return this.continent;
	}

	/**
	 * setter of continent
	 *
	 * @param continent セットする continent
	 */
	public void setContinent(final String continent) {
		this.continent = continent;
	}

	/**
	 * getter for nation
	 *
	 * @return nation
	 */
	public String getNation() {
		return this.nation;
	}

	/**
	 * setter of nation
	 *
	 * @param nation セットする nation
	 */
	public void setNation(final String nation) {
		this.nation = nation;
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
	 * toString
	 */
	@Override
	public String toString() {
		return "CityView [id=" + this.id + ", name=" + this.name + ", continent=" + this.continent + ", nation="
				+ this.nation + ", district=" + this.district + ", population=" + this.population + "]";
	}
}
