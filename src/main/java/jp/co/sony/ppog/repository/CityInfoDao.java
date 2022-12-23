package jp.co.sony.ppog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sony.ppog.entity.CityInfo;

/**
 * searching dao of table WORLD_CITY_VIEW
 *
 * @author Administrator
 * @date 2022-12-17
 */
@Repository
public interface CityInfoDao extends JpaRepository<CityInfo, Long> {
}