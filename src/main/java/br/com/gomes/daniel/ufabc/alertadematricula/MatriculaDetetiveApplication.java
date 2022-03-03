package br.com.gomes.daniel.ufabc.alertadematricula;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableRabbit
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class})
public class MatriculaDetetiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatriculaDetetiveApplication.class, args);

	}

}
