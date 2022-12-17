package jp.co.toshiba.ppok.repository;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.toshiba.ppok.entity.City;

@Mapper
public interface CityDao extends BaseMapper<City> {
}
