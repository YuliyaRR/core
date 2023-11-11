package org.example.patterns.proxy;

import org.example.patterns.proxy.api.SomeService;
import org.example.patterns.proxy.impl.SomeServiceImpl;

public class SomeServiceFactory {
    public static SomeService getInstance() {
        return new SomeServiceProxy(new SomeServiceImpl());
    }
}
