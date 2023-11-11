package org.example.patterns.proxy.impl;

import org.example.patterns.proxy.annotations.Log;
import org.example.patterns.proxy.api.SomeService;

import java.util.concurrent.ThreadLocalRandom;

public class SomeServiceImpl implements SomeService {
    @Override
    @Log
    public void doSmtUseful() {
        for (int i = 0; i < 10; i++) {
            System.out.println(ThreadLocalRandom.current().nextInt());
        }
    }

    @Override
    @Log
    public void doSmtWithParameter(int a, String abc) {
        for (int i = 0; i < 10; i++) {
            System.out.println(a + abc);
        }
    }

    @Override
    @Log
    public double doSmtAndReturnValue(double a, double b) {
        return a + b;
    }
}
