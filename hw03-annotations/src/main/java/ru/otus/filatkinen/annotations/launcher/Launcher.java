package ru.otus.filatkinen.annotations.launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S2629", "java:S3776", "java:S3011"})
public class Launcher {

    private static class MethodInstance {
        Method method;

        public MethodInstance(Method method) {
            this.method = method;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

    private final List<MethodInstance> before;
    private final List<MethodInstance> after;
    private final List<MethodInstance> methods;
    protected boolean failConfiguration;

    private final Class<?> classLaunch;

    private int testCount;
    private int testSuccessful;
    private int testFall;

    public Launcher(Class<?> cl) {
        this.classLaunch = cl;
        before = new ArrayList<>();
        after = new ArrayList<>();
        methods = new ArrayList<>();
        initLauncher();
    }

    private boolean isTestMethodAndFillMethodAnnotations(Method method) {

        if ((method.isAnnotationPresent(Before.class) || method.isAnnotationPresent(After.class))
                && method.isAnnotationPresent(Test.class)) {

            MethodInstance methodInstance = new MethodInstance(method);
            if (method.isAnnotationPresent(Before.class)) {
                before.add(methodInstance);
            } else {
                after.add(methodInstance);
            }
            return true;
        }

        if (method.isAnnotationPresent(Test.class)) {
            MethodInstance methodInstance = new MethodInstance(method);

            methods.add(methodInstance);
            return true;
        }
        return false;
    }

    private void initLauncher() {
        for (Method method : classLaunch.getDeclaredMethods()) {
            if (isTestMethodAndFillMethodAnnotations(method)) {
                method.setAccessible(true);
                testCount++;
            }
        }
    }

    private void endLauncher() {

        logger.info("Статистика тестирования:");
        logger.info("Всего методов: {}", testCount);
        logger.info("Выполнено успешно: {}", testSuccessful);
        logger.info("Завершились неудачей: {}", testFall);
    }

    public void launchTest()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if (failConfiguration) {
            logger.info("Ошибка в применении аннтотаций - тестирования не было");
            return;
        }

        int counter;
        for (MethodInstance methodLaunch : methods) {
            Object o = classLaunch.getConstructor().newInstance();
            logger.info("");
            counter = 0;
            for (MethodInstance methodBefore : before) {
                if (counter == 0) {
                    logger.info(">>>>>>>>>@Before<<<<<<<<<");
                    counter++;
                }
                launchMethod(methodBefore.method, o);
            }

            logger.info(">>>>>>>>>>>>>>@Test<<<<<<<<<<<<<<");
            launchMethod(methodLaunch.method, o);

            counter = 0;
            for (MethodInstance methodAfter : after) {
                if (counter == 0) {
                    logger.info(">>>>>>>>>@After<<<<<<<<<");
                    counter++;
                }
                launchMethod(methodAfter.method, o);
            }
        }

        endLauncher();
    }

    private void launchMethod(Method m, Object o) {
        if (m == null) {
            return;
        }
        try {
            m.invoke(o);
        } catch (Exception e) {
            logger.info(String.format("%s %s", "!!!Error -", e));
            testFall++;
        }
        testSuccessful++;
    }
}
