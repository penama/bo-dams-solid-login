package com.solid.login.interfaces;

import com.solid.login.modelo.Logs;
import com.solid.login.modelo.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping( "/api/v1/loggersbd" )
public interface ILoggerBdRest {

    @GetMapping("/")
    public ResponseEntity<List<Logs>> getLogs();

}
