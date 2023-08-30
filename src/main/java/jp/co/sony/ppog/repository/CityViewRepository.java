package jp.co.sony.ppog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import jp.co.sony.ppog.entity.CityView;

/**
 * 都市情報リポジトリ
 *
 * @author Administrator
 */
public interface CityViewRepository extends JpaRepository<CityView, Long>, JpaSpecificationExecutor<CityView> {

	/**
	 * 人口数量昇順で都市情報を検索する
	 *
	 * @return List<CityView>
	 */
	@Query(value = "SELECT WCV.CITY_ID, WCV.CITY_NAME, WCV.CONTINENT, WCV.COUNTRY_NAME, WCV.DISTRICT, WCV.POPULATION "
			+ "FROM WORLD_CITY_VIEW WCV ORDER BY WCV.POPULATION FETCH FIRST 90 ROWS ONLY", nativeQuery = true)
	List<CityView> findMinimumRanks();

	/**
	 * 人口数量降順で都市情報を検索する
	 *
	 * @return List<CityView>
	 */
	@Query(value = "SELECT WCV.CITY_ID, WCV.CITY_NAME, WCV.CONTINENT, WCV.COUNTRY_NAME, WCV.DISTRICT, WCV.POPULATION "
			+ "FROM WORLD_CITY_VIEW WCV ORDER BY WCV.POPULATION DESC FETCH FIRST 90 ROWS ONLY", nativeQuery = true)
	List<CityView> findMaximumRanks();
}