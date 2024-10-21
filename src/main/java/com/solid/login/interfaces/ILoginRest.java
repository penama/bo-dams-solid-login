package com.solid.login.interfaces;

import com.solid.login.modelo.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping( "/api/v1/login" )
public interface ILoginRest {

    @PostMapping("/autenticar")
    public ResponseEntity autenticar(@RequestBody Usuario usuario );

    @PutMapping( "/forget" )
    public ResponseEntity forget( @RequestBody Usuario usuario );
}
