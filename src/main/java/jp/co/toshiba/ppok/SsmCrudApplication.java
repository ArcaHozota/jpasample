package jp.co.toshiba.ppok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.extern.slf4j.Slf4j;

/**
 * ssmcrud application
 *
 * @author Administrator
 * @date 2022-12-12
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
public class SsmCrudApplication {
	public static void main(final String[] args) {
		SpringApplication.run(SsmCrudApplication.class, args);
		log.info("アプリは正常に起動しました!");
	}
}
