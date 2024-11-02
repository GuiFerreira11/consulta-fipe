package com.example.consultafipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.consultafipe.main.Main;

@SpringBootApplication
public class ConsultafipeApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(ConsultafipeApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    Main main = new Main();
    main.exibeMenu();

  }

}
