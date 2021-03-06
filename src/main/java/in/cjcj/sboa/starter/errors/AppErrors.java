package in.cjcj.sboa.starter.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppErrors {
    private ErrorDetail error;
    private List<ErrorDetail> subErrors = new LinkedList<>();

    private Throwable exception;

    public boolean hasErrors() {
        return error != null || this.subErrors.size() > 0;
    }

    public void validate() {
    }
}
