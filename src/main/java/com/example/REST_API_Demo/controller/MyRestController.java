package com.example.REST_API_Demo.controller;

import com.example.REST_API_Demo.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "http://91.241.64.178:7081/api/users")
public class MyRestController {

    private final RestTemplate restTemplate;
    private HttpHeaders headers;

    public MyRestController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "")
    public String getAllUsers() {
        headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        List<String> cookies = restTemplate.getForEntity("http://91.241.64.178:7081/api/users", String.class)
                .getHeaders().get("Set-Cookie");

        System.out.println(cookies);
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        return restTemplate.exchange("http://91.241.64.178:7081/api/users",
                HttpMethod.GET, new HttpEntity<>(headers), String.class).getBody();
    }

    @PostMapping(value = "")
    public String addNewUser(@RequestBody User user) {
        return restTemplate.exchange("http://91.241.64.178:7081/api/users",
                HttpMethod.POST, new HttpEntity<>(user, headers), String.class).getBody();
    }

    @PutMapping("")
    public String updateUser(@RequestBody User user) {
        return restTemplate.exchange("http://91.241.64.178:7081/api/users",
                HttpMethod.PUT, new HttpEntity<>(user, headers), String.class).getBody();
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return restTemplate.exchange("http://91.241.64.178:7081/api/users/" + id,
                HttpMethod.DELETE, new HttpEntity<>(headers), String.class).getBody();
    }
}
