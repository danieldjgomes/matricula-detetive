package br.com.gomes.daniel.ufabc.alertadematricula.framework.configuracao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Configuration
@Service
public class ConfiguracaoDeBanco {

    @Value("${general.connections.jdbc}")
    private String URL_BANCO;


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(URL_BANCO);
        return dataSource;
    }


}
