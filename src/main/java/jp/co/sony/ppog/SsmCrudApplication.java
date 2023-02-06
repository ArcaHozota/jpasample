package jp.co.sony.ppog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.extern.log4j.Log4j2;

/**
 * ssmcrudアプリケーション
 *
 * @author Administrator
 * @date 2022-12-12
 */
@Log4j2
@SpringBootApplication
@ServletComponentScan
public class SsmCrudApplication {
	public static void main(final String[] args) {
		SpringApplication.run(SsmCrudApplication.class, args);
		log.info("アプリは正常に起動しました!");
	}
}
