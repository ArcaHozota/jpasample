package jp.co.sony.ppog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * dto of the view of world cities
 *
 * @author Administrator
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Proxy(lazy = false)
@Table(name = "WORLD_CITY_VIEW")
@NamedQuery(name = "CityView.findByNations", query = "select cv from CityView cv where cv.nation =:nation")
@NamedQuery(name = "CityView.getByNations", query = "select cv from CityView cv where cv.nation =:nation order by cv.id asc")
@NamedQuery(name = "CityView.getByNames", query = "select cv from CityView cv where cv.name like concat('%', :name, '%') order by cv.id asc")
public class CityView implements Serializable {

	private static final long serialVersionUID = 6678964783710878220L;

	/**
	 * This field corresponds to the database column id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * This field corresponds to the database column name
	 */
	@Column(nullable = false)
	@Pattern(regexp = "^[a-zA-Z-\\p{IsWhiteSpace}]{4,17}$", message = "Name of cities should be in 4~17 Latin alphabets.")
	private String name;

	/**
	 * This field corresponds to the database column continent
	 */
	@Column(nullable = false)
	private String continent;

	/**
	 * This field corresponds to the database column nation
	 */
	@Column(nullable = false)
	private String nation;

	/**
	 * This field corresponds to the database column district
	 */
	@Column(nullable = false)
	private String district;

	/**
	 * This field corresponds to the database column population
	 */
	@Column(nullable = false)
	private Long population;

	@Override
	public String toString() {
		return "CityView [id=" + this.id + ", name=" + this.name + ", continent=" + this.continent + ", nation="
				+ this.nation + ", district=" + this.district + ", population=" + this.population + "]";
	}
}
