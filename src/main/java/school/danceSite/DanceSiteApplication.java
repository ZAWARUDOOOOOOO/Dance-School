package school.danceSite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)//параметр отключает спринг секьюрити
public class DanceSiteApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DanceSiteApplication.class, args);
    }
}
