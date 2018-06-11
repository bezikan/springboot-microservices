package com.oceanboa.stock.stockservice.config;

//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class Config {

    //need for eureka service
    @LoadBalanced  //go to service registry and resolve it
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
