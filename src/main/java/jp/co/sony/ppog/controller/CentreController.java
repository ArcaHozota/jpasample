package jp.co.sony.ppog.controller;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import jp.co.sony.ppog.entity.CityView;
import jp.co.sony.ppog.entity.Country;
import jp.co.sony.ppog.service.CityViewService;
import jp.co.sony.ppog.utils.RestMsg;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 中央処理コントローラ
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/ssmcrud")
public class CentreController {

    @Resource
    private CityViewService cityViewService;

    /**
     * 都市情報を検索する
     *
     * @return modelAndView
     */
    @GetMapping(value = "/city")
    public ModelAndView getCityInfo(@RequestParam(value = "pageNum", defaultValue = "1") final Integer pageNum,
                                    @RequestParam(value = "keyword", defaultValue = "") final String keyword) {
        // 聲明分頁構造器；
        final Page<CityView> pageInfo = Page.of(pageNum, 17);
        // 聲明條件構造器；
        final LambdaQueryWrapper<CityView> queryWrapper = Wrappers.lambdaQuery(new CityView());
        // 添加過濾條件；
        queryWrapper.like(StringUtils.isNotEmpty(keyword), CityView::getName, keyword);
        // 添加排序條件；
        queryWrapper.orderByAsc(CityView::getId);
        // 執行查詢；
        this.cityViewService.page(pageInfo, queryWrapper);
        // modelAndViewオブジェクトを宣言する；
        final ModelAndView mav = new ModelAndView("index");
        // 前のページを取得する；
        final long current = pageInfo.getCurrent();
        // ページングナビゲーションの数を定義する；
        final int naviNums = 7;
        // ページングナビの最初と最後の数を取得する；
        final int pageFirstIndex = (int) ((current / naviNums) * naviNums + 1);
        int pageLastIndex = (int) ((current / naviNums + 1) * naviNums);
        if (pageLastIndex > pageInfo.getPages()) {
            pageLastIndex = (int) (pageInfo.getPages());
        } else {
            pageLastIndex = (int) ((current / naviNums + 1) * naviNums);
        }
        mav.addObject("pageInfo", pageInfo);
        mav.addObject("keyword", keyword);
        mav.addObject("pageFirstIndex", pageFirstIndex);
        mav.addObject("pageLastIndex", pageLastIndex);
        mav.addObject("title", "CityList");
        return mav;
    }

    @GetMapping(value = "/city/{id}")
    @ResponseBody
    public RestMsg getCityInfo(@PathVariable("id") final Long id) {
        final CityView cityInfo = this.cityViewService.getById(id);
        return RestMsg.success().add("citySelected", cityInfo);
    }

    @GetMapping(value = "/nations/{id}")
    @ResponseBody
    public RestMsg getListOfNationsById(@PathVariable("id") final Long id) {
        final List<String> list = Lists.newArrayList();
        final CityView cityView = this.cityViewService.getById(id);
        final String nationName = cityView.getNation();
        list.add(nationName);
        final String continent = cityView.getContinent();
        final List<CityView> nations = this.cityViewService.getNations(continent);
        nations.forEach(item -> {
            if (!nationName.equals(item.getNation())) {
                list.add(item.getNation());
            }
        });
        return RestMsg.success().add("nationsWithName", list);
    }
}