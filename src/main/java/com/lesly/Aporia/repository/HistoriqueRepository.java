/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.lesly.Aporia.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
//import org.springframework.data.jpa.repository.Query;
import com.lesly.Aporia.model.Historique;

/**
 *
 * @author jeanl
 */

@Repository
public interface HistoriqueRepository extends JpaRepository<Historique, Integer> {
    
    Optional<Historique> findByIdAporianIdAndIdCategorieIdAndIdNiveauId(Integer aporianId, Integer categorieId, Integer niveauId);
    
    List<Historique> findByIdAporianIdOrderByDatePassageDesc(Integer aporianId);
    
}
