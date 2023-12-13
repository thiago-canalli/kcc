package com.tcms.kcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KccApplication {

	public static void main(String[] args) {
		SpringApplication.run(KccApplication.class, args);
	}

}
