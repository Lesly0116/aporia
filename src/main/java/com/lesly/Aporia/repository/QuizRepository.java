/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lesly.Aporia.model.Quiz;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jeanl
 */

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    
    List<Quiz> findByTitreContainingIgnoreCase(String titre);
    
    @Query(value = "SELECT * FROM quiz WHERE id_categorie = ?1 AND id_niveau = ?2 ORDER BY RAND() Limit 10",
           nativeQuery = true)
    List<Quiz> findRandomQuizzesByCategorieAndNiveau(Integer categorieId, Integer idNiveau);
    
}
