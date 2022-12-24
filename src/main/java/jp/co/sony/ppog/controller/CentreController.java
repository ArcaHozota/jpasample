package jp.co.sony.ppog.controller;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jp.co.sony.ppog.entity.CityInfo;
import jp.co.sony.ppog.repository.CityDao;
import jp.co.sony.ppog.repository.CityInfoDao;
import jp.co.sony.ppog.repository.NationDao;

/**
 * Center Terminal Controller Handle the retrieve and update requests.
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/jpassmcrud")
public class CentreController {

	@Resource
	private CityDao cityDao;

	@Resource
	private NationDao nationDao;

	@Resource
	private CityInfoDao cityInfoDao;

	/**
	 * 都市情報を検索する
	 *
	 * @return modelAndView
	 */
	@GetMapping(value = "/city")
	public ModelAndView getCityInfo(@RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
			@RequestParam(value = "keyword", defaultValue = "") final String keyword) {
		final PageRequest pageRequest = PageRequest.of(pageNum - 1, 17, Sort.by(Sort.Direction.ASC, "id"));
		Page<CityInfo> dtoPage;
		if (StringUtils.isNotEmpty(keyword)) {
			final CityInfo cityInfo = new CityInfo();
			cityInfo.setName(keyword);
			final ExampleMatcher matcher = ExampleMatcher.matching()
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase(true)
					.withMatcher(keyword, ExampleMatcher.GenericPropertyMatchers.contains())
					.withIgnorePaths("id", "continent", "nation", "district", "population");
			final Example<CityInfo> example = Example.of(cityInfo, matcher);
			dtoPage = this.cityInfoDao.findAll(example, pageRequest);
		} else {
			dtoPage = this.cityInfoDao.findAll(pageRequest);
		}
		// modelAndViewオブジェクトを宣言する；
		final ModelAndView mav = new ModelAndView("index");
		// 前のページを取得する；
		final int current = dtoPage.getNumber();
		// ページングナビゲーションの数を定義する；
		final int naviNums = 7;
		// ページングナビの最初と最後の数を取得する；
		final int pageFirstIndex = (current / naviNums) * naviNums;
		int pageLastIndex = (current / naviNums + 1) * naviNums - 1;
		if (pageLastIndex > dtoPage.getTotalPages() - 1) {
			pageLastIndex = dtoPage.getTotalPages() - 1;
		} else {
			pageLastIndex = (current / naviNums + 1) * naviNums - 1;
		}
		mav.addObject("pageInfo", dtoPage);
		mav.addObject("keyword", keyword);
		mav.addObject("pageFirstIndex", pageFirstIndex);
		mav.addObject("pageLastIndex", pageLastIndex);
		return mav;
	}
}