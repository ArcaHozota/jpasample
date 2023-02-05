package jp.co.sony.ppog.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Lists;

import jp.co.sony.ppog.dto.CityInfo;
import jp.co.sony.ppog.entity.City;
import jp.co.sony.ppog.entity.CityView;
import jp.co.sony.ppog.entity.Country;
import jp.co.sony.ppog.repository.CityRepository;
import jp.co.sony.ppog.repository.CityViewRepository;
import jp.co.sony.ppog.repository.CountryRepository;
import jp.co.sony.ppog.utils.RestMsg;
import jp.co.sony.ppog.utils.StringUtils;

/**
 * 中央処理コントローラ
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/ssmcrud")
public class CentreController {

	@Resource
	private CityRepository cityRepository;

	@Resource
	private CityViewRepository cityViewRepository;

	@Resource
	private CountryRepository countryRepository;

	/**
	 * 都市情報を検索する
	 *
	 * @return modelAndView
	 */
	@GetMapping(value = "/city")
	public ModelAndView getCityInfo(@RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
			@RequestParam(value = "keyword", defaultValue = "") final String keyword) {
		// ページングコンストラクタを宣言する；
		final PageRequest pageRequest = PageRequest.of(pageNum - 1, 17);
		final Page<CityView> pageInfo;
		// キーワードの属性を判断する；
		if (StringUtils.isNotEmpty(keyword)) {
			// ページング検索；
			final List<CityView> findByNations = this.cityViewRepository.findByNations(keyword);
			if (!findByNations.isEmpty()) {
				pageInfo = this.cityViewRepository.getByNations(keyword, pageRequest);
			} else if (StringUtils.isEqual("min(pop)", keyword)) {
				// 人口数量昇順で最初の15個都市の情報を吹き出します；
				final List<CityView> minimumRanks = this.cityViewRepository.findMinimumRanks();
				pageInfo = new PageImpl<>(minimumRanks);
			} else if (StringUtils.isEqual("max(pop)", keyword)) {
				// 人口数量降順で最初の15個都市の情報を吹き出します；
				final List<CityView> maximumRanks = this.cityViewRepository.findMaximumRanks();
				pageInfo = new PageImpl<>(maximumRanks);
			} else {
				// ページング検索；
				pageInfo = this.cityViewRepository.getByNames(keyword, pageRequest);
			}
		} else {
			// ページング検索；
			pageInfo = this.cityViewRepository.findAll(pageRequest);
		}
		// modelAndViewオブジェクトを宣言する；
		final ModelAndView mav = new ModelAndView("index");
		// 前のページを取得する；
		final int current = pageInfo.getNumber();
		// ページングナビゲーションの数を定義する；
		final int naviNums = 7;
		// ページングナビの最初と最後の数を取得する；
		final int pageFirstIndex = (current / naviNums) * naviNums;
		int pageLastIndex = (current / naviNums + 1) * naviNums - 1;
		if (pageLastIndex > pageInfo.getTotalPages() - 1) {
			pageLastIndex = pageInfo.getTotalPages() - 1;
		} else {
			pageLastIndex = (current / naviNums + 1) * naviNums - 1;
		}
		mav.addObject("pageInfo", pageInfo);
		mav.addObject("keyword", keyword);
		mav.addObject("pageFirstIndex", pageFirstIndex);
		mav.addObject("pageLastIndex", pageLastIndex);
		mav.addObject("title", "CityList");
		return mav;
	}

	/**
	 * 指定された都市の情報を取得する
	 *
	 * @param id 都市ID
	 * @return 都市情報
	 */
	@GetMapping(value = "/city/{id}")
	@ResponseBody
	public RestMsg getCityInfo(@PathVariable("id") final Integer id) {
		final CityView cityView = this.cityViewRepository.getById(id);
		return RestMsg.success().add("citySelected", cityView);
	}

	/**
	 * 指定された都市の大陸に位置するすべての国を取得する
	 *
	 * @param id 都市ID
	 * @return 国のリスト
	 */
	@GetMapping(value = "/nations/{id}")
	@ResponseBody
	public RestMsg getListOfNationsById(@PathVariable("id") final Integer id) {
		final List<String> list = Lists.newArrayList();
		final CityView cityView = this.cityViewRepository.getById(id);
		final String nationName = cityView.getNation();
		list.add(nationName);
		final String continent = cityView.getContinent();
		final List<Country> nations = this.countryRepository.findNationsByCnt(continent);
		nations.forEach(item -> {
			if (StringUtils.isNotEqual(nationName, item.getName())) {
				list.add(item.getName());
			}
		});
		return RestMsg.success().add("nationsWithName", list);
	}

	/**
	 * 入力した都市情報を変更する
	 *
	 * @param cityInfo 都市情報DTO
	 * @return 処理成功のメッセージ
	 */
	@PutMapping(value = "/city/{id}")
	@ResponseBody
	public RestMsg updateCityInfo(@RequestBody final CityInfo cityInfo) {
		final City city = this.saveAndUpdate(cityInfo);
		this.cityRepository.updateById(city);
		return RestMsg.success();
	}

	/**
	 * 入力した都市情報を保存する
	 *
	 * @param cityInfo 都市情報DTO
	 * @return 処理成功のメッセージ
	 */
	@PostMapping(value = "/city")
	@ResponseBody
	public RestMsg saveCityInfo(@RequestBody final CityInfo cityInfo) {
		final City city = this.saveAndUpdate(cityInfo);
		this.cityRepository.save(city);
		return RestMsg.success();
	}

	/**
	 * 選択された都市情報を削除する
	 *
	 * @param id 都市ID
	 * @return 処理成功のメッセージ
	 */
	@DeleteMapping(value = "/city/{id}")
	@ResponseBody
	public RestMsg deleteCityInfo(@PathVariable("id") final Integer id) {
		this.cityRepository.removeById(id);
		return RestMsg.success();
	}

	/**
	 * 大陸情報を取得する
	 *
	 * @return 大陸名称のリスト
	 */
	@GetMapping(value = "/continents")
	@ResponseBody
	public RestMsg getContinents() {
		final List<String> list = this.countryRepository.findAllContinents();
		return RestMsg.success().add("continentList", list);
	}

	/**
	 * 指定された大陸に位置するすべての国を取得する
	 *
	 * @param continentVal 大陸名称
	 * @return 国のリスト
	 */
	@GetMapping(value = "/nations")
	@ResponseBody
	public RestMsg getListOfNationsById(@RequestParam("continentVal") final String continentVal) {
		final List<String> nationList = Lists.newArrayList();
		final List<Country> nations = this.countryRepository.findNationsByCnt(continentVal);
		nations.forEach(item -> nationList.add(item.getName()));
		return RestMsg.success().add("nationList", nationList);
	}

	/**
	 * 入力した都市名を重複かどうかをチェックする
	 *
	 * @param cityName 都市名称
	 * @return 処理成功のメッセージ
	 */
	@GetMapping(value = "/check")
	@ResponseBody
	public RestMsg checkName(@RequestParam("cityName") final String cityName) {
		final String regex = "^[a-zA-Z-\\p{IsWhiteSpace}]{4,17}$";
		if (cityName.matches(regex)) {
			final City city = new City();
			city.setName(cityName);
			final ExampleMatcher matcher = ExampleMatcher.matching()
					.withStringMatcher(ExampleMatcher.StringMatcher.EXACT).withIgnoreCase(true)
					.withMatcher(cityName, GenericPropertyMatchers.exact())
					.withIgnorePaths("id", "countryCode", "district", "population", "isDeleted");
			final Example<City> example = Example.of(city, matcher);
			final List<City> lists = this.cityRepository.findAll(example);
			if (!lists.isEmpty()) {
				return RestMsg.failure().add("validatedMsg", "入力した都市名が重複する。");
			} else {
				return RestMsg.success();
			}
		} else {
			return RestMsg.failure().add("validatedMsg", "入力した都市名は4桁から23桁までのローマ字にしなければなりません。");
		}
	}

	private City saveAndUpdate(final CityInfo cityInfo) {
		final City city = new City();
		BeanUtils.copyProperties(cityInfo, city, "continent", "nation");
		final String nationName = cityInfo.getNation();
		final Country nation = this.countryRepository.findNationCode(nationName);
		final String nationCode = nation.getCode();
		city.setCountryCode(nationCode);
		city.setIsDeleted(0);
		return city;
	}
}