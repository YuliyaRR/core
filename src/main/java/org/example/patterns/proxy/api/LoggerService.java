package org.example.patterns.proxy.api;

import java.lang.annotation.Annotation;

public interface LoggerService {
    void doLogIfItNeeds(String pattern, String nameClass, String nameMethod, Class<? extends Annotation> nameAnnotation);
}
