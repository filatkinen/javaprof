package ru.otus.filatkinen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S2629"})
public class LogInvocationHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(LogInvocationHandler.class);
    private final Object target;
    private final Set<Method> methods;

    public LogInvocationHandler(Object target) {
        this.target = target;
        methods = new HashSet<>();
        Collections.addAll(methods, target.getClass().getInterfaces()[0].getMethods());
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methods.contains(method)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("executed method: ");
            stringBuilder.append(method.getName());
            stringBuilder.append(", param: ");

            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    stringBuilder.append(args[i]);
                    if (i < args.length - 1) {
                        stringBuilder.append(", ");
                    }
                }
            }

            logger.info(stringBuilder.toString());
        }
        return method.invoke(target, args);
    }
}
