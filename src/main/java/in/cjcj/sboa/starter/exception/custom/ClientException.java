package in.cjcj.sboa.starter.exception.custom;

import in.cjcj.sboa.starter.errors.AppErrors;
import in.cjcj.sboa.starter.errors.ErrorBuilder;
import in.cjcj.sboa.starter.errors.ErrorDetail;
import in.cjcj.sboa.starter.exception.base.BadRequestException;

public class ClientException extends BadRequestException {
    public ClientException(AppErrors appErrors) {
        super(appErrors);
    }
    public ClientException(ErrorDetail errorDetail) {
        super(errorDetail);
    }
    public ClientException(ErrorBuilder errorBuilder) {
        super(errorBuilder);
    }
}
