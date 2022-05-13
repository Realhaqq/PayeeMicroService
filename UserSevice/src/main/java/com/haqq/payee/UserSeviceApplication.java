package com.haqq.payee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAspectJAutoProxy
public class UserSeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSeviceApplication.class, args);
    }

}
