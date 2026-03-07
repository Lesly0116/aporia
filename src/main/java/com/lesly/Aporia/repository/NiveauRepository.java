/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lesly.Aporia.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lesly.Aporia.model.Niveau;

import java.util.List;

/**
 *
 * @author jeanl
 */
@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Integer> {
    
    List<Niveau> findByNom(String nom);
    
}
