package com.solid.login.service.logger;

import com.solid.login.interfaces.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LoggerApiImpl implements ILogger {

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private LoggerApiService log;

    private String formatear(String dir, String nivel, String msg) {
        Date date = new Date();
        String fechaHora = dateFormat.format(date);
        return fechaHora + " " + nivel + " " + dir + " " + msg;
    }

    @Override
    public void info(Class classs, String msg) {
        log.send( formatear( classs.getName(), "INFO", msg ) );
    }

    @Override
    public void debug(Class classs, String msg) {
        log.send( formatear( classs.getCanonicalName(), "DEBUG", msg ) );
    }

    @Override
    public void warn(Class classs, String msg) {
        log.send( formatear( classs.getCanonicalName(), "WARN", msg ) );
    }

    @Override
    public void error(Class classs, String msg) {
        log.send( formatear( classs.getCanonicalName(), "ERROR", msg ) );
    }
}
