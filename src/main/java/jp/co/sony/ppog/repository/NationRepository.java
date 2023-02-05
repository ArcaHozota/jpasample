package jp.co.sony.ppog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sony.ppog.entity.Nation;

public interface NationRepository extends JpaRepository<Nation, String> {

	/**
	 * Retrieve the nation list distinct.
	 *
	 * @param continent name of continent
	 * @return List<CityInfo>
	 */
	List<Nation> findNationsByCnt(@Param("continent") final String continent);

	/**
	 * Retrieve the nationcd through name.
	 *
	 * @param name name of nation
	 * @return List<CityInfo>
	 */
	Nation findNationCode(@Param("name") final String name);

	/**
	 * Retrieve continent list distinct.
	 *
	 * @return List<CityInfo>
	 */
	@Query(value = "SELECT DISTINCT WCY.CONTINENT FROM WORLD_COUNTRY WCY", nativeQuery = true)
	List<String> findAllContinents();
}
