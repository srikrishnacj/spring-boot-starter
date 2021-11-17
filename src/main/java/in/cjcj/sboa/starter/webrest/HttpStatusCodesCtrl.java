package in.cjcj.sboa.starter.webrest;

import in.cjcj.sboa.starter.response.builder.ResponseBuilder;
import in.cjcj.sboa.starter.util.YmlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@RestController
@ConditionalOnProperty("debug.controller.enableHttpStatusCodesCtrl")
@Api(tags = {"[INTERNAL] Status Codes Endpoint"})
public class HttpStatusCodesCtrl {
    private static final String STATUS_CODE_FILE = "/rest-status-codes.yml";
    private List<Map<String, Object>> statusCodes;

    @PostConstruct
    private void init() {
        try {
            List<Map<String, Object>> _conf = YmlUtil.loadAsList(STATUS_CODE_FILE);
            for (Map<String, Object> map : _conf) {
                if (map.containsKey("whenToUse")) {
                    map.remove("whenToUse");
                }
            }
            this.statusCodes = _conf;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @ApiOperation(value = "returns all the possible status codes used by this API")
    @GetMapping(path = "/internal/http-status-codes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity statusCodes() {
        return ResponseBuilder.okResponse().ok().data(statusCodes).build();
    }
}
