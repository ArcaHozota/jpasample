package jp.co.sony.ppog.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sony.ppog.entity.CityInfo;

/**
 * @author Administrator
 */
public interface CityInfoDao extends JpaRepository<CityInfo, Integer> {

	/**
	 * Retrieve city infos by nation name provided.
	 *
	 * @param nation name of nation
	 * @return List<CityInfo>
	 */
	List<CityInfo> findByNations(@Param("nation") final String nation);

	/**
	 * Retrieve city infos by nation name provided.
	 *
	 * @param nation   name of nation
	 * @param pageable page
	 * @return Page<CityInfo>
	 */
	Page<CityInfo> getByNations(@Param("nation") final String nation, final Pageable pageable);

	/**
	 * Retrieve city infos by city name provided.
	 *
	 * @param name     city name
	 * @param pageable page
	 * @return Page<CityInfo>
	 */
	Page<CityInfo> getByNames(@Param("name") final String name, final Pageable pageable);

	/**
	 * Retrieve city infos by population ascending.
	 *
	 * @return Page<CityInfo>
	 */
	@Query(value = "select * from city_view cvn order by cvn.population asc fetch first 15 rows only", nativeQuery = true)
	List<CityInfo> findMinimumRanks();

	/**
	 * Retrieve city infos by population descending.
	 *
	 * @return Page<CityInfo>
	 */
	@Query(value = "select * from city_view cvn order by cvn.population desc fetch first 15 rows only", nativeQuery = true)
	List<CityInfo> findMaximumRanks();
}