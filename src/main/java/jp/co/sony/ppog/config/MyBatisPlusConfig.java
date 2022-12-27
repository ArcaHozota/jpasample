package jp.co.sony.ppog.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatisPlus Pagination
 *
 * @author Administrator
 */
@Configuration
public class MyBatisPlusConfig {

	/**
	 * configuration for MBPlus intercepter
	 *
	 * @return mybatisPlusInterceptor
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		final MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
		final PaginationInnerInterceptor innerInterceptor = new PaginationInnerInterceptor();
		innerInterceptor.setDbType(DbType.POSTGRE_SQL);
		innerInterceptor.setOverflow(true);
		mybatisPlusInterceptor.addInnerInterceptor(innerInterceptor);
		return mybatisPlusInterceptor;
	}
}
