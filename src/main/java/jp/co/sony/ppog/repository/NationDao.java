package jp.co.sony.ppog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sony.ppog.entity.Nation;

/**
 * searching dao of table WORLD_COUNTRY
 *
 * @author Administrator
 * @date 2022-12-16
 */
@Repository
public interface NationDao extends JpaRepository<Nation, String> {
}
