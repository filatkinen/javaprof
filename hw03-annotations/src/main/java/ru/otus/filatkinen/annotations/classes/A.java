package ru.otus.filatkinen.annotations.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.filatkinen.annotations.launcher.*;

@SuppressWarnings({"java:S2133", "java:S2629"})
public class A {
    private static final Logger logger = LoggerFactory.getLogger(A.class);

    public void a01() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test
    public void a03() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Before
    @Test
    public void a05() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @After
    @Test
    public void a06() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @After
    @Test
    public void a07() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test
    public void a08() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test
    public void a09() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test
    public void a10() throws ErrorSimple {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());

        throw new ErrorSimple();
    }

    @Test
    public void a11() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    private void logging(String cl, String method) {
        logger.info(String.format(">>Execute class %s, method %s%n", cl, method));
    }
}
