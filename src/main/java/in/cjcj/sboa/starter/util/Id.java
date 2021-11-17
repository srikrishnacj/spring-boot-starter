package in.cjcj.sboa.starter.util;

import com.fasterxml.uuid.Generators;
import org.apache.commons.lang3.RandomStringUtils;

/*
    Will be useful if we are representing object id as string as opposed to integers.
    This class will generate time series based UUIDs. Will help database indexing faster.
    String Id are common in document databases.
 */
public class Id {
    private static final int SHORT_ID_LENGTH = 8;

    public static String id() {
        return Generators.timeBasedGenerator().generate().toString();
    }

    public static String id(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static String shortId() {
        String shortId = id(SHORT_ID_LENGTH);
        return shortId;
    }

    public static String testId() {
        String id = id();
        id = id.substring(5, id.length());
        id = "TEST-" + id;
        return id;
    }

    public static String testShortId() {
        String shortId = shortId();
        shortId = shortId.substring(2, shortId.length());
        shortId = "T-" + shortId;
        return shortId;
    }
}
