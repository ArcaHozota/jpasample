package jp.co.sony.ppog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sony.ppog.entity.CityInfo;

public interface CityInfoRepository extends JpaRepository<CityInfo, Long>, JpaSpecificationExecutor<CityInfo> {

	/**
	 * 人口数量昇順で都市情報を検索する
	 *
	 * @param sort ソート
	 * @return List<City>
	 */
	@Query(value = "select cn.id, cn.name, cn.country_code, cn.district, cn.population from city as cn "
			+ "where cn.delete_flg = 'visible' order by cn.population limit :sortNumber", nativeQuery = true)
	List<CityInfo> findMinimumRanks(@Param("sortNumber") Integer sort);

	/**
	 * 人口数量降順で都市情報を検索する
	 *
	 * @param sort ソート
	 * @return List<City>
	 */
	@Query(value = "select cn.id, cn.name, cn.country_code, cn.district, cn.population from city as cn "
			+ "where cn.delete_flg = 'visible' order by cn.population desc limit :sortNumber", nativeQuery = true)
	List<CityInfo> findMaximumRanks(@Param("sortNumber") Integer sort);
}
