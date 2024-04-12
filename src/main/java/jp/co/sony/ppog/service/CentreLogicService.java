package jp.co.sony.ppog.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jp.co.sony.ppog.dto.CityDto;
import jp.co.sony.ppog.entity.City;
import jp.co.sony.ppog.utils.Pagination;
import jp.co.sony.ppog.utils.RestMsg;

/**
 * 中央処理サービスインターフェス
 *
 * @author ArcaHozota
 * @since 4.40
 */
public interface CentreLogicService {

	/**
	 * 入力した都市名の重複するかどうかを検証する
	 *
	 * @param cityName 都市名
	 * @return List<City>
	 */
	List<City> checkDuplicate(String cityName);

	/**
	 * 大陸情報を取得する
	 *
	 * @return List<String>
	 */
	List<String> findAllContinents();

	/**
	 * 指定された国の公用語を取得する
	 *
	 * @param nationVal 国名
	 * @return List<String>
	 */
	String findLanguageByCty(String nationVal);

	/**
	 * 指定された大陸に位置するすべての国を取得する
	 *
	 * @param continentVal 大陸名称もしくは都市ID
	 * @return List<String>
	 */
	List<String> findNationsByCnt(String continentVal);

	/**
	 * 都市IDによって情報を抽出する
	 *
	 * @param id 都市ID
	 * @return CityInfoDto
	 */
	CityDto getCityInfoById(Integer id);

	/**
	 * パージング情報を抽出する
	 *
	 * @param pageNum ページングナンバー
	 * @param keyword 検索キーワード
	 * @return Page<CityDto>
	 */
	Page<CityDto> getPageInfo(Integer pageNum, String keyword);

	/**
	 * パージング情報を抽出する
	 *
	 * @param pageNum ページングナンバー
	 * @param keyword 検索キーワード
	 * @return Pagination<CityDto>
	 */
	Pagination<CityDto> getPagination(Integer pageNum, String keyword);

	/**
	 * 都市IDによって情報を削除する
	 *
	 * @param id 都市ID
	 */
	void removeById(Integer id);

	/**
	 * 入力した都市情報を保存する
	 *
	 * @param cityInfoDto 都市情報
	 */
	RestMsg save(CityDto cityInfoDto);

	/**
	 * 入力した都市情報を更新する
	 *
	 * @param cityInfoDto 都市情報
	 */
	RestMsg update(CityDto cityInfoDto);
}
