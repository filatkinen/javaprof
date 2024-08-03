package ru.otus.filatkinen.annotations.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.filatkinen.annotations.launcher.*;

@SuppressWarnings({"java:S2133", "java:S2629"})
public class B {
    private static final Logger logger = LoggerFactory.getLogger(B.class);

    public void b01() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Before
    @Test
    public void b05() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @After
    @Test
    private void b06() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @After
    @Test
    public void b07() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test
    private void b08() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test
    private void b09() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test
    private void b10() throws ErrorSimple {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());

        throw new ErrorSimple();
    }

    @Test
    private void b11() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    private void logging(String cl, String method) {
        logger.info(String.format(">>Execute class %s, method %s%n", cl, method));
    }
}
