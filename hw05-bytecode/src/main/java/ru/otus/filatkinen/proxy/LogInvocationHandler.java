package ru.otus.filatkinen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S2629"})
public class LogInvocationHandler implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(LogInvocationHandler.class);
    private final Object target;
    private final List<MethodArgs> methodArgs;

    private static class MethodArgs {
        String name;
        Class<?>[] types;

        public MethodArgs(String name, Class<?>[] types) {
            this.name = name;
            this.types = types;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MethodArgs that = (MethodArgs) o;
            return Objects.equals(name, that.name) && Objects.deepEquals(types, that.types);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, Arrays.hashCode(types));
        }
    }

    public LogInvocationHandler(Object target) {
        this.target = target;
        methodArgs = new ArrayList<>();
        Method[] ms = target.getClass().getDeclaredMethods();
        for (Method m : ms) {
            if (m.isAnnotationPresent(Log.class)) {
                methodArgs.add(new MethodArgs(m.getName(), m.getParameterTypes()));
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (methodArgs.contains(new MethodArgs(method.getName(), method.getParameterTypes()))) {
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
