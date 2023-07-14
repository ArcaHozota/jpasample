package jp.co.sony.ppog;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * JpaSsmcrudアプリケーション
 *
 * @author Administrator
 * @since 1.00beta
 */
@Log4j2
@SpringBootApplication
@ServletComponentScan
public class JpaSsmCrudApplication {

    public static void main(final String[] args) {
        SpringApplication.run(JpaSsmCrudApplication.class, args);
        log.info("アプリは正常に起動しました!");
    }
}
