package in.cjcj.sboa.starter.config;

import in.cjcj.sboa.starter.props.RestTemplateProps;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class RestTemplateConfig {
    private final String REGISTRATION_PREFIX = "oauthresttemplate.registrations.";
    private final RestTemplateProps restTemplateProps;
    private final Environment environment;

    public RestTemplateConfig(RestTemplateProps restTemplateProps, Environment environment) {
        this.restTemplateProps = restTemplateProps;
        this.environment = environment;
    }

    @Bean
    public OAuth2AuthorizedClientManager clientManager() {
        ClientRegistrationRepository registrationRepository = clientRegistrationRepository();
        OAuth2AuthorizedClientService clientService = new InMemoryOAuth2AuthorizedClientService(registrationRepository);

        OAuth2AuthorizedClientProvider authorizedClientProvider =
                OAuth2AuthorizedClientProviderBuilder
                        .builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(registrationRepository, clientService);
        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    private ClientRegistrationRepository clientRegistrationRepository() {
        List<ClientRegistration> clientRegistrations = new LinkedList<>();
        Binder binder = Binder.get(environment);
        for (String registrationName : restTemplateProps.getRegistrations().keySet()) {
            String name = REGISTRATION_PREFIX + registrationName;
            CustomClientRegistration registration = binder.bind(name, CustomClientRegistration.class).get();
            registration.setRegistrationId(name);
            clientRegistrations.add(registration.clientRegistration());
        }
        return new InMemoryClientRegistrationRepository(clientRegistrations);
    }
}
