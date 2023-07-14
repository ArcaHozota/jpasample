package jp.co.sony.ppog.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 言語テーブルWORLD_LANGUAGEのエンティティ
 *
 * @author Administrator
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "WORLD_LANGUAGE")
@Proxy(lazy = false)
@IdClass(LanguageId.class)
@NamedQuery(name = "Language.findLanguagesByCity", query = "select nl from Language as nl where nl.logicDeleteFlg = 'visible' "
        + "and nl.countryCode =:countryCode order by nl.percentage desc")
public class Language implements Serializable {

    private static final long serialVersionUID = -8085659909634431823L;

    /**
     * This field corresponds to the database column COUNTRY_CODE
     */
    @Id
    private String countryCode;

    /**
     * This field corresponds to the database column LANGUAGE
     */
    @Id
    @Column(name = "LANGUAGE")
    private String name;

    /**
     * This field corresponds to the database column IS_OFFICIAL
     */
    @Column(nullable = false)
    private String isOfficial;

    /**
     * This field corresponds to the database column PERCENTAGE
     */
    @Column(nullable = false)
    private BigDecimal percentage;

    /**
     * This field corresponds to the database column LOGIC_DELETE_FLG
     */
    @Column(nullable = false)
    private String logicDeleteFlg;
}
