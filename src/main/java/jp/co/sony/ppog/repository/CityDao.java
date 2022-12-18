package jp.co.sony.ppog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sony.ppog.entity.City;

/**
 * searching dao of table WORLD_CITY
 *
 * @author Administrator
 * @date 2022-12-17
 */
@Repository
public interface CityDao extends JpaRepository<City, Long> {
}
