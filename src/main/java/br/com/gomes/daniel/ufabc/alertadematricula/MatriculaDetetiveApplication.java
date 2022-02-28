package br.com.gomes.daniel.ufabc.alertadematricula;

import br.com.gomes.daniel.ufabc.alertadematricula.framework.scheduler.UpdateListaDisciplinas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class})
public class MatriculaDetetiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatriculaDetetiveApplication.class, args);

	}

}
