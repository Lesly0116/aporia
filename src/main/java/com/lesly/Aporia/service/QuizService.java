/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.service;

import org.springframework.stereotype.Service;
import com.lesly.Aporia.repository.QuizRepository;
import com.lesly.Aporia.model.Quiz;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author jeanl
 */

@Service
public class QuizService {
    
    private final QuizRepository quizRepository;
    
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Quiz> getQuizByCategorieAndNiveau(Integer categorieId, Integer niveauId) {
    
        System.out.println("=== QuizService.getQuizByCategorieAndNiveau ===");
        System.out.println("Paramètres reçus - Catégorie ID: " + categorieId + ", Niveau ID: " + niveauId);
        
        if(niveauId == null || categorieId == null) {
            
            System.out.println(" Catégorie ou niveau null, retour liste vide");
            return List.of();
        }
        
        List<Quiz> quizzes = quizRepository.findRandomQuizzesByCategorieAndNiveau(categorieId, niveauId);
        
        System.out.println(" Nombre de quiz reçus du repository: " + quizzes.size());
        
        if (!quizzes.isEmpty()) {
            
            System.out.print(" IDs des quiz reçus: ");
            
            for (Quiz q : quizzes) {
                
                System.out.print(q.getId() + " ");
            }
            
            System.out.println();
            
        } else {
            
            System.out.println(" Aucun quiz trouvé pour ce couple catégorie/niveau");
        }
        
        final int LIMITE_MAX = 10;
        if (quizzes.size() > LIMITE_MAX) {
            
            System.out.println(" Attention: " + quizzes.size() + " quiz reçus, limitation à " + LIMITE_MAX);
            quizzes = quizzes.subList(0, LIMITE_MAX);
            System.out.println(" Quiz après limitation: " + quizzes.size());
            
            System.out.print(" IDs des quiz après limitation: ");
            
            for (Quiz q : quizzes) {
                
                System.out.print(q.getId() + " ");
                
            }
            
            System.out.println();
            
        }
        
        if (quizzes.size() < LIMITE_MAX) {
            
            System.out.println(" Attention: Moins de " + LIMITE_MAX + " quiz disponibles (" + quizzes.size() + ")");
        }
        
        System.out.println("=== Fin QuizService ===\n");
        
        return quizzes;
    }
}