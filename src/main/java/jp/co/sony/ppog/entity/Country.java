package jp.co.sony.ppog.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity of Table WORLD_COUNTRY
 * 
 * @author Administrator
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "WORLD_COUNTRY")
@NamedQuery(name = "Country.findAllContinents", query = "select distinct n.continent from Country n order by n.continent asc")
@NamedQuery(name = "Country.findNationsByCnt", query = "select distinct n.name from Country n where n.continent =:continent order by n.name asc")
public class Country implements Serializable {

	private static final long serialVersionUID = 6762395398373991166L;

	/**
	 * This field corresponds to the database column CODE
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String code;

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
	 * This field corresponds to the database column REGION
	 */
	@Column(nullable = false)
	private String region;

	/**
	 * This field corresponds to the database column SURFACE_AREA
	 */
	@Column(name = "SURFACE_AREA", nullable = false)
	private BigDecimal surfaceArea;

	/**
	 * This field corresponds to the database column INDEPENDENCE_YEAR
	 */
	@Column(name = "INDEPENDENCE_YEAR")
	private Integer independenceYear;

	/**
	 * This field corresponds to the database column POPULATION
	 */
	@Column(nullable = false)
	private Long population;

	/**
	 * This field corresponds to the database column LIFE_EXPECTANCY
	 */
	@Column(name = "LIFE_EXPECTANCY")
	private Integer lifeExpectancy;

	/**
	 * This field corresponds to the database column GNP
	 */
	private BigDecimal gnp;

	/**
	 * This field corresponds to the database column GNP_OLD
	 */
	@Column(name = "GNP_OLD")
	private BigDecimal gnpOld;

	/**
	 * This field corresponds to the database column LOCAL_NAME
	 */
	@Column(name = "LOCAL_NAME", nullable = false)
	private String localName;

	/**
	 * This field corresponds to the database column GOVERNMENT_FORM
	 */
	@Column(name = "GOVERNMENT_FORM", nullable = false)
	private String governmentForm;

	/**
	 * This field corresponds to the database column HEAD_OF_STATE
	 */
	@Column(name = "HEAD_OF_STATE")
	private String headOfState;

	/**
	 * This field corresponds to the database column CAPITAL
	 */
	private Integer capital;

	/**
	 * This field corresponds to the database column CODE2
	 */
	@Column(nullable = false)
	private String code2;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	@Column(name = "LOGIC_DELETE_FLG", nullable = false)
	private String logicDeleteFlg;

	@Override
	public String toString() {
		return "Country [code=" + this.code + ", name=" + this.name + ", continent=" + this.continent + ", region="
				+ this.region + ", surfaceArea=" + this.surfaceArea + ", independenceYear=" + this.independenceYear
				+ ", population=" + this.population + ", lifeExpectancy=" + this.lifeExpectancy + ", gnp=" + this.gnp
				+ ", gnpOld=" + this.gnpOld + ", localName=" + this.localName + ", governmentForm="
				+ this.governmentForm + ", headOfState=" + this.headOfState + ", capital=" + this.capital + ", code2="
				+ this.code2 + ", logicDeleteFlg=" + this.logicDeleteFlg + "]";
	}
}
