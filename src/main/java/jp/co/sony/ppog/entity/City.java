package jp.co.sony.ppog.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 都市テーブルのエンティティ
 *
 * @author ArcaHozota
 * @since 2.17
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "city")
@NamedQuery(name = "City.saiban", query = "select count(cn.id) + 1 from City as cn")
@NamedQuery(name = "City.removeById", query = "update City as cn set cn.deleteFlg = 'removed' where cn.id =:id")
public final class City implements Serializable {

	private static final long serialVersionUID = 1815689293387304425L;

	/**
	 * This field corresponds to the database column ID
	 */
	@Id
	private Integer id;

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
	private Integer population;

	/**
	 * This field corresponds to the database column LOGIC_DELETE_FLG
	 */
	@Column(nullable = false)
	private String deleteFlg;

	/**
	 * This field corresponds to the database table country
	 */
	@ManyToOne
	@JoinColumn(name = "countryCode", insertable = false, updatable = false)
	private Country country;
}