package jp.co.sony.ppog.repository;

import jp.co.sony.ppog.entity.CityView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 都市情報リポジトリ
 *
 * @author Administrator
 */
public interface CityViewRepository extends JpaRepository<CityView, Long>, JpaSpecificationExecutor<CityView> {

    /**
     * 国名によって都市情報を検索する
     *
     * @param nation 国名
     * @return List<CityView>
     */
    List<CityView> findByNations(@Param("nation") String nation);

    /**
     * 人口数量昇順で都市情報を検索する
     *
     * @return List<CityView>
     */
    @Query(value = "SELECT WCV.CITY_ID, WCV.CITY_NAME, WCV.CONTINENT, WCV.COUNTRY_NAME, WCV.DISTRICT, WCV.POPULATION "
            + "FROM WORLD_CITY_VIEW WCV ORDER BY WCV.POPULATION FETCH FIRST 15 ROWS ONLY", nativeQuery = true)
    List<CityView> findMinimumRanks();

    /**
     * 人口数量降順で都市情報を検索する
     *
     * @return List<CityView>
     */
    @Query(value = "SELECT WCV.CITY_ID, WCV.CITY_NAME, WCV.CONTINENT, WCV.COUNTRY_NAME, WCV.DISTRICT, WCV.POPULATION "
            + "FROM WORLD_CITY_VIEW WCV ORDER BY WCV.POPULATION DESC FETCH FIRST 15 ROWS ONLY", nativeQuery = true)
    List<CityView> findMaximumRanks();
}