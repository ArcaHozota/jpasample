package jp.co.sony.ppog.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sony.ppog.entity.Language;

/**
 * Searching repository of entity Language
 *
 * @author Administrator
 */
public interface LanguageRepository extends JpaRepository<Language, String> {

	@Query(value = "SELECT NL.LANGUAGE FROM WORLD_LANGUAGE NL INNER JOIN " +
			"(SELECT WL.COUNTRY_CODE, MIN(WL.PERCENTAGE) AS PERCENTAGE FROM WORLD_LANGUAGE WL"
			+ "WHERE WL.LOGIC_DELETE_FLG = 'visible' AND WL.IS_OFFICIAL = 'T' AND WL.COUNTRY_CODE =:countryCode " +
			"AND WL.PERCENTAGE >=:percentage GROUP BY WL.COUNTRY_CODE) SUB " +
			"ON NL.COUNTRY_CODE = SUB.COUNTRY_CODE AND NL.PERCENTAGE = SUB.PERCENTAGE", nativeQuery = true)
	Language findLanguageByCity(@Param("percentage") BigDecimal percentage, @Param("countryCode") String countryCode);
}
