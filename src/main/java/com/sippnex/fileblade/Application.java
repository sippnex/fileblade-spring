package com.sippnex.fileblade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFileblade
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
