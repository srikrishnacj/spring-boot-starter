package in.cjcj.sboa.starter.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ResourceUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Slf4j
public class YmlUtil {
    public static List<Map<String, Object>> loadAsList(String classPathResource) throws IOException {
        InputStream stream = tryLoadResourceWithMethod1(classPathResource);
        if (stream == null) {
            stream = tryLoadResourceWithMethod2(classPathResource);
        }
        if (stream == null) {
            stream = tryLoadResourceWithMethod3(classPathResource);
        }
        if (stream == null) {
            stream = tryLoadResourceWithMethod4(classPathResource);
        }
        if (stream == null) {
            return new LinkedList<>();
        }

        return (List<Map<String, Object>>) new Yaml().load(stream);
    }

    private static InputStream tryLoadResourceWithMethod1(String classPathResource) {
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource("classpath:" + classPathResource);
            InputStream stream = resource.getInputStream();
            return stream;
        } catch (Exception exception) {
            log.warn("Unable to open classpath resource: {} with method1", classPathResource);
            return null;
        }
    }

    private static InputStream tryLoadResourceWithMethod2(String classPathResource) {
        try {
            File file = ResourceUtils.getFile("classpath:" + classPathResource);
            InputStream stream = new FileInputStream(file);
            return stream;
        } catch (Exception exception) {
            log.warn("Unable to open classpath resource: {} with method2", classPathResource);
            return null;
        }
    }

    private static InputStream tryLoadResourceWithMethod3(String classPathResource) {
        try {
            URL resource = YmlUtil.class.getResource(classPathResource);
            if (resource == null) return null;
            InputStream stream = resource.openStream();
            return stream;
        } catch (IOException e) {
            log.warn("Unable to open classpath resource: {} with method3", classPathResource);
            return null;
        }
    }

    private static InputStream tryLoadResourceWithMethod4(String classPathResource) {
        try {
            URL resource = YmlUtil.class.getClassLoader().getResource(classPathResource);
            if (resource == null) return null;
            InputStream stream = resource.openStream();
            return stream;
        } catch (IOException e) {
            log.warn("Unable to open classpath resource: {} with method4", classPathResource);
            return null;
        }
    }
}
