package jp.co.sony.ppog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * JpaSsmcrudアプリケーション
 *
 * @author Administrator
 * @since 1.00beta
 */
@SpringBootApplication
@ServletComponentScan
public class JpaSsmCrudApplication {

	private static final Logger log = LogManager.getLogger(JpaSsmCrudApplication.class);

	public static void main(final String[] args) {
		SpringApplication.run(JpaSsmCrudApplication.class, args);
		log.info("アプリは正常に起動しました!");
	}
}
