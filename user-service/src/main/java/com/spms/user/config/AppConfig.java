/**
 * Project: AD2-Final-Project
 * Company: Institute of IJSE
 * ---------------------------------------------
 * Author: Shan Jayawardhana
 * Email: shaanz11.11@gmail.com
 * GitHub: https://github.com/shaanzx
 * Created on: 6/24/2025
 */


package com.spms.user.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

