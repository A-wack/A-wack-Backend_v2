package org.awack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AwackV2Application {

    public static void main(String[] args) {
        SpringApplication.run(AwackV2Application.class, args);
    }

}
