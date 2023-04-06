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
	 * Retrieve city infos by nation name provided.
	 *
	 * @param nation name of nation
	 * @return List<CityInfo>
	 */
	List<CityView> findByNations(@Param("nation") String nation);

	/**
	 * Retrieve city infos by nation name provided.
	 *
	 * @param nation   name of nation
	 * @param pageable page
	 * @return Page<CityInfo>
	 */
	Page<CityView> getByNations(@Param("nation") String nation, Pageable pageable);

	/**
	 * Retrieve city infos by city name provided.
	 *
	 * @param name     city name
	 * @param pageable page
	 * @return Page<CityInfo>
	 */
	Page<CityView> getByNames(@Param("name") String name, Pageable pageable);

	/**
	 * Retrieve city infos by population ascending.
	 *
	 * @return Page<CityInfo>
	 */
	@Query(value = "SELECT WCV.ID, WCV.NAME, WCV.CONTINENT, WCV.NATION, WCV.DISTRICT, WCV.POPULATION FROM WORLD_CITY_VIEW WCV ORDER BY WCV.POPULATION ASC FETCH FIRST 15 ROWS ONLY", nativeQuery = true)
	List<CityView> findMinimumRanks();

	/**
	 * Retrieve city infos by population descending.
	 *
	 * @return Page<CityInfo>
	 */
	@Query(value = "SELECT WCV.ID, WCV.NAME, WCV.CONTINENT, WCV.NATION, WCV.DISTRICT, WCV.POPULATION FROM WORLD_CITY_VIEW WCV ORDER BY WCV.POPULATION DESC FETCH FIRST 15 ROWS ONLY", nativeQuery = true)
	List<CityView> findMaximumRanks();
}