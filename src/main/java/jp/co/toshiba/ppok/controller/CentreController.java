package jp.co.toshiba.ppok.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.google.common.collect.Maps;
import jp.co.toshiba.ppok.entity.City;
import jp.co.toshiba.ppok.entity.CityDto;
import jp.co.toshiba.ppok.entity.Nation;
import jp.co.toshiba.ppok.repository.CityDao;
import jp.co.toshiba.ppok.repository.CityViewDao;
import jp.co.toshiba.ppok.repository.NationDao;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Center Terminal Controller Handle the retrieve and update requests.
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/jpasmcrud")
public class CentreController {

    @Resource
    private CityDao cityDao;

    @Resource
    private NationDao nationDao;

    @Resource
    private CityViewDao cityViewDao;

    /**
     * Retrieve the city data.
     *
     * @return modelAndView
     */
    @GetMapping(value = "/city")
    public ModelAndView getCityInfo(@RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum) {
        PageHelper.startPage(pageNum, 15);
        final List<CityDto> list = this.cityViewDao.findAll();
        final PageInfo<CityDto> pageInfo = PageInfo.of(list, 7);
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("title", "CityList");
        final int totalPages = pageInfo.getPages();
        if (pageNum == 1) {
            final int prePage = 1;
            pageInfo.setPrePage(prePage);
        } else if (pageNum == totalPages) {
            pageInfo.setNextPage(totalPages);
        }
        mav.addObject("pageInfo", pageInfo);
        return mav;
    }

    /**
     * Search the selected city's name.
     *
     * @param id the ID of city
     * @return modelAndView
     */
    @GetMapping(value = "/city/{id}")
    public ModelAndView getCityInfo(@PathVariable("id") final Long id) {
        final Optional<CityDto> cityOp = this.cityViewDao.findById(id);
        CityDto cityDto = null;
        if (cityOp.isPresent()) {
            cityDto = cityOp.get();
        }
        final ModelAndView mav = new ModelAndView("index");
        mav.addObject("cityInfo", cityDto);
        return mav;
    }

    /**
     * Save the input messages.
     *
     * @param cityDto the input message of cities
     * @return RestMsg.success()
     */
    @PostMapping(value = "/city")
    public ModelAndView saveCityInfo(@Valid final CityDto cityDto, final BindingResult result) {
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