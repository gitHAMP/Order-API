package com.upc.orderapi.config;

import java.time.format.DateTimeFormatter;

import com.upc.orderapi.converters.OrderConverter;
import com.upc.orderapi.converters.ProductConverter;
import com.upc.orderapi.converters.UserConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConverterConfig {

    @Value("${config.datetimeFormat}")
    private String datetimeFormat;

    @Bean
    public ProductConverter getProductConverter() {
        return new ProductConverter();
    }

    @Bean
    public OrderConverter getOrderConverter() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern(datetimeFormat);
        return new OrderConverter(format, getProductConverter(),getUserConverter());
    }

    @Bean
    public UserConverter getUserConverter(){
        return new UserConverter();
    }

}
