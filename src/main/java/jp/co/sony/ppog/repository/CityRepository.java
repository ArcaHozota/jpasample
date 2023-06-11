package jp.co.sony.ppog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sony.ppog.entity.City;
import oracle.jdbc.driver.OracleSQLException;

/**
 * 都市リポジトリ
 *
 * @author Administrator
 */
public interface CityRepository extends JpaRepository<City, Integer> {

	/**
	 * 論理削除
	 *
	 * @param id id of the selected city
	 */
	@Modifying
	@Transactional(rollbackFor = OracleSQLException.class)
	void removeById(@Param("id") Integer id);

	/**
	 * 更新
	 *
	 * @param id          都市ID
	 * @param name        都市名
	 * @param countryCode 国家コード
	 * @param district    地域
	 * @param population  人口数量
	 */
	@Modifying
	@Transactional(rollbackFor = OracleSQLException.class)
	@Query(value = "UPDATE WORLD_CITY WCN SET WCN.NAME=:name, WCN.COUNTRY_CODE=:countryCode, "
			+ "WCN.DISTRICT=:district, WCN.POPULATION=:population WHERE LOGIC_DELETE_FLG = 'visible' AND WCN.ID=:id", nativeQuery = true)
	void updateById(@Param("id") Long id, @Param("name") String name, @Param("countryCode") String countryCode,
			@Param("district") String district, @Param("population") Long population);
}
