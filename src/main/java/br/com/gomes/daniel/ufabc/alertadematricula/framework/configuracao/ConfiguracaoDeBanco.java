package br.com.gomes.daniel.ufabc.alertadematricula.framework.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class ConfiguracaoDeBanco {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        String url = System.getProperty("dbUrl");
        dataSource.setUrl(url);
        return dataSource;
    }


}
