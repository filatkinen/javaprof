package ru.otus.filatkinen.annotations.launcher;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S2629", "java:S3776"})
public class Launcher {

    private static class MethodInstance {
        Method method;
        int priority;

        public MethodInstance(Method method, int priority) {
            this.method = method;
            this.priority = priority;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(Launcher.class);

    private Method beforeSuite;
    private Method afterSuite;
    private List<MethodInstance> before;
    private List<MethodInstance> after;
    private List<MethodInstance> methods;
    private boolean skippedClass;
    protected boolean failConfiguration;

    private final Class<?> classLaunch;

    private int testCount;
    private int testSkipped;
    private int testSuccessful;
    private int testFall;

    public Launcher(Class<?> cl) {
        this.classLaunch = cl;
        before = new ArrayList<>();
        after = new ArrayList<>();
        methods = new ArrayList<>();
        initLauncher();
    }

    private void getMethodAnnotations(Method method) {

        if (method.isAnnotationPresent(Disabled.class)) {
            testSkipped++;
            return;
        }

        if (method.isAnnotationPresent(BeforeSuite.class) && !method.isAnnotationPresent(Test.class)) {
            if (beforeSuite != null) {
                failConfiguration = true;
                logger.info("BeforeSuite встречается более 1-го раза");
                return;
            }
            beforeSuite = method;
            return;
        }
        if (method.isAnnotationPresent(AfterSuite.class) && !method.isAnnotationPresent(Test.class)) {
            if (afterSuite != null) {
                failConfiguration = true;
                logger.info("AfterSuit встречается более 1-го раза");
                return;
            }
            afterSuite = method;
            return;
        }

        if ((method.isAnnotationPresent(Before.class) || method.isAnnotationPresent(After.class))
                && method.isAnnotationPresent(Test.class)) {
            Test test = method.getAnnotation(Test.class);
            if (!(test.priority() >= 1 && test.priority() <= 10)) {
                return;
            }

            MethodInstance methodInstance = new MethodInstance(method, test.priority());
            if (method.isAnnotationPresent(Before.class)) {
                before.add(methodInstance);
            } else {
                after.add(methodInstance);
            }
            return;
        }

        if (method.isAnnotationPresent(Test.class)) {
            Test test = method.getAnnotation(Test.class);
            MethodInstance methodInstance = new MethodInstance(method, test.priority());
            methods.add(methodInstance);
        }
    }

    private void initLauncher() {

        if (classLaunch.isAnnotationPresent(Disabled.class)) {
            skippedClass = true;
            return;
        }

        for (Method method : classLaunch.getDeclaredMethods()) {
            testCount++;
            getMethodAnnotations(method);
        }

        Comparator<MethodInstance> cmp = (x, y) -> {
            if (x.priority == y.priority) {
                return 0;
            }
            return x.priority < y.priority ? 1 : -1;
        };

        methods.sort(cmp);

        before.sort(cmp);
        after.sort(cmp);
    }

    private void endLauncher() {
        logger.info(String.format("%nСтатистика тестирования:"));
        logger.info(String.format("Всего методов: %d%n", testCount));
        logger.info(String.format("Пропущено(Disabled): %d%n", testSkipped));
        logger.info(String.format("Выполнено успешно: %d%n", testSuccessful));
        logger.info(String.format("Завершились неудачей: %d%n", testFall));
    }

    public void launchTest()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (skippedClass) {
            logger.info("Аннотация Disable  была установлена на класс целиком - тестирования не было");
            return;
        }

        if (failConfiguration) {
            logger.info("Ошибка в применении аннтотаций - тестирования не было");
            return;
        }

        Object o = classLaunch.getConstructor().newInstance();

        logger.info(">>>>>>@BeforeSuite<<<<<<");
        launchMethod(beforeSuite, o);

        int counter;
        for (MethodInstance methodLaunch : methods) {
            logger.info("");
            counter = 0;
            for (MethodInstance methodBefore : before) {
                if (counter == 0) {
                    logger.info(">>>>>>>>>@Before<<<<<<<<<");
                    counter++;
                }
                launchMethod(methodBefore.method, o);
            }

            logger.info(String.format("%s %s", ">>>>>>>>>>>>>>@Test<<<<<<<<<<<<<<  priority=", methodLaunch.priority));
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

        logger.info("\n>>>>>>@AfterSuite<<<<<<");
        launchMethod(afterSuite, o);

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
