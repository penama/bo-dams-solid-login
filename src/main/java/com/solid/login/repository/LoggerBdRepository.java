package com.solid.login.repository;

import com.solid.login.modelo.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggerBdRepository extends JpaRepository<Logs, Long> {

    @Query( "SELECT l FROM Logs l ORDER BY l.id DESC" )
    List<Logs> findAllByOrderIdDesc();

}
