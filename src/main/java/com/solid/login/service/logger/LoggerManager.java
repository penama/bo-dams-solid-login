package com.solid.login.service.logger;

import com.solid.login.interfaces.ILogger;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerManager {

    @Autowired
    private LoggerBdImpl loggerBd;

    @Autowired
    private LoogerLog4jImpl loogerLog4j;

    @Autowired
    private LoggerApiImpl loggerApi;

    private ILogger iLogger;

    @PostConstruct
    public void initLogger() {
        setTipoLogger("log4j");
    }

    public void setTipoLogger(String tipo) {
        if ( iLogger != null )
            iLogger.info( this.getClass(), "Cambiando el tipo de logger antes: " + iLogger.getClass() );
        if (tipo.equals("log4j"))
            iLogger = loogerLog4j;
        else if (tipo.equals("bd"))
            iLogger = loggerBd;
        else if (tipo.equals("api"))
            iLogger = loggerApi;
        else
            iLogger.info( this.getClass(), "no aplica cambio... ");
        iLogger.info( this.getClass(), "Cambiando el tipo de logger despues: " + iLogger.getClass() );
    }

    public void info(Class classs, String msg) {
        iLogger.info(classs, msg);
    }

    public void debug(Class classs, String msg) {
        iLogger.debug(classs, msg);
    }

    public void warn(Class classs, String msg) {
        iLogger.warn(classs, msg);
    }

    public void error(Class classs, String msg) {
        iLogger.error(classs, msg);
    }
}
