package in.cjcj.sboa.starter.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties("oauthresourceserver")
public class OAuthResourceServerProps {
    private boolean enable;
    private String issuerURI;
    private Map<String, String> verifyClaims;
    private Map<String, String> authorityMapping;
    private List<String> whitelistUrls;
}
