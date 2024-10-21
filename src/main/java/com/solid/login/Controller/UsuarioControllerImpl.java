package com.solid.login.Controller;

import com.solid.login.interfaces.IUsuarioRest;
import com.solid.login.modelo.Usuario;
import com.solid.login.service.logger.LoggerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.solid.login.service.UsuarioService;

import java.util.List;

@RestController
public class UsuarioControllerImpl implements IUsuarioRest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LoggerManager log;

    @Override
    public ResponseEntity<List<Usuario>> getUsuarios() {
        log.info(this.getClass(), "Consultando listado de usuario...");
        try {
            return new ResponseEntity<List<Usuario>>(usuarioService.getUsuarios(), HttpStatus.OK);
        } catch (Exception e) {
            log.error(this.getClass(), e.getMessage() + " " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Usuario> getUsuarioById(Long id) {
        try {
            Usuario usuario = usuarioService.getUsuario(id);
            if (usuario == null) {
                log.warn(this.getClass(), "usuario no existe...");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            log.info(this.getClass(), "Consultando usuario id = " + id + " -> " + usuario.toString());
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            log.error(this.getClass(), e.getMessage() + " " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Usuario> getUsuarioByUser(String user) {
        try {
            Usuario usuario = usuarioService.getUsuario(user);
            if (usuario == null) {
                log.warn(this.getClass(), "usuario no existe...");
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            log.info(this.getClass(), "Consultando usuario user = " + user + " -> " + usuario.toString());
            return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            log.error(this.getClass(), e.getMessage() + " " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> crearUsuario(Usuario usuario) {
        try {
            String response = usuarioService.crearUsuario(usuario);
            log.info(this.getClass(), (response.isEmpty() ? "usuario creado " + usuario.getUser() : response));
            if (response.isEmpty())
                return new ResponseEntity<String>(response, HttpStatus.CREATED);
            else
                return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(this.getClass(), e.getMessage() + " " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> actualizarUsuario(Usuario usuario) {
        try {
            String response = usuarioService.actualizarUsuario(usuario);
            log.info(this.getClass(), "actualizando datos del usuario: " + usuario.toString());
            if (response.isEmpty())
                return new ResponseEntity<String>(response, HttpStatus.ACCEPTED);
            else
                return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(this.getClass(), e.getMessage() + " " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> eliminarUsuario(Long id) {
        String response = "";
        try {
            log.info(this.getClass(), "eliminando usuario id = " + id);
            response = usuarioService.eliminarUsuario(id);
        } catch (Exception e) {
            log.error(this.getClass(), e.getMessage() + " " + e.getLocalizedMessage());
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (response.isEmpty())
            return new ResponseEntity<String>(response, HttpStatus.OK);
        else
            return new ResponseEntity<String>(response, HttpStatus.BAD_REQUEST);
    }


}
