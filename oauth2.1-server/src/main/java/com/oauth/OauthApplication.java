package com.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 伍六七
 * @date 2023/6/4 18:51
 */
@SpringBootApplication
public class OauthApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(OauthApplication.class);
        springApplication.run(args);
    }
}
