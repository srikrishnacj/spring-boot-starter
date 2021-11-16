package in.cjcj.sboa.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
        OAuth2ResourceServerAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        OAuth2ClientAutoConfiguration.class,
//        ManagementWebSecurityAutoConfiguration.class
})
//@SpringBootApplication
public class MyNewBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyNewBootApplication.class, args);
    }

}
