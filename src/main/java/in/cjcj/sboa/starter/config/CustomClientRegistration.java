package in.cjcj.sboa.starter.config;

import lombok.Data;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

import java.util.Collections;
import java.util.Set;

@Data
public class CustomClientRegistration {
    private String registrationId;
    private String clientId;
    private String clientSecret;
    private Set<String> scopes = Collections.emptySet();
    private String tokenUri;

    public ClientRegistration clientRegistration() {
        return ClientRegistration
                .withRegistrationId(registrationId)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .scope(scopes)
                .tokenUri(tokenUri)
                .build();
    }
}
