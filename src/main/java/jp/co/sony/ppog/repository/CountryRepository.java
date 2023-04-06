package jp.co.sony.ppog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sony.ppog.entity.Country;

/**
 * 国家リポジトリ
 *
 * @author Administrator
 */
public interface CountryRepository extends JpaRepository<Country, String> {

	/**
	 * 大陸の集合を取得する
	 *
	 * @return List<String>
	 */
	List<String> findAllContinents();

	/**
	 * 選択された大陸の上にすべての国の情報を取得する
	 *
	 * @param continent 大陸名
	 * @return List<String>
	 */
	List<String> findNationsByCnt(@Param("continent") String continent);

	/**
	 * 国名によって国家コードを抽出する
	 *
	 * @param name 国名
	 * @return String
	 */
	@Query(value = "SELECT WCY.CODE FROM WORLD_COUNTRY WCY WHERE "
			+ "LOGIC_DELETE_FLG = 'visible' AND WCY.NAME=:name", nativeQuery = true)
	String findNationCode(@Param("name") String name);
}
