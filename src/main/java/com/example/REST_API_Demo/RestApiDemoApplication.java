package com.example.REST_API_Demo;

import com.example.REST_API_Demo.controller.MyRestController;
import com.example.REST_API_Demo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestApiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiDemoApplication.class, args);

        MyRestController myRestController = new MyRestController(new RestTemplate());

        System.out.println(myRestController.getAllUsers());
        System.out.println(myRestController.addNewUser(new User(3L, "James", "Brown", (byte) 50)));
        System.out.println(myRestController.updateUser(new User(3L, "Thomas", "Shelby", (byte) 50)));
        System.out.println(myRestController.deleteUser(3L));
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
