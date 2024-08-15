package ru.otus.filatkinen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S2629"})
public class LogInvocationHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(LogInvocationHandler.class);

    private final Object target;

    public LogInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target.getClass()
                        .getMethod(method.getName(), method.getParameterTypes())
                        .getAnnotation(Log.class)
                != null) {

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
