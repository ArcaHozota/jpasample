package jp.co.sony.ppog.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 都市テーブルWORLD_CITYのエンティティ
 *
 * @author Administrator
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "WORLD_CITY")
@Proxy(lazy = false)
@NamedQuery(name = "City.removeById", query = "update City as c set c.logicDeleteFlg = 'removed' where c.id =:id")
@NamedQuery(name = "City.saiban", query = "select count(c.id) + 1 from City c")
public class City implements Serializable {

	private static final long serialVersionUID = 1815689293387304425L;

	/**
	 * This field corresponds to the database column ID
	 */
	@Id
	private Long id;

	/**
	 * This field corresponds to the database column NAME
	 */
	@Column(nullable = false)
	private String name;

	/**
	 * This field corresponds to the database column COUNTRY_CODE
	 */
	@Column(nullable = false)
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
	@Column(nullable = false)
	private String logicDeleteFlg;
}