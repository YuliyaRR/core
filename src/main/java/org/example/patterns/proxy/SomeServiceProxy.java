package org.example.patterns.proxy;

import org.example.patterns.proxy.annotations.Log;
import org.example.patterns.proxy.api.LoggerService;
import org.example.patterns.proxy.api.SomeService;
import org.example.patterns.proxy.impl.LoggerServiceImpl;
import org.example.patterns.proxy.impl.SomeServiceImpl;

public class SomeServiceProxy implements SomeService {
    private SomeService realService;
    private final LoggerService loggerService;
    private static final String LOG_PATTERN_START = "The %s method, owned by %s, started at %s";
    private static final String LOG_PATTERN_FINISH = "The %s method, owned by %s, finished at %s";


    public SomeServiceProxy(SomeServiceImpl realService) {
        this.realService = realService;
        this.loggerService = new LoggerServiceImpl();
    }

    @Override
    public void doSmtUseful(){
        loggerService.doLogIfItNeeds(LOG_PATTERN_START, realService.getClass().getName(),"doSmtUseful", Log.class);
        realService.doSmtUseful();
        loggerService.doLogIfItNeeds(LOG_PATTERN_FINISH, realService.getClass().getName(),"doSmtUseful", Log.class);
    }

    @Override
    public void doSmtWithParameter(int a, String abc) {
        loggerService.doLogIfItNeeds(LOG_PATTERN_START, realService.getClass().getName(),"doSmtWithParameter", Log.class);
        realService.doSmtWithParameter(a, abc);
        loggerService.doLogIfItNeeds(LOG_PATTERN_FINISH, realService.getClass().getName(),"doSmtWithParameter", Log.class);
    }

    @Override
    public double doSmtAndReturnValue(double a, double b) {
        loggerService.doLogIfItNeeds(LOG_PATTERN_START, realService.getClass().getName(),"doSmtAndReturnValue", Log.class);
        double value = realService.doSmtAndReturnValue(a, b);
        loggerService.doLogIfItNeeds(LOG_PATTERN_FINISH, realService.getClass().getName(),"doSmtAndReturnValue", Log.class);

        return value;
    }
}
