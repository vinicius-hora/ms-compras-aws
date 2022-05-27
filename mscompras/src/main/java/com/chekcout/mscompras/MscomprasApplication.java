package com.chekcout.mscompras;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class MscomprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscomprasApplication.class, args);
	}

}
