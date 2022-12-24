package jp.co.sony.ppog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import jp.co.sony.ppog.utils.RestDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

import jp.co.sony.ppog.entity.City;
import jp.co.sony.ppog.entity.CityInfo;
import jp.co.sony.ppog.entity.Nation;
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
    public ModelAndView getCityInfo(@RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum
            , @RequestParam(value = "keyword", defaultValue = "") final String keyword) {
        final PageRequest pageRequest = PageRequest.of(pageNum - 1, 17, Sort.by(Sort.Direction.ASC, "id"));
        Page<CityInfo> dtoPage;
        if (StringUtils.isNotEmpty(keyword)) {
            final CityInfo cityInfo = new CityInfo();
            cityInfo.setName(keyword);
            final ExampleMatcher matcher = ExampleMatcher.matching()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                    .withIgnoreCase(true)
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

    /**
     * Search the selected city's name.
     *
     * @param id the ID of city
     * @return modelAndView
     */
    @GetMapping(value = "/city/{id}")
    public String getCityInfo(@PathVariable("id") final Long id, final Model model) {
        final Optional<CityInfo> cityOp = this.cityInfoDao.findById(id);
        final CityInfo cityDto = cityOp.get();
        model.addAttribute("cityInfo", cityDto);
        return "cityInfo";
    }

    /**
     * Save the input messages.
     *
     * @param cityDto the input message of cities
     * @return RestMsg.success()
     */
    @PostMapping(value = "/city")
    public ModelAndView saveCityInfo(@Valid final CityInfo cityDto, final BindingResult result) {
        final Map<String, Object> map = Maps.newHashMap();
        final City city = new City();
        final ModelAndView mav = new ModelAndView("index");
        if (result.hasErrors()) {
            final List<FieldError> fieldErrors = result.getFieldErrors();
            for (final FieldError fieldError : fieldErrors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return mav.addObject("errorFields", map);
        } else {
            BeanUtils.copyProperties(cityDto, city, "continent", "nation");
            final Nation nation = new Nation();
            nation.setName(cityDto.getName());
            final Example<Nation> nationExample = Example.of(nation);
            final List<Nation> nations = this.nationDao.findAll(nationExample);
            city.setCountryCode(nations.get(0).getCode());
        }
        city.setIsDeleted(0);
        this.cityDao.save(city);
        return mav;
    }

//    /**
//     * Delete the selected city info.
//     *
//     * @param id the ID of city
//     * @return RestMsg.success()
//     */
//    @DeleteMapping(value = "/city/{id}")
//    public RestMsg deleteCityInfo(@PathVariable("id") final Long id) {
//        cityDtoService.deleteCityInfo(id);
//        return RestMsg.success();
//    }
//
//    /**
//     * Check the input city name already existed or not.
//     *
//     * @param cityName the input name
//     * @return RestMsg.success()
//     */
//    @GetMapping(value = "/checklist")
//    public RestMsg checkCityName(@RequestParam("cityName") final String cityName) {
//        final String regex = "^[a-zA-Z_-]{4,17}$";
//        if (cityName.matches(regex)) {
//            if (this.cityDtoService.checkDuplicated(cityName)) {
//                return RestMsg.failure().add("validatedMsg", "City name is duplicate.");
//            } else {
//                return RestMsg.success();
//            }
//        } else {
//            return RestMsg.failure().add("validatedMsg", "Name of cities should be in 4~17 Latin alphabets.");
//        }
//    }
//
//    /**
//     * Get list of continents.
//     *
//     * @return RestMsg.success().add(data)
//     */
//    @GetMapping(value = "/continents")
//    public RestMsg getListOfContinents() {
//        final List<CityDto> list = this.cityDtoService.getContinents();
//        return RestMsg.success().add("continents", list);
//    }
//
//    /**
//     * Get list of nations.
//     *
//     * @return RestMsg.success().add(data)
//     */
//    @GetMapping(value = "/nations")
//    public RestMsg getListOfNations(@RequestParam("continentVal") final String continent) {
//        final List<CityDto> list = this.cityDtoService.getNations(continent);
//        return RestMsg.success().add("nations", list);
//    }
}