@SpringBootApplication(exclude = {
OAuth2ResourceServerAutoConfiguration.class,
SecurityAutoConfiguration.class,
OAuth2ClientAutoConfiguration.class,
})
public class MyNewBootApplication {
public static void main(String[] args) {
SpringApplication.run(MyNewBootApplication.class, args);
}
}