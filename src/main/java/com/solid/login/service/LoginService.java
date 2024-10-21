package com.solid.login.service;

import com.solid.login.modelo.Usuario;
import com.solid.login.repository.UsuarioRepository;
import com.solid.login.service.cifrado.CifradoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class LoginService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    private CifradoManager cifradoManager;

    @Value("${loggerapi.url}")
    private String loggerapiUrl;

    public Boolean autenticar( Usuario usuario ) throws Exception{
        Usuario usuarioBd = usuarioRepository.findByUser( usuario.getUser().trim() );
        if ( usuarioBd == null )
            return false;
        String cifrado = cifradoManager.cifrar( usuario.getPassword() );
        return cifrado.equals( usuarioBd.getPassword() );
    }

    public String forget( Usuario usuario ) throws Exception {
        Usuario usuarioBd = usuarioRepository.findByUser( usuario.getUser().trim() );
        if ( usuarioBd == null )
            return null;
        String passAleatorio = generarPassAleatorio();
        String cifrado = cifradoManager.cifrar( passAleatorio );
        usuarioBd.setPassword( cifrado );
        usuarioRepository.save( usuarioBd );
        return passAleatorio;
    }

    private String generarPassAleatorio(){
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String cadena = "";
        int longitud = 5;
        for (int i = 0; i < longitud; i++) {
            cadena += banco.charAt(ThreadLocalRandom.current().nextInt( 0, banco.length() ));;
        }
        return cadena;
    }

}
