package org.idoroiengel.testappmvn.application;

import org.idoroiengel.testappmvn.config.ConfigProperties;
import org.idoroiengel.testappmvn.interfaces.JsonService;
import org.idoroiengel.testappmvn.service.JsonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Scanner;

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperties.class)
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Autowired
    @Qualifier("jsonInput")
    private JsonService jsonService = new JsonServiceImpl();

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConsoleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("enter your json: ");
        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        String data = "";
        while(scanner.hasNext()) {
            builder.append(scanner.nextLine());
            data = builder.toString();
            if(data.contains("}")){
                break;
            }
        }
        scanner.close();

        jsonService.parse(builder.toString());

    }
}
