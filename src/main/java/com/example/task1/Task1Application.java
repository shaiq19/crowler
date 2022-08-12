package com.example.task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Task1Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Task1Application.class, args);
        WebCrowler webCrowler =  new WebCrowler();
//        webCrowler.apifetch();
        webCrowler.HistoricData();
    }

}
