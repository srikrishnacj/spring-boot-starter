package in.cjcj.sboa.starter.util;

import io.github.classgraph.*;

import java.util.List;

public class AnnotationUtil {
    public static String findPackageNameForClassWithAnnotation(Class cls) {
        try (ScanResult scanResult =
                     new ClassGraph()
                             .verbose()               // Log to stderr
                             .enableAllInfo()         // Scan classes, methods, fields, annotations
                             .acceptPackages("")     // Scan com.xyz and subpackages (omit to scan all packages)
                             .scan()) {               // Start the scan
            for (ClassInfo routeClassInfo : scanResult.getClassesWithAnnotation(cls)) {
                if (routeClassInfo.isStandardClass()) {
                    return routeClassInfo.getPackageName();
                }
            }
        }
        return null;
    }
}
