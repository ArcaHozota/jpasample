package jp.co.toshiba.ppok.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.toshiba.ppok.entity.CityEm;

/**
 * searching dao of table WORLD_CITY_VIEW
 *
 * @author Administrator
 * @date 2022-12-17
 */
@Repository
public interface CityEmDao extends JpaRepository<CityEm, Long> {

	/**
	 * ページング検索ID昇順
	 *
	 * @param pageable ページング
	 * @return Pageオブジェクト
	 */
	Page<CityEm> findByOrderByIdAsc(Pageable pageable);
}