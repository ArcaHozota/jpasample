package jp.co.sony.ppog.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jp.co.sony.ppog.dto.CityInfoDto;
import jp.co.sony.ppog.entity.City;

/**
 * 中央処理サービスインターフェス
 *
 * @author Administrator
 */
public interface CentreLogicService {

	/**
	 * パージング情報を抽出する
	 *
	 * @param pageNum ページングナンバー
	 * @param keyword 検索キーワード
	 * @return Page<CityInfoDto>
	 */
	Page<CityInfoDto> getPageInfo(Integer pageNum, String keyword);

	/**
	 * 都市IDによって情報を抽出する
	 *
	 * @param id 都市ID
	 * @return CityInfoDto
	 */
	CityInfoDto getCityInfoById(Integer id);

	/**
	 * 都市IDによって国家名を抽出する
	 *
	 * @param id 都市ID
	 * @return List<String>
	 */
	List<String> getListOfNationsById(Integer id);

	/**
	 * 入力した都市情報を保存する
	 *
	 * @param cityInfoDto 都市情報
	 */
	void save(CityInfoDto cityInfoDto);

	/**
	 * 入力した都市情報を更新する
	 *
	 * @param cityInfoDto 都市情報
	 */
	void update(CityInfoDto cityInfoDto);

	/**
	 * 都市IDによって情報を削除する
	 *
	 * @param id 都市ID
	 */
	void removeById(Integer id);

	/**
	 * 大陸情報を取得する
	 *
	 * @return List<String>
	 */
	List<String> findAllContinents();

	/**
	 * 指定された大陸に位置するすべての国を取得する
	 *
	 * @param continentVal 大陸名称
	 * @return List<String>
	 */
	List<String> findNationsByCnt(String continentVal);

	/**
	 * 入力した都市名の重複するかどうかを検証する
	 *
	 * @param cityName 都市名
	 * @return List<City>
	 */
	List<City> checkDuplicate(String cityName);
}
