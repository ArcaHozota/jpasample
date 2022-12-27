package jp.co.sony.ppog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jp.co.sony.ppog.entity.Country;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountryMapper extends BaseMapper<Country> {
}
