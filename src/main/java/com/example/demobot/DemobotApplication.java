package com.example.demobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class DemobotApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemobotApplication.class, args);
        ArrayList<Integer> arr = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        arr.sort((a, b) -> {
            if (a.equals(b)) {
                return 0;
            }

            return a > b ? -1 : 1;
        });

        System.out.println(arr);
    }
}
