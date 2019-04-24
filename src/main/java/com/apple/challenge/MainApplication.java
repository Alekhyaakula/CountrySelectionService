package com.apple.challenge;

import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.apple.challenge")
public class MainApplication {

    public static void main(String[] args) {

        // Startup class to execute the Consumer
        SpringApplication application = new SpringApplication(MainApplication.class);


        // Turn off Spring boot Banner
        application.setBannerMode(Banner.Mode.OFF);

        LoggerFactory.getLogger(MainApplication.class).info("Going to run the application now!!!!");

        // Finally Run
        application.run(args);
    }
}