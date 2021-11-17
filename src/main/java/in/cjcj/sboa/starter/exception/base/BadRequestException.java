package in.cjcj.sboa.starter.exception.base;

import in.cjcj.sboa.starter.errors.AppErrors;
import in.cjcj.sboa.starter.errors.CommonResourceErrorCode;
import in.cjcj.sboa.starter.errors.ErrorBuilder;
import in.cjcj.sboa.starter.errors.ErrorDetail;
import in.cjcj.sboa.starter.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = CommonResourceErrorCode.Fields.BAD_REQUEST)
public class BadRequestException extends AppException {
    public BadRequestException(AppErrors appErrors) {
        super(appErrors);
    }

    public BadRequestException(ErrorDetail errorDetail) {
        super(errorDetail);
    }

    public BadRequestException(ErrorBuilder errorBuilder) {
        super(errorBuilder);
    }

}
