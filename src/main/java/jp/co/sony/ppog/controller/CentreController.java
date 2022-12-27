package jp.co.sony.ppog.controller;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jp.co.sony.ppog.entity.CityView;
import jp.co.sony.ppog.service.CityViewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 中央処理コントローラ
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/jpassmcrud")
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
        final int pageFirstIndex = (int) ((current / naviNums) * naviNums);
        int pageLastIndex = (int) ((current / naviNums + 1) * naviNums);
        if (pageLastIndex > pageInfo.getPages() - 1) {
            pageLastIndex = (int) (pageInfo.getPages() - 1);
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
}