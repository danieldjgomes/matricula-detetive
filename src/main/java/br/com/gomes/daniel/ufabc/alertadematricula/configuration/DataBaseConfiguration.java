package br.com.gomes.daniel.ufabc.alertadematricula.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Bean
public class DataSource {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.("com.mysql.cj.jdbc.Driver");
    dataSource.setUsername("mysqluser");
    dataSource.setPassword("mysqlpass");
    dataSource.setUrl(
            "jdbc:mysql://localhost:3306/myDb?createDatabaseIfNotExist=true");

    return dataSource;

}
