package ru.otus.filatkinen.annotations.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.filatkinen.annotations.launcher.*;

@SuppressWarnings({"java:S2133", "java:S2629"})
public class A {
    private static final Logger logger = LoggerFactory.getLogger(A.class);

    @Disabled
    public void a01() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @BeforeSuite
    public void a02() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test(priority = 10)
    public void a03() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @AfterSuite
    public void a04() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Before
    @Test(priority = 10)
    public void a05() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @After
    @Test(priority = 7)
    public void a06() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @After
    @Test(priority = 4)
    public void a07() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test(priority = 8)
    public void a08() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test(priority = 1)
    public void a09() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test(priority = 9)
    public void a10() throws ErrorSimple {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());

        throw new ErrorSimple();
    }

    @Test(priority = 8)
    public void a11() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    private void logging(String cl, String method) {
        logger.info(String.format(">>Execute class %s, method %s%n", cl, method));
    }
}
