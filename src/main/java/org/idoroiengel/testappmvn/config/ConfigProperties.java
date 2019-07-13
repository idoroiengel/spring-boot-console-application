package org.idoroiengel.testappmvn.config;

import org.idoroiengel.testappmvn.interfaces.JsonService;
import org.idoroiengel.testappmvn.model.InputNumbersJson;
import org.idoroiengel.testappmvn.service.JsonServiceImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@ComponentScan("org.idoroiengel.testappmvn.*")
public class ConfigProperties {

    @Bean
    public JsonService jsonInput(){
        return new JsonServiceImpl();
    }

    @Bean
    public InputNumbersJson inputNumbersJson(){
        return new InputNumbersJson();
    }

}
