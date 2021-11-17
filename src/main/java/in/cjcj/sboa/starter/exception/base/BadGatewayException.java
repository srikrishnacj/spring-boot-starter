package in.cjcj.sboa.starter.exception.base;

import in.cjcj.sboa.starter.errors.AppErrors;
import in.cjcj.sboa.starter.errors.CommonResourceErrorCode;
import in.cjcj.sboa.starter.errors.ErrorBuilder;
import in.cjcj.sboa.starter.errors.ErrorDetail;
import in.cjcj.sboa.starter.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_GATEWAY, reason = CommonResourceErrorCode.Fields.UPSTREAM_ERROR)
public class BadGatewayException extends AppException {
    public BadGatewayException(AppErrors appErrors) {
        super(appErrors);
    }

    public BadGatewayException(ErrorDetail errorDetail) {
        super(errorDetail);
    }

    public BadGatewayException(ErrorBuilder errorBuilder) {
        super(errorBuilder);
    }
}
