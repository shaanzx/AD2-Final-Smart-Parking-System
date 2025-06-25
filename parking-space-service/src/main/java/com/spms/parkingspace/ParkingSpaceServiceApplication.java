package com.spms.parkingspace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ParkingSpaceServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkingSpaceServiceApplication.class, args);
    }
}