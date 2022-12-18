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
public class CityEm implements Serializable {

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
    @Column(nullable = false)
    @Pattern(regexp = "^[a-zA-Z_-]{4,17}$", message = "Name of cities should be in 4~17 Latin alphabets.")
    private String name;

    /**
     * This field corresponds to the database column WORLD_CITY_VIEW.CONTINENT
     */
    @Column(nullable = false)
    private String continent;

    /**
     * This field corresponds to the database column WORLD_CITY_VIEW.NATION
     */
    @Column(nullable = false)
    private String nation;

    /**
     * This field corresponds to the database column WORLD_CITY_VIEW.DISTRICT
     */
    @Column(nullable = false)
    private String district;

    /**
     * This field corresponds to the database column WORLD_CITY_VIEW.POPULATION
     */
    @Column(nullable = false)
    private Long population;

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
     * @param id WORLD_CITY_VIEW.ID
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
     * @param name WORLD_CITY_VIEW.NAME
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * getter of field continent
     *
     * @return continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * setter of field continent
     *
     * @param continent WORLD_CITY_VIEW.CONTINENT
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * getter of field nation
     *
     * @return nation
     */
    public String getNation() {
        return nation;
    }

    /**
     * setter of field nation
     *
     * @param nation WORLD_CITY_VIEW.NATION
     */
    public void setNation(String nation) {
        this.nation = nation;
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
     * setter of field nation
     *
     * @param district WORLD_CITY_VIEW.DISTRICT
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
     * @param population WORLD_CITY_VIEW.POPULATION
     */
    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "CityDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", nation='" + nation + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
