package jp.co.sony.ppog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sony.ppog.entity.CityView;

/**
 * 都市情報リポジトリ
 *
 * @author Administrator
 */
public interface CityViewRepository extends JpaRepository<CityView, Integer> {

	/**
	 * 国名によって都市情報を検索する
	 *
	 * @param nation 国名
	 * @return List<CityView>
	 */
	List<CityView> findByNations(@Param("nation") String nation);

	/**
	 * 国名によって都市情報をページング取得する
	 *
	 * @param nation   国名
	 * @param pageable ページング条件
	 * @return Page<CityView>
	 */
	Page<CityView> getByNations(@Param("nation") String nation, Pageable pageable);

	/**
	 * 都市名によって情報を取得する
	 *
	 * @param name     都市名
	 * @param pageable ページング条件
	 * @return Page<CityView>
	 */
	Page<CityView> getByNames(@Param("name") String name, Pageable pageable);

	/**
	 * 人口数量昇順で都市情報を検索する
	 *
	 * @return List<CityView>
	 */
	@Query(value = "SELECT WCV.ID, WCV.NAME, WCV.CONTINENT, WCV.NATION, WCV.DISTRICT, WCV.POPULATION "
			+ "FROM WORLD_CITY_VIEW WCV ORDER BY WCV.POPULATION ASC FETCH FIRST 15 ROWS ONLY", nativeQuery = true)
	List<CityView> findMinimumRanks();

	/**
	 * 人口数量降順で都市情報を検索する
	 *
	 * @return List<CityView>
	 */
	@Query(value = "SELECT WCV.ID, WCV.NAME, WCV.CONTINENT, WCV.NATION, WCV.DISTRICT, WCV.POPULATION "
			+ "FROM WORLD_CITY_VIEW WCV ORDER BY WCV.POPULATION DESC FETCH FIRST 15 ROWS ONLY", nativeQuery = true)
	List<CityView> findMaximumRanks();
}