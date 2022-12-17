package jp.co.toshiba.ppok.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Administrator
 */
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
    private String name;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.CONTINENT
     */
    private String continent;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.REGION
     */
    private String region;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.SURFACE_AREA
     */
    @Column(name = "surface_area")
    private BigDecimal surfaceArea;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.INDEPENDENCE_YEAR
     */
    @Column(name = "independence_year")
    private Integer independenceYear;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.POPULATION
     */
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
    @Column(name = "local_name")
    private String localName;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.GOVERNMENT_FORM
     */
    @Column(name = "government_form")
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
    private String code2;

    /**
     * This field corresponds to the database column WORLD_COUNTRY.IS_DELETED
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;
}