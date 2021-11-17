package in.cjcj.sboa.starter.exception.base;

import in.cjcj.sboa.starter.errors.AppErrors;
import in.cjcj.sboa.starter.errors.CommonResourceErrorCode;
import in.cjcj.sboa.starter.errors.ErrorBuilder;
import in.cjcj.sboa.starter.errors.ErrorDetail;
import in.cjcj.sboa.starter.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED, reason = CommonResourceErrorCode.Fields.NOT_IMPLEMENTED)
public class NotImplementedException extends AppException {
    public NotImplementedException(AppErrors appErrors) {
        super(appErrors);
    }
    public NotImplementedException(ErrorDetail errorDetail) {
        super(errorDetail);
    }
    public NotImplementedException(ErrorBuilder errorBuilder) {
        super(errorBuilder);
    }
}
