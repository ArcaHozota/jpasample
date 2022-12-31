package jp.co.sony.ppog.entity;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * dto of the view of world cities
 *
 * @author Administrator
 */

@Data
public class CityView implements Serializable {

	private static final long serialVersionUID = 6678964783710878220L;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.ID
	 */
	private Long id;

	/**
	 * This field corresponds to the database column WORLD_CITY_VIEW.NAME
	 */
	@Pattern(regexp = "^[a-zA-Z-\\p{IsWhiteSpace}]{4,17}$", message = "都市名は4桁から23桁までのローマ字にしなければなりません。")
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
