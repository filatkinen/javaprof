package ru.otus.filatkinen.annotations.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.filatkinen.annotations.launcher.*;

@SuppressWarnings({"java:S2133", "java:S2629"})
@Disabled
public class C {
    private static final Logger logger = LoggerFactory.getLogger(C.class);

    @Disabled
    //    @ThrowsException(exception = "ArithmeticException")
    public void c01() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @BeforeSuite
    private void c02() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @AfterSuite
    public void c03() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @AfterSuite
    private void c04() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Before
    @Test(priority = 10)
    public void c05() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @After
    @Test(priority = 7)
    private void c06() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @After
    @Test(priority = 4)
    public void c07() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test(priority = 8)
    private void c08() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test(priority = 1)
    private void c09() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    @Test(priority = 9)
    private void c10() throws ErrorSimple {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());

        throw new ErrorSimple();
    }

    @Test(priority = 8)
    private void c11() {
        logging(
                this.getClass().getName(),
                new Object() {}.getClass().getEnclosingMethod().getName());
    }

    private void logging(String cl, String method) {
        logger.info(String.format(">>Execute class %s, method %s%n", cl, method));
    }
}
