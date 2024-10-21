package com.solid.login.service.logger;

import com.solid.login.service.logger.commom.ResponseLoggerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class LoggerApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${loggerapi.url}")
    private String loggerapiUrl;

    public void send( String msg ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String url = loggerapiUrl.replace( "{msg}", msg );
        ResponseEntity<ResponseLoggerApi> response = restTemplate.getForEntity( url, ResponseLoggerApi.class );
        System.out.println( "SEND API | status=" + response.getStatusCode() + ", body=" + response.getBody().toString() );
    }

}
