package com.solid.login.service.logger;

import com.solid.login.interfaces.ILogger;
import com.solid.login.modelo.Logs;
import com.solid.login.repository.LoggerBdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LoggerBdImpl implements ILogger {

    @Autowired
    public LoggerBdRepository loggerBdRepository;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String formatear(String dir, String nivel, String msg) {
        Date date = new Date();
        String fechaHora = dateFormat.format(date);
        return fechaHora + " " + nivel + " " + dir + " " + msg;
    }

    @Override
    public void info(Class classs, String msg) {
        Logs log = new Logs();
        log.setLog(formatear(classs.getName(), "INFO", msg));
        loggerBdRepository.save(log);
    }

    @Override
    public void debug(Class classs, String msg) {
        Logs loggerBdEntity = new Logs();
        loggerBdEntity.setLog(formatear(classs.getCanonicalName(), "DEBUG", msg));
        loggerBdRepository.save(loggerBdEntity);
    }

    @Override
    public void warn(Class classs, String msg) {
        Logs loggerBdEntity = new Logs();
        loggerBdEntity.setLog(formatear(classs.getCanonicalName(), "WARN", msg));
        loggerBdRepository.save(loggerBdEntity);
    }

    @Override
    public void error(Class classs, String msg) {
        Logs loggerBdEntity = new Logs();
        loggerBdEntity.setLog(formatear(classs.getCanonicalName(), "ERROR", msg));
        loggerBdRepository.save(loggerBdEntity);
    }
}
