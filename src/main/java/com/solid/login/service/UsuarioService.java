package com.solid.login.service;

import com.solid.login.modelo.Usuario;
import com.solid.login.repository.UsuarioRepository;
import com.solid.login.service.cifrado.CifradoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    private CifradoManager cifradoManager;

    public List<Usuario> getUsuarios() throws Exception{
        return usuarioRepository.findAll();
    }

    public String crearUsuario(Usuario usuario ) throws Exception {
        if ( existeUser( null, usuario.getUser() ) ){
            return "Usuario con user " + usuario.getUser()  + " ya existe...";
        }
        usuario.setPassword(cifradoManager.cifrar( usuario.getPassword() ) );
        usuarioRepository.save( usuario );
        return "";
    }

    public Usuario getUsuario(Long id ) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById( id );
        if ( usuarioOptional.isPresent() )
            return usuarioOptional.get();
        return null;
    }

    public Usuario getUsuario(String user ) throws Exception {
        return usuarioRepository.findByUser( user );
    }

    public String actualizarUsuario(Usuario usuario ) throws Exception {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById( usuario.getId() );
        if ( usuarioOptional.isPresent() ){
            Usuario user = usuarioOptional.get();
            if ( !user.getUser().equals( usuario.getUser() ) )
                if (existeUser( usuario.getId(), usuario.getUser() ))
                    return "Usuario con user " + usuario.getUser() + " modificado ya existe, debe escoger otro...";
            if ( !user.getPassword().equals( usuario.getPassword().trim() ) )
                usuario.setPassword(cifradoManager.cifrar( usuario.getPassword() ) );
            usuarioRepository.save( usuario );
            return "";
        }
        return "Usuario no existe...";
    }

    public String eliminarUsuario( Long id ) throws Exception{
        if ( usuarioRepository.existsById( id ) ) {
            usuarioRepository.deleteById(id);
            return "";
        } else {
            return "Usurio no existe...[" +  id + "]";
        }
    }

    public boolean existeUser( Long id, String user ) throws  Exception{
        Usuario usuario = null;
        if (id == null){
            usuario = usuarioRepository.findByUser( user );
            return usuario != null;
        } else {
            usuario = usuarioRepository.findByIdAndUser( id, user );
            return usuario != null;
        }
    }
}
