package jp.co.sony.ppog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
	 * @param city entity
	 */
	@Modifying
	@Transactional(rollbackFor = OracleSQLException.class)
	@Query(value = "UPDATE WORLD_CITY WCN SET WCN.NAME=:city.name, WCN.COUNTRY_CODE=:city.countryCode, "
			+ "WCN.DISTRICT=:city.district, WCN.POPULATION=:city.population, WCN.IS_DELETED=:city.isDeleted "
			+ "WHERE WCN.ID=:city.id", nativeQuery = true)
	void updateById(@Param("city") City city);
}
