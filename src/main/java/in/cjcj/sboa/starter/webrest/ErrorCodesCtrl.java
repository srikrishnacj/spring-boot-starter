package in.cjcj.sboa.starter.webrest;

import in.cjcj.sboa.starter.errors.ErrorDetailResolver;
import in.cjcj.sboa.starter.response.builder.ResponseBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@ConditionalOnProperty("debug.controller.enableErrorCodesCtrl")
@Api(tags = {"[INTERNAL] Error Codes Endpoint"})
public class ErrorCodesCtrl {
    @ApiOperation(value = "returns all the possible error codes and sub error codes that this API can throw")
    @GetMapping(path = "/internal/error-codes")
    public ResponseEntity errorCodes() {
        return ResponseBuilder
                .okResponse()
                .ok()
                .data(ErrorDetailResolver.getAllErrorCodes())
                .build();
    }
}
