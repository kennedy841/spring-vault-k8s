package ch.corner.lab.vault.vaultexampleweb.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Component
@Slf4j
//@Profile("enabledb")
@Import(DataSourceAutoConfiguration.class)
public class DatabaseConfig {

    public static final String URL = "jdbc:mysql://localhost/carcomparator?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    void init(){
        log.info("username {} password {}", username, password);
    }


}
