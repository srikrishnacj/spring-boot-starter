package in.cjcj.sboa.starter.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Data
@Configuration
@ConfigurationProperties("oauthresttemplate")
public class RestTemplateProps {
    private Map<String, Object> registrations;
}
