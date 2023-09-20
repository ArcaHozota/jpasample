package jp.co.sony.ppog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sony.ppog.entity.City;
import oracle.jdbc.driver.OracleSQLException;

/**
 * 都市リポジトリ
 *
 * @author Administrator
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

	/**
	 * 採番を行います
	 *
	 * @return 採番値
	 */
	Long saiban();

	/**
	 * 論理削除
	 *
	 * @param id id of the selected city
	 */
	@Modifying
	@Transactional(rollbackFor = OracleSQLException.class)
	void removeById(@Param("id") Long id);

	/**
	 * 人口数量昇順で都市情報を検索する
	 *
	 * @return List<CityView>
	 */
	@Query(value = "SELECT WCV.ID, WCV.CONTINENT, WCV.NATION, WCV.NAME, WCV.DISTRICT, WCV.POPULATION "
			+ "FROM WORLD_CITY_VIEW WCV ORDER BY WCV.POPULATION ASC FETCH FIRST :sortNumber ROWS ONLY", nativeQuery = true)
	List<City> findMinimumRanks(@Param("sortNumber") Integer sort);

	/**
	 * 人口数量降順で都市情報を検索する
	 *
	 * @return List<CityView>
	 */
	@Query(value = "SELECT WCV.ID, WCV.CONTINENT, WCV.NATION, WCV.NAME, WCV.DISTRICT, WCV.POPULATION "
			+ "FROM WORLD_CITY_VIEW WCV ORDER BY WCV.POPULATION DESC FETCH FIRST :sortNumber ROWS ONLY", nativeQuery = true)
	List<City> findMaximumRanks(@Param("sortNumber") Integer sort);
}
