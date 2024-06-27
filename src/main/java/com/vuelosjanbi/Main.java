package com.vuelosjanbi;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vuelosjanbi.systemUser.infraesctructure.adapter.in.SystemUserAdminController;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    SystemUserAdminController systemUserAdminController;

    @Override
    public void run(String... args) throws Exception {

        systemUserAdminController.run(args);

    }

}