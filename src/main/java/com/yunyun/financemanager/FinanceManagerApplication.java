package com.yunyun.financemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class FinanceManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceManagerApplication.class, args);
    }

}
