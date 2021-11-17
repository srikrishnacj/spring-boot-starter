package in.cjcj.sboa.starter.util;

import in.cjcj.sboa.starter.exception.custom.BeanPropertiesCopyException;
import org.springframework.beans.BeanUtils;

public class BeanUtil {
    public static Object copyProperties(Object dest, Object orig) {
        try {
            BeanUtils.copyProperties(dest, orig);
            return dest;
        } catch (Exception e) {
            BeanPropertiesCopyException exception = new BeanPropertiesCopyException();
            exception.initCause(e);
            throw exception;
        }
    }
}
