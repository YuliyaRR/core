package org.example.patterns.proxy;

import org.example.patterns.proxy.api.SomeService;

public class Main {
    public static void main(String[] args) {
        SomeService someService = SomeServiceFactory.getInstance();

        someService.doSmtUseful();

        someService.doSmtWithParameter(15, "abc");

        System.out.println(someService.doSmtAndReturnValue(12.32, 147.21));
    }
}
