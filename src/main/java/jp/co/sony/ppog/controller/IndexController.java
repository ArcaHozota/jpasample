package jp.co.sony.ppog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sony.ppog.dto.CityDto;
import jp.co.sony.ppog.service.CentreLogicService;
import jp.co.sony.ppog.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * 画面初期表示コントローラ
 *
 * @author ArcaHozota
 * @since 1.78
 */
@Controller
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IndexController {

	/**
	 * 中央処理サービスインターフェス
	 */
	private final CentreLogicService centreLogicService;

	/**
	 * 都市情報を検索する
	 *
	 * @return modelAndView
	 */
	@GetMapping(value = "/index")
	public ModelAndView initial() {
		// ページング検索結果を吹き出します；
		final Page<CityDto> pageInfo = this.centreLogicService.getPageInfo(1, StringUtils.EMPTY_STRING);
		// modelAndViewオブジェクトを宣言する；
		final ModelAndView modelAndView = new ModelAndView("index");
		final Map<String, Object> extendMap = new HashMap<>();
		extendMap.put("pageInfo", pageInfo);
		extendMap.put("keyword", StringUtils.EMPTY_STRING);
		modelAndView.addObject("extend", extendMap);
		return modelAndView;
	}
}
