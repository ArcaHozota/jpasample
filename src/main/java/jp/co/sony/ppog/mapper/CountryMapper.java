package jp.co.sony.ppog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.sony.ppog.entity.Country;

@Mapper
public interface CountryMapper extends BaseMapper<Country> {

	/**
	 * Search continents of cities located on.
	 *
	 * @return List<CityDto>
	 */
	List<String> selectContinents();

	/**
	 * Search nation's name of cities.
	 *
	 * @param continent name of continent which the nation located on.
	 * @return List<CityDto>
	 */
	List<String> selectNations(String continent);
}
