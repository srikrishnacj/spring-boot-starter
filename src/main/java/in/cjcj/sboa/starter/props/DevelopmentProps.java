package in.cjcj.sboa.starter.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Data
@Configuration
@ConfigurationProperties("debug")
public class DevelopmentProps {
    private Logging logging;
    private Controller controller;
    private Actuator actuator;
    private Response response;

    @Data
    public static class Actuator {
        private boolean enable;
        private String endpoints;
    }

    @Data
    public static class Controller {
        private boolean enableErrorCodesCtrl;
        private boolean enableHTTPStatusCodesCtrl;
    }

    @Data
    public static class Logging {
        private Servlet servlet;
        private RestTemplate restTemplate;
        private boolean springWebDebug;

        public static enum LoggingOptions {
            NONE, BASIC, QUERY_PARAMS, HEADER, BODY
        }

        @Data
        public static class Servlet {
            private Set<LoggingOptions> request;
            private Set<LoggingOptions> response;
        }

        @Data
        public static class RestTemplate {
            private Set<LoggingOptions> request;
            private Set<LoggingOptions> response;
        }
    }

    @Data
    public static class Response {
        private boolean includeDebugInfo;
        private boolean enableGlobalCors;

        public boolean getIncludeDebugInfo() { return includeDebugInfo; }
        public void setIncludeDebugInfo(boolean value) { this.includeDebugInfo = value; }

        public boolean getEnableGlobalCors() { return enableGlobalCors; }
        public void setEnableGlobalCors(boolean value) { this.enableGlobalCors = value; }
    }
}
