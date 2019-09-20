package ch.corner.lab.vault.vaultexampleweb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Slf4j
public class VaultExampleWebApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(VaultExampleWebApplication.class, args);

    }

}
