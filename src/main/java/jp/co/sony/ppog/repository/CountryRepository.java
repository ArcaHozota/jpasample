package jp.co.sony.ppog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import jp.co.sony.ppog.entity.Country;

public interface CountryRepository extends JpaRepository<Country, String> {

	/**
	 * Retrieve continent list distinct.
	 *
	 * @return List<CityInfo>
	 */
	List<String> findAllContinents();

	/**
	 * Retrieve the nation list distinct.
	 *
	 * @param continent name of continent
	 * @return List<CityInfo>
	 */
	List<String> findNationsByCnt(@Param("continent") String continent);

	/**
	 * Retrieve the nationcd through name.
	 *
	 * @param name name of nation
	 * @return List<CityInfo>
	 */
	String findNationCode(@Param("name") String name);
}
