package com.solid.login.interfaces;

import com.solid.login.modelo.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping( "/api/v1/usuarios" )
public interface IUsuarioRest {

    @GetMapping( "/" )
    public ResponseEntity<List<Usuario>> getUsuarios();

    @GetMapping ( "/id/{id}" )
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id);

    @GetMapping ( "/user/{user}" )
    public ResponseEntity<Usuario> getUsuarioByUser(@PathVariable String user);

    @PostMapping ( "/" )
    public ResponseEntity<String> crearUsuario(@RequestBody Usuario usuario);

    @PutMapping ( "/" )
    public ResponseEntity<String> actualizarUsuario(@RequestBody Usuario usuario);

    @DeleteMapping( "/{id}" )
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id);
}
