package in.cjcj.sboa.starter.exception.base;

import in.cjcj.sboa.starter.errors.AppErrors;
import in.cjcj.sboa.starter.errors.ErrorBuilder;
import in.cjcj.sboa.starter.errors.ErrorDetail;
import in.cjcj.sboa.starter.exception.AppException;

public class InternalServerException extends AppException {
    public InternalServerException(AppErrors appErrors) {
        super(appErrors);
    }
    public InternalServerException(ErrorDetail errorDetail) {
        super(errorDetail);
    }
    public InternalServerException(ErrorBuilder errorBuilder) {
        super(errorBuilder);
    }
}
