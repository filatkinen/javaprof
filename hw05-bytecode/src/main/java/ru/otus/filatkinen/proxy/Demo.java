package ru.otus.filatkinen.proxy;

import java.lang.reflect.Proxy;

public class Demo {
    public static void main(String[] args) {
        TestLoggingInterface original = new TestLogging();
        TestLoggingInterface proxyInstance = (TestLoggingInterface) Proxy.newProxyInstance(
                TestLogging.class.getClassLoader(),
                TestLogging.class.getInterfaces(),
                new LogInvocationHandler(original));

        proxyInstance.calculation(6);
        proxyInstance.calculation(7, 8);
        proxyInstance.calculation(9, 10, "example");
    }
}
