/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lesly.Aporia.repository;

import com.lesly.Aporia.model.Aporian;
import java.sql.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jeanl
 */
@Repository
public interface AporianRepository extends JpaRepository<Aporian, Integer> {
    
    //Optional<Aporian> findByEmail(String email);
    List<Aporian> findByDateInscript(Timestamp dateInscr);
    
    boolean existsByEmail(String email);
    
    Optional<Aporian> findByEmailAndPassword (String email, String password);
    
}
