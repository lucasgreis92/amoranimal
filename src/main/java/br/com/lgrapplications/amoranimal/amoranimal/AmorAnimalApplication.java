package br.com.lgrapplications.amoranimal.amoranimal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableSpringDataWebSupport
@EnableScheduling
public class AmorAnimalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmorAnimalApplication.class, args);
    }

}
