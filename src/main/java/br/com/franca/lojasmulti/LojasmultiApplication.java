package br.com.franca.lojasmulti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class LojasmultiApplication {

    public static void main(String[] args) {

        SpringApplication.run(LojasmultiApplication.class, args);
    }
}