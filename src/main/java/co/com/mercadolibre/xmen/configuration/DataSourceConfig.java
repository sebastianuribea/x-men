package co.com.mercadolibre.xmen.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceConfig {

    @Primary
    @Bean
    DataSource getDataSourceDev() {
        return setProperties("localhost","root","root");//replace with your username and password
    }

    DataSource setProperties(String host,String user, String pwd) {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://" + host + ":3306/xmendb?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true"
                        + "&useLegacyDatetimeCode=false&serverTimezone=UTC")
                .username(user)
                .password(pwd)
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
