package jp.co.sony.ppog.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jp.co.sony.ppog.dto.CityInfoDto;

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
	 * 入力した都市情報を更新する
	 *
	 * @param cityInfoDto 都市情報
	 */
	void update(CityInfoDto cityInfoDto);
}
