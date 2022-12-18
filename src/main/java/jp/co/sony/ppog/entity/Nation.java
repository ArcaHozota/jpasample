package jp.co.sony.ppog.entity;

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

    /**
     * getter of field code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * setter of field code
     *
     * @param code WORLD_COUNTRY.CODE
     */
    public void setCode(String code) {
        this.code = code;
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
     * @param name WORLD_COUNTRY.NAME
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
     * @param continent WORLD_COUNTRY.CONTINENT
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * getter of field region
     *
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * setter of field region
     *
     * @param region WORLD_COUNTRY.REGION
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * getter of field surfaceArea
     *
     * @return surfaceArea
     */
    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    /**
     * setter of field surfaceArea
     *
     * @param surfaceArea WORLD_COUNTRY.SURFACE_AREA
     */
    public void setSurfaceArea(BigDecimal surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    /**
     * getter of field independenceYear
     *
     * @return independenceYear
     */
    public Integer getIndependenceYear() {
        return independenceYear;
    }

    /**
     * setter of field code
     *
     * @param independenceYear WORLD_COUNTRY.INDEPENDENCE_YEAR
     */
    public void setIndependenceYear(Integer independenceYear) {
        this.independenceYear = independenceYear;
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
     * @param population WORLD_COUNTRY.POPULATION
     */
    public void setPopulation(Long population) {
        this.population = population;
    }

    /**
     * getter of field lifeExpectancy
     *
     * @return lifeExpectancy
     */
    public BigDecimal getLifeExpectancy() {
        return lifeExpectancy;
    }

    /**
     * setter of field lifeExpectancy
     *
     * @param lifeExpectancy WORLD_COUNTRY.LIFE_EXPECTANCY
     */
    public void setLifeExpectancy(BigDecimal lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    /**
     * getter of field gnp
     *
     * @return gnp
     */
    public BigDecimal getGnp() {
        return gnp;
    }

    /**
     * setter of field gnp
     *
     * @param gnp WORLD_COUNTRY.GNP
     */
    public void setGnp(BigDecimal gnp) {
        this.gnp = gnp;
    }

    /**
     * getter of field gnpOld
     *
     * @return gnpOld
     */
    public BigDecimal getGnpOld() {
        return gnpOld;
    }

    /**
     * setter of field gnpOld
     *
     * @param gnpOld WORLD_COUNTRY.GNP_OLD
     */
    public void setGnpOld(BigDecimal gnpOld) {
        this.gnpOld = gnpOld;
    }

    /**
     * getter of field localName
     *
     * @return localName
     */
    public String getLocalName() {
        return localName;
    }

    /**
     * setter of field localName
     *
     * @param localName WORLD_COUNTRY.LOCAL_NAME
     */
    public void setLocalName(String localName) {
        this.localName = localName;
    }

    /**
     * getter of field governmentForm
     *
     * @return governmentForm
     */
    public String getGovernmentForm() {
        return governmentForm;
    }

    /**
     * setter of field governmentForm
     *
     * @param governmentForm WORLD_COUNTRY.GOVERNMENT_FORM
     */
    public void setGovernmentForm(String governmentForm) {
        this.governmentForm = governmentForm;
    }

    /**
     * getter of field headOfState
     *
     * @return headOfState
     */
    public String getHeadOfState() {
        return headOfState;
    }

    /**
     * setter of field headOfState
     *
     * @param headOfState WORLD_COUNTRY.HEAD_OF_STATE
     */
    public void setHeadOfState(String headOfState) {
        this.headOfState = headOfState;
    }

    /**
     * getter of field capital
     *
     * @return capital
     */
    public Long getCapital() {
        return capital;
    }

    /**
     * setter of field capital
     *
     * @param capital WORLD_COUNTRY.CAPITAL
     */
    public void setCapital(Long capital) {
        this.capital = capital;
    }

    /**
     * getter of field code2
     *
     * @return code2
     */
    public String getCode2() {
        return code2;
    }

    /**
     * setter of field code2
     *
     * @param code2 WORLD_COUNTRY.CODE2
     */
    public void setCode2(String code2) {
        this.code2 = code2;
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
     * @param isDeleted WORLD_COUNTRY.IS_DELETED
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Nation{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", surfaceArea=" + surfaceArea +
                ", independenceYear=" + independenceYear +
                ", population=" + population +
                ", lifeExpectancy=" + lifeExpectancy +
                ", gnp=" + gnp +
                ", gnpOld=" + gnpOld +
                ", localName='" + localName + '\'' +
                ", governmentForm='" + governmentForm + '\'' +
                ", headOfState='" + headOfState + '\'' +
                ", capital=" + capital +
                ", code2='" + code2 + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}