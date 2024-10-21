package com.solid.login.service.logger;

import com.solid.login.modelo.Logs;
import com.solid.login.repository.LoggerBdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoggerBdService {

//    private final LoggerBdRepository loggerBdRepository;
    @Autowired
    public LoggerBdRepository loggerBdRepository;

//    @Autowired
//    public LoggerBdService( LoggerBdRepository loggerBdRepository ){
//        this.loggerBdRepository = loggerBdRepository;
//    }

    public List<Logs> getLogs() throws Exception{
        return loggerBdRepository.findAllByOrderIdDesc();
    }
}
