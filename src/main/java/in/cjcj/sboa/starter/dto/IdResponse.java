package in.cjcj.sboa.starter.dto;


import in.cjcj.sboa.starter.util.BeanUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@Getter
@Setter
@Slf4j
public class IdResponse<T> {
    private T id;

    private IdResponse() {
    }

    public IdResponse(T id) {
        Assert.notNull(id, "ID Should not be null");
        this.id = id;
    }

    public static IdResponse from(Object obj) {
        return (IdResponse) BeanUtil.copyProperties(new IdResponse(), obj);
    }
}
