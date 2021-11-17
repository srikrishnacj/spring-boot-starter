package in.cjcj.sboa.starter.errors;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ErrorDetail {

    String getCode();

    String getMessage();

    String getDeveloperMsg();

    String toString();
}
