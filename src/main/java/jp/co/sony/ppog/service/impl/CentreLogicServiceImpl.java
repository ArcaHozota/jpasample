package jp.co.sony.ppog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import jp.co.sony.ppog.dto.CityDto;
import jp.co.sony.ppog.entity.City;
import jp.co.sony.ppog.entity.CityInfo;
import jp.co.sony.ppog.repository.CityInfoRepository;
import jp.co.sony.ppog.repository.CityRepository;
import jp.co.sony.ppog.repository.CountryRepository;
import jp.co.sony.ppog.service.CentreLogicService;
import jp.co.sony.ppog.utils.Messages;
import jp.co.sony.ppog.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * 中央処理サービス実装クラス
 *
 * @author ArcaHozota
 * @since 4.40
 */
@Service
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CentreLogicServiceImpl implements CentreLogicService {

	/**
	 * ページサイズ
	 */
	private static final Integer PAGE_SIZE = 12;

	/**
	 * デフォルトソート値
	 */
	private static final Integer SORT_NUMBER = 100;

	/**
	 * 都市リポジトリ
	 */
	private final CityRepository cityRepository;

	/**
	 * 都市情報リポジトリ
	 */
	private final CityInfoRepository cityInfoRepository;

	/**
	 * 国家リポジトリ
	 */
	private final CountryRepository countryRepository;

	@Override
	public CityDto getCityInfoById(final Long id) {
		final CityInfo cityInfo = this.cityInfoRepository.findById(id).orElseGet(CityInfo::new);
		return new CityDto(cityInfo.getId(), cityInfo.getName(), cityInfo.getContinent(), cityInfo.getNation(),
				cityInfo.getDistrict(), cityInfo.getPopulation(), cityInfo.getLanguage());
	}

	@Override
	public Page<CityDto> getPageInfo(final Integer pageNum, final String keyword) {
		// ページングコンストラクタを宣言する；
		final PageRequest pageRequest = PageRequest.of(pageNum - 1, PAGE_SIZE, Sort.by(Direction.ASC, "id"));
		// キーワードの属性を判断する；
		if (StringUtils.isNotEmpty(keyword)) {
			final String hankakuKeyword = StringUtils.toHankaku(keyword);
			final int pageMin = PAGE_SIZE * (pageNum - 1);
			final int pageMax = PAGE_SIZE * pageNum;
			int sort = SORT_NUMBER;
			if (hankakuKeyword.startsWith("min(pop)")) {
				final int indexOf = hankakuKeyword.indexOf(")");
				final String keisan = hankakuKeyword.substring(indexOf + 1);
				if (StringUtils.isNotEmpty(keisan)) {
					sort = Integer.parseInt(keisan);
				}
				// 人口数量昇順で最初の15個都市の情報を吹き出します；
				final List<CityDto> minimumRanks = this.cityInfoRepository.findMinimumRanks(sort).stream()
						.map(item -> new CityDto(item.getId(), item.getName(), item.getContinent(), item.getNation(),
								item.getDistrict(), item.getPopulation(), item.getLanguage()))
						.toList();
				if (pageMax >= sort) {
					return new PageImpl<>(minimumRanks.subList(pageMin, sort), pageRequest, minimumRanks.size());
				}
				return new PageImpl<>(minimumRanks.subList(pageMin, pageMax), pageRequest, minimumRanks.size());
			}
			if (hankakuKeyword.startsWith("max(pop)")) {
				final int indexOf = hankakuKeyword.indexOf(")");
				final String keisan = hankakuKeyword.substring(indexOf + 1);
				if (StringUtils.isNotEmpty(keisan)) {
					sort = Integer.parseInt(keisan);
				}
				// 人口数量降順で最初の15個都市の情報を吹き出します；
				final List<CityDto> maximumRanks = this.cityInfoRepository.findMaximumRanks(sort).stream()
						.map(item -> new CityDto(item.getId(), item.getName(), item.getContinent(), item.getNation(),
								item.getDistrict(), item.getPopulation(), item.getLanguage()))
						.toList();
				if (pageMax >= sort) {
					return new PageImpl<>(maximumRanks.subList(pageMin, sort), pageRequest, maximumRanks.size());
				}
				return new PageImpl<>(maximumRanks.subList(pageMin, pageMax), pageRequest, maximumRanks.size());
			}
			// ページング検索；
			final CityInfo cityInfo = new CityInfo();
			final String nationCode = this.countryRepository.findNationCode(hankakuKeyword);
			if (StringUtils.isNotEmpty(nationCode)) {
				cityInfo.setNation(hankakuKeyword);
				final Example<CityInfo> example = Example.of(cityInfo, ExampleMatcher.matching());
				final Page<CityInfo> pages = this.cityInfoRepository.findAll(example, pageRequest);
				return this.getCityInfoDtos(pages, pageRequest, pages.getTotalElements());
			}
			cityInfo.setName(hankakuKeyword);
			final ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name",
					GenericPropertyMatchers.contains());
			final Example<CityInfo> example = Example.of(cityInfo, matcher);
			final Page<CityInfo> pages = this.cityInfoRepository.findAll(example, pageRequest);
			return this.getCityInfoDtos(pages, pageRequest, pages.getTotalElements());
		}
		// ページング検索；
		final Page<CityInfo> pages = this.cityInfoRepository.findAll(pageRequest);
		return this.getCityInfoDtos(pages, pageRequest, pages.getTotalElements());
	}

	@Override
	public List<String> getListOfNationsById(final Long id) {
		final List<String> list = new ArrayList<>();
		final City city = this.cityRepository.findById(id).orElseGet(City::new);
		final String nationName = city.getCountry().getName();
		list.add(nationName);
		final List<String> nations = this.countryRepository.findNationsByCnt(city.getCountry().getContinent()).stream()
				.filter(item -> StringUtils.isNotEqual(item, nationName)).collect(Collectors.toList());
		list.addAll(nations);
		return list;
	}

	@Override
	public void update(final CityDto cityDto) {
		final City city = this.cityRepository.findById(cityDto.id()).orElseGet(City::new);
		final String countryCode = this.countryRepository.findNationCode(cityDto.nation());
		city.setCountryCode(countryCode);
		city.setName(cityDto.name());
		city.setDistrict(cityDto.district());
		city.setPopulation(cityDto.population());
		this.cityRepository.save(city);
	}

	@Override
	public void save(final CityDto cityDto) {
		final String countryCode = this.countryRepository.findNationCode(cityDto.nation());
		final Long saiban = this.cityRepository.saiban();
		final City city = new City();
		city.setId(saiban);
		city.setName(cityDto.name());
		city.setCountryCode(countryCode);
		city.setDistrict(cityDto.district());
		city.setPopulation(cityDto.population());
		city.setDeleteFlg(Messages.MSG007);
		this.cityRepository.save(city);
	}

	@Override
	public void removeById(final Long id) {
		this.cityRepository.removeById(id);
	}

	@Override
	public List<String> findAllContinents() {
		return this.countryRepository.findAllContinents();
	}

	@Override
	public List<String> findNationsByCnt(final String continentVal) {
		final String hankaku = StringUtils.toHankaku(continentVal);
		return this.countryRepository.findNationsByCnt(hankaku);
	}

	@Override
	public String findLanguageByCty(final String nationVal) {
		return this.cityInfoRepository.getLanguage(nationVal);
	}

	@Override
	public List<City> checkDuplicate(final String cityName) {
		final City city = new City();
		city.setName(StringUtils.toHankaku(cityName));
		city.setDeleteFlg(Messages.MSG007);
		final Example<City> example = Example.of(city, ExampleMatcher.matchingAll());
		return this.cityRepository.findAll(example);
	}

	private Page<CityDto> getCityInfoDtos(final Page<CityInfo> pages, final Pageable pageable, final Long total) {
		final List<CityDto> cityDtos = pages.getContent().stream().map(item -> new CityDto(item.getId(), item.getName(),
				item.getContinent(), item.getNation(), item.getDistrict(), item.getPopulation(), item.getLanguage()))
				.toList();
		return new PageImpl<>(cityDtos, pageable, total);
	}
}
