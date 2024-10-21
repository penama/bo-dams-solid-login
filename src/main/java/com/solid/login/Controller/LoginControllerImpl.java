package com.solid.login.Controller;

import com.solid.login.interfaces.ILoginRest;
import com.solid.login.modelo.Usuario;
import com.solid.login.service.LoginService;
import com.solid.login.service.logger.LoggerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginControllerImpl implements ILoginRest {

    @Autowired
    private LoggerManager log;

    @Autowired
    private LoginService loginService;

    @Override
    public ResponseEntity autenticar(Usuario usuario) {
        try {
            if (loginService.autenticar(usuario)) {
                log.info(this.getClass(), "usuario " + usuario.getUser() + " autenticado...");
                return new ResponseEntity(HttpStatus.OK);
            }
            log.info(this.getClass(), "usuario " + usuario.getUser() + " NO autenticado...");
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            log.error(this.getClass(), e.getMessage() + " " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> forget(Usuario usuario) {
        try {
            String passAleatorio = loginService.forget( usuario );
            if ( passAleatorio == null ){
                log.warn(this.getClass(), "usuario " + usuario.getUser() + " no existe...");
                return new ResponseEntity<String>( "Usuario " + usuario.getUser() + " no existe...",  HttpStatus.BAD_REQUEST);
            }
            log.info(this.getClass(), "usuario " + usuario.getUser() + " ha olvidado su contraseña, se asignará una aleatoria...");
            return new ResponseEntity<String>( "Su nueva contraseña es " + passAleatorio,  HttpStatus.OK);
        } catch (Exception e) {
            log.error(this.getClass(), e.getMessage() + " " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
