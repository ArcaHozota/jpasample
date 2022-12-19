package jp.co.sony.ppog.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
@Entity
@Table(name = "world_country")
public class Nation implements Serializable {

	private static final long serialVersionUID = -437505450837045511L;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.CODE
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String code;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.NAME
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.CONTINENT
	 */
	@Column(nullable = false)
	private String continent;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.REGION
	 */
	@Column(nullable = false)
	private String region;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.SURFACE_AREA
	 */
	@Column(name = "surface_area", nullable = false)
	private BigDecimal surfaceArea;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.INDEPENDENCE_YEAR
	 */
	@Column(name = "independence_year")
	private Integer independenceYear;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.POPULATION
	 */
	@Column(nullable = false)
	private Long population;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.LIFE_EXPECTANCY
	 */
	@Column(name = "life_expectancy")
	private BigDecimal lifeExpectancy;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.GNP
	 */
	private BigDecimal gnp;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.GNP_OLD
	 */
	@Column(name = "gnp_old")
	private BigDecimal gnpOld;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.LOCAL_NAME
	 */
	@Column(name = "local_name", nullable = false)
	private String localName;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.GOVERNMENT_FORM
	 */
	@Column(name = "government_form", nullable = false)
	private String governmentForm;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.HEAD_OF_STATE
	 */
	@Column(name = "head_of_state")
	private String headOfState;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.CAPITAL
	 */
	private Long capital;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.CODE2
	 */
	@Column(nullable = false)
	private String code2;

	/**
	 * This field corresponds to the database column WORLD_COUNTRY.IS_DELETED
	 */
	@Column(name = "is_deleted", nullable = false)
	private Integer isDeleted;
}