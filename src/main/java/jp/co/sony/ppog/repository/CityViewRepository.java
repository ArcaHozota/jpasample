package jp.co.sony.ppog.repository;

import jp.co.sony.ppog.entity.CityView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 都市情報リポジトリ
 *
 * @author Administrator
 */
public interface CityViewRepository extends JpaRepository<CityView, Long> {

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