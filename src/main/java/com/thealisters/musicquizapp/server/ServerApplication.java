package com.thealisters.musicquizapp.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(ServerApplication.class, args);

        logger.info("\uD83C\uDFB5 Music Quiz App Server Ready And API Available For Requests \uD83C\uDFB5");
    }
}
