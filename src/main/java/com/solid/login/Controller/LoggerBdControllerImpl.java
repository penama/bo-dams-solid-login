package com.solid.login.Controller;

import com.solid.login.interfaces.ILoggerBdRest;
import com.solid.login.modelo.Logs;
import com.solid.login.modelo.Usuario;
import com.solid.login.service.logger.LoggerBdService;
import com.solid.login.service.logger.LoggerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoggerBdControllerImpl implements ILoggerBdRest {

    @Autowired
    public LoggerBdService loggerBdService;

    @Autowired
    public LoggerManager log;

    @Override
    public ResponseEntity<List<Logs>> getLogs() {
        try {
            return new ResponseEntity<List<Logs>>(loggerBdService.getLogs(), HttpStatus.OK);
        } catch (Exception e) {
            log.error( this.getClass(), e.getMessage() + " " + e.getLocalizedMessage()  );
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
