package in.cjcj.sboa.starter.exception.base;

import in.cjcj.sboa.starter.errors.AppErrors;
import in.cjcj.sboa.starter.errors.CommonResourceErrorCode;
import in.cjcj.sboa.starter.errors.ErrorBuilder;
import in.cjcj.sboa.starter.errors.ErrorDetail;
import in.cjcj.sboa.starter.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = CommonResourceErrorCode.Fields.VALIDATION_ERROR)
public class ValidationException extends AppException {
    public ValidationException(AppErrors appErrors) {
        super(appErrors);
    }

    public ValidationException(ErrorDetail errorDetail) {
        super(errorDetail);
    }

    public ValidationException(ErrorBuilder errorBuilder) {
        super(errorBuilder);
    }
}
