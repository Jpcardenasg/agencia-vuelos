package com.vuelosjanbi;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.vuelosjanbi.plane.infrastructure.adapters.in.PlaneConsoleAdapter;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private PlaneConsoleAdapter planeConsoleAdapter;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the repository type:");
        System.out.println("1. JPA");
        System.out.println("2. MySQL Manual Queries");

        int choice = scanner.nextInt();
        boolean useJpa = (choice == 1);

        planeConsoleAdapter.start(useJpa);
    }
}