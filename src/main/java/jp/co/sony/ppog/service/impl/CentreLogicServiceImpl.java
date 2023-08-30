package jp.co.sony.ppog.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jp.co.sony.ppog.dto.CityInfoDto;
import jp.co.sony.ppog.entity.City;
import jp.co.sony.ppog.entity.CityView;
import jp.co.sony.ppog.entity.Language;
import jp.co.sony.ppog.repository.CityRepository;
import jp.co.sony.ppog.repository.CityViewRepository;
import jp.co.sony.ppog.repository.CountryRepository;
import jp.co.sony.ppog.repository.LanguageRepository;
import jp.co.sony.ppog.service.CentreLogicService;
import jp.co.sony.ppog.utils.StringUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * 中央処理サービス実装クラス
 *
 * @author Administrator
 */
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CentreLogicServiceImpl implements CentreLogicService {

	/**
	 * 都市リポジトリ
	 */
	private final CityRepository cityRepository;

	/**
	 * 都市情報リポジトリ
	 */
	private final CityViewRepository cityViewRepository;

	/**
	 * 国家リポジトリ
	 */
	private final CountryRepository countryRepository;

	/**
	 * 言語リポジトリ
	 */
	private final LanguageRepository languageRepository;

	@Override
	public CityInfoDto getCityInfoById(final Long id) {
		final CityView cityView = this.cityViewRepository.getById(id);
		final CityInfoDto cityInfoDto = new CityInfoDto();
		BeanUtils.copyProperties(cityView, cityInfoDto);
		final String language = this.findLanguageByCty(cityInfoDto.getNation());
		cityInfoDto.setLanguage(language);
		return cityInfoDto;
	}

	@Override
	public Page<CityInfoDto> getPageInfo(final Integer pageNum, final String keyword) {
		// ページングコンストラクタを宣言する；
		final PageRequest pageRequest = PageRequest.of(pageNum - 1, 17);
		// キーワードの属性を判断する；
		if (StringUtils.isNotEmpty(keyword)) {
			// ページング検索；
			final CityView cityView = new CityView();
			cityView.setNation(keyword);
			final ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("nation",
					GenericPropertyMatchers.exact());
			final Example<CityView> example = Example.of(cityView, matcher);
			final List<CityView> findByNations = this.cityViewRepository.findAll(example);
			if (!findByNations.isEmpty()) {
				return getCityInfoDtos(this.cityViewRepository.findAll(example, pageRequest));
			}
			if (StringUtils.isEqual("min(pop)", keyword)) {
				// 人口数量昇順で最初の15個都市の情報を吹き出します；
				final List<CityInfoDto> minimumRanks = this.cityViewRepository.findMinimumRanks().stream().map(item -> {
					final CityInfoDto cityInfoDto = new CityInfoDto();
					BeanUtils.copyProperties(item, cityInfoDto);
					final String language = this.findLanguageByCty(item.getNation());
					cityInfoDto.setLanguage(language);
					return cityInfoDto;
				}).collect(Collectors.toList());
				return new PageImpl<>(minimumRanks);
			} else if (StringUtils.isEqual("max(pop)", keyword)) {
				// 人口数量降順で最初の15個都市の情報を吹き出します；
				final List<CityInfoDto> maximumRanks = this.cityViewRepository.findMaximumRanks().stream().map(item -> {
					final CityInfoDto cityInfoDto = new CityInfoDto();
					BeanUtils.copyProperties(item, cityInfoDto);
					final String language = this.findLanguageByCty(item.getNation());
					cityInfoDto.setLanguage(language);
					return cityInfoDto;
				}).collect(Collectors.toList());
				return new PageImpl<>(maximumRanks);
			} else {
				cityView.setName("%" + keyword + "%");
				final ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("name",
						GenericPropertyMatchers.exact());
				final Example<CityView> example2 = Example.of(cityView, exampleMatcher);
				// ページング検索；
				return getCityInfoDtos(this.cityViewRepository.findAll(example2, pageRequest));
			}
		}
		// ページング検索；
		return getCityInfoDtos(this.cityViewRepository.findAll(pageRequest));
	}

	@Override
	public List<String> getListOfNationsById(final Long id) {
		final List<String> list = new ArrayList<>();
		final CityView cityView = this.cityViewRepository.getById(id);
		final List<String> nations = this.countryRepository.findNationsByCnt(cityView.getContinent());
		final String nationName = cityView.getNation();
		list.add(nationName);
		final List<String> collect = nations.stream().filter(item -> StringUtils.isNotEqual(item, nationName))
				.collect(Collectors.toList());
		list.addAll(collect);
		return list;
	}

	@Override
	public void update(final CityInfoDto cityInfoDto) {
		final City city = new City();
		BeanUtils.copyProperties(cityInfoDto, city, "continent", "nation", "language");
		final String nationName = cityInfoDto.getNation();
		final String nationCode = this.countryRepository.findNationCode(nationName);
		city.setCountryCode(nationCode);
		city.setLogicDeleteFlg("visible");
		this.cityRepository.save(city);
	}

	@Override
	public void save(final CityInfoDto cityInfoDto) {
		final City city = new City();
		BeanUtils.copyProperties(cityInfoDto, city, "continent", "nation", "language");
		final String nationName = cityInfoDto.getNation();
		final String nationCode = this.countryRepository.findNationCode(nationName);
		city.setCountryCode(nationCode);
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
		return this.countryRepository.findNationsByCnt(continentVal);
	}

	@Override
	public String findLanguageByCty(final String nationVal) {
		final String nationCode = this.countryRepository.findNationCode(nationVal);
		final Specification<Language> specification1 = (root, query, criteriaBuilder) -> criteriaBuilder
				.equal(root.get("countryCode"), nationCode);
		final Specification<Language> specification2 = (root, query, criteriaBuilder) -> {
			query.orderBy(criteriaBuilder.desc(root.get("percentage")));
			return criteriaBuilder.equal(root.get("logicDeleteFlg"), "visible");
		};
		final Specification<Language> languageSpecification = Specification.where(specification1).and(specification2);
		final List<Language> languages = this.languageRepository.findAll(languageSpecification);
		if (languages.size() == 1) {
			return languages.get(0).getName();
		}
		final List<Language> officialLanguages = languages.stream()
				.filter(al -> StringUtils.isEqual("True", al.getIsOfficial())).collect(Collectors.toList());
		final List<Language> typicalLanguages = languages.stream()
				.filter(al -> StringUtils.isEqual("False", al.getIsOfficial())).collect(Collectors.toList());
		if (officialLanguages.isEmpty() && !typicalLanguages.isEmpty()) {
			return typicalLanguages.get(0).getName();
		} else if (!officialLanguages.isEmpty() && typicalLanguages.isEmpty()) {
			return officialLanguages.get(0).getName();
		} else {
			final Language language1 = officialLanguages.get(0);
			final Language language2 = typicalLanguages.get(0);
			if (language2.getPercentage().subtract(language1.getPercentage()).compareTo(BigDecimal.valueOf(35L)) <= 0) {
				return language1.getName();
			} else {
				return language2.getName();
			}
		}
	}

	@Override
	public List<City> checkDuplicate(final String cityName) {
		final City city = new City();
		city.setName(cityName);
		final ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase(true)
				.withMatcher("name", GenericPropertyMatchers.exact())
				.withIgnorePaths("id", "countryCode", "district", "population", "isDeleted");
		final Example<City> example = Example.of(city, matcher);
		return this.cityRepository.findAll(example);
	}

	private Page<CityInfoDto> getCityInfoDtos(final Page<CityView> pages) {
		final List<CityInfoDto> findByNames = pages.getContent().stream().map(item -> {
			final CityInfoDto cityInfoDto = new CityInfoDto();
			BeanUtils.copyProperties(item, cityInfoDto);
			final String language = this.findLanguageByCty(item.getNation());
			cityInfoDto.setLanguage(language);
			return cityInfoDto;
		}).collect(Collectors.toList());
		return new PageImpl<>(findByNames);
	}
}
