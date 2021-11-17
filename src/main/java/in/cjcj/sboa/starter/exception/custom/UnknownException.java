package in.cjcj.sboa.starter.exception.custom;


import in.cjcj.sboa.starter.errors.AppErrors;
import in.cjcj.sboa.starter.errors.ErrorBuilder;
import in.cjcj.sboa.starter.errors.ErrorDetail;
import in.cjcj.sboa.starter.exception.base.InternalServerException;

public class UnknownException extends InternalServerException {
    public UnknownException(AppErrors appErrors) {
        super(appErrors);
    }

    public UnknownException(ErrorDetail errorDetail) {
        super(errorDetail);
    }

    public UnknownException(ErrorBuilder errorBuilder) {
        super(errorBuilder);
    }
}