package jp.co.sony.ppog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sony.ppog.entity.City;
import oracle.jdbc.driver.OracleSQLException;

public interface CityRepository extends JpaRepository<City, Integer> {

	/**
	 * logic remove query.
	 *
	 * @param id id of the selected city
	 */
	@Modifying
	@Transactional(rollbackFor = OracleSQLException.class)
	void removeById(@Param("id") Integer id);

	/**
	 * update query.
	 * 
	 * @param id          ID
	 * @param name        NAME
	 * @param countryCode COUNTRY_CODE
	 * @param district    DISTRICT
	 * @param population  POPULATION
	 * @param isDeleted   IS_DELETED
	 */
	@Modifying
	@Transactional(rollbackFor = OracleSQLException.class)
	void updateById(@Param("id") Integer id, @Param("name") String name, @Param("countryCode") String countryCode,
			@Param("district") String district, @Param("population") Long population,
			@Param("isDeleted") Integer isDeleted);
}
