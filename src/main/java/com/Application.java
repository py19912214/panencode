package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * TODO
 *
 * @author: yuepan
 * @date: 2018/9/21
 */
@Controller
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
