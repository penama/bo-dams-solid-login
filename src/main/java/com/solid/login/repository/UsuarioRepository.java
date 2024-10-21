package com.solid.login.repository;

import com.solid.login.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.user = ?1")
    Usuario findByUser(String user);

    @Query("SELECT u FROM Usuario u WHERE u.id <> ?1 and u.user = ?2")
    Usuario findByIdAndUser(Long id, String user);
}
