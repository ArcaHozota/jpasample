package jp.co.toshiba.ppok.repository;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import jp.co.toshiba.ppok.entity.Nation;

@Mapper
public interface NationDao extends BaseMapper<Nation> {
}
