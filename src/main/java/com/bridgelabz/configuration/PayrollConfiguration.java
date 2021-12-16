package com.bridgelabz.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/********************************************************************************************************
 * Purpose: This class is to add the configurations that are needed for this project.
 *
 * @author Ashwith
 * @since 11/12/21
 *******************************************************************************************************/
@Configuration
public class PayrollConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
