package jp.co.toshiba.ppok.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * @author Administrator
 */
@Entity
@Table(name = "world_city")
public class City implements Serializable {

    private static final long serialVersionUID = 1815689293387304425L;

    /**
     * This field corresponds to the database column WORLD_CITY.ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * This field corresponds to the database column WORLD_CITY.NAME
     */
    @Column(nullable = false)
    private String name;

    /**
     * This field corresponds to the database column WORLD_CITY.COUNTRY_CODE
     */
    @Column(name = "country_code", nullable = false)
    private String countryCode;

    /**
     * This field corresponds to the database column WORLD_CITY.DISTRICT
     */
    @Column(nullable = false)
    private String district;

    /**
     * This field corresponds to the database column WORLD_CITY.POPULATION
     */
    @Column(nullable = false)
    private Long population;

    /**
     * This field corresponds to the database column WORLD_CITY.IS_DELETED
     */
    @Column(name = "is_deleted", nullable = false)
    private Integer isDeleted;

    /**
     * getter of field id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * setter of field id
     *
     * @param id WORLD_CITY.ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * getter of field name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * setter of field name
     *
     * @param name WORLD_CITY.NAME
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter of field countryCode
     *
     * @return countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * setter of field countryCode
     *
     * @param countryCode WORLD_CITY.COUNTRY_CODE
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * getter of field district
     *
     * @return district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * setter of field district
     *
     * @param district WORLD_CITY.DISTRICT
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * getter of field population
     *
     * @return population
     */
    public Long getPopulation() {
        return population;
    }

    /**
     * setter of field population
     *
     * @param population WORLD_CITY.POPULATION
     */
    public void setPopulation(Long population) {
        this.population = population;
    }

    /**
     * getter of field isDeleted
     *
     * @return isDeleted
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * setter of field isDeleted
     *
     * @param isDeleted WORLD_CITY.IS_DELETED
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                ", isDeleted=" + isDeleted +
                '}';
    }
}