package org.example.patterns.proxy.impl;

import org.example.patterns.proxy.SomeServiceProxy;
import org.example.patterns.proxy.api.LoggerService;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerServiceImpl implements LoggerService {
    private static final Logger logger = Logger.getLogger(SomeServiceProxy.class.getName());
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS dd-MM-yyyy");

    @Override
    public void doLogIfItNeeds(String pattern, String nameClass, String nameMethod, Class<? extends Annotation> nameAnnotation) {
        try {
            Class<?> aClass = Class.forName(nameClass);
            Method[] declaredMethods = aClass.getDeclaredMethods();

            Method method = Arrays.stream(declaredMethods)
                    .filter(m -> m.getName().equals(nameMethod))
                    .findFirst()
                    .orElseThrow(NoSuchMethodException::new);

            Class<?> returnType = method.getReturnType();

            Class<?>[] parameterTypes = method.getParameterTypes();
            int parametersAmount = parameterTypes.length;
            StringBuilder builder = new StringBuilder(String.format("\nMethod contains %s parameter(-s): ", parametersAmount));

            for (Class<?> type : parameterTypes) {
                builder.append(type.getName()).append(", ");
            }

            int length = builder.length();
            builder.replace(length - 2, length, ".");
            builder.append("\nReturning type: ").append(returnType);

            if (method.isAnnotationPresent(nameAnnotation)) {
                if (method.isAnnotationPresent(nameAnnotation)) {
                    logger.info(String.format(pattern + builder, method.getName(), nameClass, LocalDateTime.now().format(formatter)));
                }
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            logger.log(Level.SEVERE, "Exception: ", e);
        }
    }

}
