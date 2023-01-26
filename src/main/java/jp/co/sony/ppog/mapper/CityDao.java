package jp.co.sony.ppog.mapper;

import org.postgresql.util.PSQLException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sony.ppog.entity.City;

public interface CityDao extends JpaRepository<City, Integer> {

	/**
	 * logic remove query.
	 *
	 * @param id id of the selected city
	 */
	@Modifying
	@Transactional(rollbackFor = PSQLException.class)
	void removeById(@Param("id") final Integer id);
}
