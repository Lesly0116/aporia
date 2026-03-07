/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.controller;

import com.lesly.Aporia.service.QuizService;
import com.lesly.Aporia.service.HistoriqueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import com.lesly.Aporia.model.*;
import com.lesly.Aporia.dto.*;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Map;
import com.lesly.Aporia.model.Quiz;

@Controller
public class QuizController {
    
    private final QuizService quizService;
    private final HistoriqueService historiqueService;
    
    public QuizController(QuizService quizService, HistoriqueService historiqueService) {
        this.quizService = quizService;
        this.historiqueService = historiqueService;
    }
    
    @GetMapping("/quiz")
public String afficherQuiz(HttpSession session, Model model) {
    
    System.out.println("=== DÉBUT QUIZ ===");
    
    if(session.getAttribute("utilisateurConnecte") == null) {
        return "redirect:/loginAporian";
    }
    
    Integer categorieId = (Integer) session.getAttribute("categorieChoisie");
    Integer niveauId = (Integer) session.getAttribute("niveauChoisi");
    
    if(categorieId == null || niveauId == null) {
        return "redirect:/selection";
    }
    
    if (session.getAttribute("quizEnCours") != null) {
        System.out.println("⚠️ Quiz déjà en cours, nettoyage et redémarrage");
        session.removeAttribute("quizEnCours");
        session.removeAttribute("quizList");
   
    }
    
    List<Quiz> quizList = quizService.getQuizByCategorieAndNiveau(categorieId, niveauId);
    System.out.println("📊 Quiz chargés: " + quizList.size() + " questions");
    
    if (quizList.isEmpty()) {
        System.out.println("⚠️ Aucun quiz trouvé");
        model.addAttribute("quizList", quizList);
        return "quiz";
    }
    
    session.setAttribute("quizList", quizList);
    session.setAttribute("quizEnCours", true);
    session.setAttribute("categorieChoisie", categorieId);
    session.setAttribute("niveauChoisi", niveauId);
    
    for (Quiz quiz : quizList) {
        List<String> reponses = Arrays.asList(
            quiz.getBonneReponse(),
            quiz.getMauvaiseReponse1(),
            quiz.getMauvaiseReponse2(),
            quiz.getMauvaiseReponse3()
        );
        
        List<String> reponsesSecurisees = new ArrayList<>();
        reponsesSecurisees.add(quiz.getBonneReponse() != null ? quiz.getBonneReponse() : "Réponse A");
        reponsesSecurisees.add(quiz.getMauvaiseReponse1() != null ? quiz.getMauvaiseReponse1() : "Réponse B");
        reponsesSecurisees.add(quiz.getMauvaiseReponse2() != null ? quiz.getMauvaiseReponse2() : "Réponse C");
        reponsesSecurisees.add(quiz.getMauvaiseReponse3() != null ? quiz.getMauvaiseReponse3() : "Réponse D");
        
        Collections.shuffle(reponsesSecurisees);
        
        String attributName = "reponses_" + quiz.getId();
        model.addAttribute(attributName, reponsesSecurisees);
    }
    
    model.addAttribute("quizList", quizList);
    System.out.println("=== FIN QUIZ ===\n");
    
    return "quiz";
}
    
    @PostMapping("/repondre")
    public String traiterReponses(@RequestParam Map<String, String> toutesReponses,
                                  HttpSession session,
                                  Model model) {
        
        System.out.println("=== TRAITEMENT DES RÉPONSES ===");
        
        if(session.getAttribute("utilisateurConnecte") == null) {
            
            return "redirect:/loginAporian";
            
        }
        
        List<Quiz> quizList = (List<Quiz>) session.getAttribute("quizList");
        
        if (quizList == null || quizList.isEmpty()) {
            
            System.out.println(" Aucune liste de quiz en session");
            
            return "redirect:/selection";
            
        }
        
        Aporian utilisateur = (Aporian) session.getAttribute("utilisateurConnecte");
        
        ResultatQuiz resultat = calculerScore(quizList, toutesReponses);
        
        System.out.println(" Score: " + resultat.getScore() + "/" + resultat.getTotalQuestions() + 
                          " (" + resultat.getPourcentage() + "%)");
        
        Historique historique = new Historique();
        historique.setIdAporian(utilisateur);
        
        Integer categorieId = (Integer) session.getAttribute("categorieChoisie");
        Integer niveauId = (Integer) session.getAttribute("niveauChoisi");
        
        Categorie categorie = new Categorie();
        categorie.setId(categorieId);
        Niveau niveau = new Niveau();
        niveau.setId(niveauId);
        
        historique.setIdCategorie(categorie);
        historique.setIdNiveau(niveau);
        historique.setScore(resultat.getPourcentage());
        
        historiqueService.sauvegarder(historique);
        
        session.removeAttribute("quizList");
        session.removeAttribute("quizEnCours");
        
        model.addAttribute("score", resultat.getScore());
        model.addAttribute("totalQuestions", resultat.getTotalQuestions());
        model.addAttribute("pourcentage", resultat.getPourcentage());
        model.addAttribute("details", resultat.getDetails());
        model.addAttribute("categorie", categorieId);
        model.addAttribute("niveau", niveauId);
        
        System.out.println("=== FIN TRAITEMENT ===\n");
        
        return "resultat";
    }
    
    private ResultatQuiz calculerScore(List<Quiz> quizList, Map<String, String> reponses) {
        
        int score = 0;
        
        List<ResultatQuestion> details = new ArrayList<>();
        
        for (Quiz quiz : quizList) {
            
            String reponseUtilisateur = reponses.get("reponse_" + quiz.getId());
            boolean estCorrecte = reponseUtilisateur != null && 
                                 quiz.getBonneReponse().equals(reponseUtilisateur);
            
            if (estCorrecte) {
                
                score++;
                
            }
            
            details.add(new ResultatQuestion(
                    
                quiz.getQuestion(),
                reponseUtilisateur != null ? reponseUtilisateur : "Aucune réponse",
                quiz.getBonneReponse(),
                estCorrecte
                    
            ));
        }
        
        return new ResultatQuiz(score, quizList.size(), details);
    }
}