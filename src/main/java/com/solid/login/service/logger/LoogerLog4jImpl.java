package com.solid.login.service.logger;

import com.solid.login.interfaces.ILogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class LoogerLog4jImpl implements ILogger {

    //    Logger logger = LogManager.getLogger(this.getClass());
    private static final Logger log = LogManager.getLogger(LoogerLog4jImpl.class);

    @Override
    public void info(Class classs, String msg) {
        LogManager.getLogger(classs).info(msg);
    }

    @Override
    public void debug(Class classs, String msg) {
        LogManager.getLogger(classs).debug(msg);
    }

    @Override
    public void warn(Class classs, String msg) {
        LogManager.getLogger(classs).warn(msg);
    }

    @Override
    public void error(Class classs, String msg) {
        LogManager.getLogger(classs).error(msg);
    }
}
