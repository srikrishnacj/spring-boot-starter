package in.cjcj.sboa.starter.config.filters;

import in.cjcj.sboa.starter.props.DevelopmentProps;
import in.cjcj.sboa.starter.util.StringFormatter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
public class CustomServletRequestLoggingFilter extends CommonsRequestLoggingFilter {

    private final DevelopmentProps.Logging.Servlet loggingConfig;

    public CustomServletRequestLoggingFilter(DevelopmentProps.Logging.Servlet loggingConfig) {
        this.loggingConfig = loggingConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        ContentCachingResponseWrapper response = new ContentCachingResponseWrapper((HttpServletResponse) servletResponse);

        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            super.doFilterInternal(request, response, filterChain);
        } finally {
            stopWatch.stop();

            StringFormatter msg = new StringFormatter();
            msg.line().decorator();
            this.printBeforeRequest(request, filterChain, msg);
            this.printAfterRequest(request, response, filterChain, msg);
            msg.line("Response Time: " + stopWatch.getTotalTimeMillis() + "ms");
            msg.decorator();
            log.info(msg.toString());
            response.copyBodyToResponse();
        }
    }

    private void printBeforeRequest(HttpServletRequest request, FilterChain filterChain, StringFormatter msg) {
        msg.header("BEFORE SERVLET REQUEST");

        msg.line("[" + request.getMethod() + " " + request.getRequestURI() + "]");

        Set<DevelopmentProps.Logging.LoggingOptions> loggingConfigRequest = this.loggingConfig.getRequest();

        if (loggingConfigRequest.contains(DevelopmentProps.Logging.LoggingOptions.QUERY_PARAMS)) {
            this.printQueryParameters(request, msg);
        }
        if (loggingConfigRequest.contains(DevelopmentProps.Logging.LoggingOptions.HEADER)) {
            this.printRequestHeaders(request, msg);
        }
        if (loggingConfigRequest.contains(DevelopmentProps.Logging.LoggingOptions.BODY)) {
            this.printRequestBody(request, msg);
        }
    }

    private void printQueryParameters(HttpServletRequest request, StringFormatter msg) {
        String queryString = request.getQueryString();
        if (StringUtils.isNotEmpty(queryString)) {
            try {
                msg.line("Query Parameters: ");
                Map<String, String[]> queryParameters = new HashMap<>();
                queryString = URLDecoder.decode(queryString, StandardCharsets.UTF_8.toString());
                String[] parameters = queryString.split("&");
                for (String parameter : parameters) {
                    String[] keyValuePair = parameter.split("=");
                    String[] values = queryParameters.get(keyValuePair[0]);
                    //length is one if no value is available.
                    values = keyValuePair.length == 1 ? ArrayUtils.add(values, "") :
                            ArrayUtils.addAll(values, keyValuePair[1].split(",")); //handles CSV separated query param values.
                    queryParameters.put(keyValuePair[0], values);
                }
                for (String key : queryParameters.keySet()) {
                    String value = msg.values(queryParameters.get(key));
                    msg.subline(msg.value(key, value));
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private void printRequestHeaders(HttpServletRequest request, StringFormatter msg) {
        msg.line("Request Headers: ");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            msg.subline(msg.value(key, value));
        }
    }

    private void printRequestBody(HttpServletRequest request, StringFormatter msg) {
        msg.line("Request Body: ");
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        String theString = new String(wrapper.getContentAsByteArray());
        msg.line(theString);
    }

    private void printAfterRequest(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, StringFormatter msg) {
        msg.header("AFTER SERVLET REQUEST");

        msg.line("[" + response.getStatus() + " " + request.getMethod() + " " + request.getRequestURI() + "]");

        Set<DevelopmentProps.Logging.LoggingOptions> loggingConfigResponse = this.loggingConfig.getResponse();

        if (loggingConfigResponse.contains(DevelopmentProps.Logging.LoggingOptions.HEADER)) {
            this.printResponseHeaders(response, msg);
        }
        if (loggingConfigResponse.contains(DevelopmentProps.Logging.LoggingOptions.BODY)) {
            this.printResponseBody(response, msg);
        }
    }

    private void printResponseHeaders(HttpServletResponse response, StringFormatter msg) {
        msg.line("Response Headers: ");
        Collection<String> headerNames = response.getHeaderNames();
        Iterator<String> iterator = headerNames.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = response.getHeader(key);
            if (key.startsWith("Vary")) continue;
            msg.line(1, msg.value(key, value));
        }
    }

    private void printResponseBody(HttpServletResponse response, StringFormatter msg) {
        msg.line("Response Body: ");
        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        String theString = new String(wrapper.getContentAsByteArray());
        msg.line(theString);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

    }

    /**
     * Writes a log message after the request is processed.
     */
    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

    }
}
