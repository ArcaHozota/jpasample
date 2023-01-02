package jp.co.sony.ppog.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import jp.co.sony.ppog.entity.CityView;

/**
 * @author Administrator
 */
public interface CityViewService extends IService<CityView> {

	/**
	 * Search continents of cities located on.
	 *
	 * @return List<CityView>
	 */
	List<String> getContinents();

	/**
	 * Search nation's name of cities.
	 *
	 * @param continent name of continent which the nation located on.
	 * @return List<CityView>
	 */
	List<String> getNations(String continent);

	/**
	 * Save city info.
	 *
	 * @param cityView city info
	 */
	void saveCityInfo(CityView cityView);

	/**
	 * Update city info.
	 *
	 * @param cityView city info
	 */
	void updateCityInfo(CityView cityView);

	/**
	 * Delete city info by id.
	 *
	 * @param id city id
	 */
	void deleteCityInfo(Integer id);

	/**
	 * Check the duplicated name of cities.
	 *
	 * @param name city name
	 * @return true: duplicated, false: insertable
	 */
	boolean checkDuplicated(String name);
}
