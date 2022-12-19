package jp.co.sony.ppog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import lombok.extern.slf4j.Slf4j;

/**
 * jpasmcrud application
 *
 * @author Administrator
 * @date 2022-12-12
 */
@Slf4j
@SpringBootApplication
@ServletComponentScan
public class JpAsmCrudApplication {
	public static void main(final String[] args) {
		SpringApplication.run(JpAsmCrudApplication.class, args);
		log.info("アプリは正常に起動しました!");
	}
}
