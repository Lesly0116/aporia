/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lesly.Aporia.repository;

import com.lesly.Aporia.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jeanl
 */

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer> {
    
    List <Categorie> findByNom(String nom);
    
    Optional<Categorie> findByNomIgnoreCase(String nom);
    
    boolean existsByNom(String nom);

    
}
