package jp.co.toshiba.ppok.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

/**
 * dto of the view of world cities
 *
 * @author Administrator
 */

@Entity
@Table(name = "world_city_view")
public class CityDto implements Serializable {

	private static final long serialVersionUID = -863534569423043863L;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.NAME
	 */
	@Pattern(regexp = "^[a-zA-Z_-]{4,17}$", message = "Name of cities should be in 4~17 Latin alphabets.")
	private String name;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.CONTINENT
	 */
	private String continent;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.NATION
	 */
	private String nation;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.DISTRICT
	 */
	private String district;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.POPULATION
	 */
	private Long population;
}
