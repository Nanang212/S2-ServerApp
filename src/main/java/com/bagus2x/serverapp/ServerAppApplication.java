package com.bagus2x.serverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServerAppApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServerAppApplication.class, args);
		HelloController bean = context.getBean(HelloController.class);
		System.out.println("\nServer App is running...\n" + bean);
    }
}
