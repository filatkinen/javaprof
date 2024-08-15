package ru.otus.filatkinen.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogging implements TestLoggingInterface {
    private static final Logger logger = LoggerFactory.getLogger(TestLogging.class);

    @Override
    @Log
    public void calculation(int param) {
        logger.info("Executing calculation with param:  {}", param);
    }

    @Override
    @Log
    public void calculation(int param1, int param2) {
        logger.info("Executing calculation with param:  {}, param2: {}", param1, param2);
    }

    @Override
    @Log
    public void calculation(int param1, int param2, String param3) {
        logger.info("Executing calculation with param:  {}, param2: {}, param3: {}", param1, param2, param3);
    }
}
