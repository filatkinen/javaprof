package ru.otus.filatkinen.annotations.launcher;

import java.lang.reflect.InvocationTargetException;
import org.slf4j.LoggerFactory;
import ru.otus.filatkinen.annotations.classes.A;
import ru.otus.filatkinen.annotations.classes.B;
import ru.otus.filatkinen.annotations.classes.C;

public class Main {
    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        org.slf4j.Logger log = LoggerFactory.getLogger("ru.otus.filatkinen.annotations.main");

        log.info("---Testing Class A----");
        new Launcher(A.class).launchTest();
        log.info("---Testing Class B----");
        new Launcher(B.class).launchTest();
        log.info("---Testing Class C----");
        new Launcher(C.class).launchTest();
    }
}
