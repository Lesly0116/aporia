/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.controller;

import org.springframework.stereotype.Controller;
import com.lesly.Aporia.service.NiveauService;
import com.lesly.Aporia.service.CategorieService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.lesly.Aporia.model.Niveau;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.HashMap;
import java.util.Map;  

/**
 *
 * @author jeanl
 */

@Controller
public class SelectionController {
    
    private final CategorieService categorieService;
    private final NiveauService niveauService;
    
    public SelectionController(CategorieService categorieService,
                               NiveauService niveauService) {
    
        this.categorieService = categorieService;
        this.niveauService = niveauService;
    
    }
    
    @GetMapping("/selection")
    public String afficherSelection(HttpSession session, Model model) {
    
        if (session.getAttribute("utilisateurConnecte") == null) {
            
            return "redirect:/loginAporian";
            
        }
        
            try {
            System.out.println("Récupération des catégories...");
            var categories = categorieService.getAllCategories();
            System.out.println("Catégories récupérées: " + categories.size());
            
            System.out.println("Récupération des niveaux...");
            var niveaux = niveauService.getAllNiveaux();
            System.out.println("Niveaux récupérés: " + niveaux.size());
            
            model.addAttribute("categories", categories);
            model.addAttribute("niveaux", niveaux);
            
            Map<String, Integer> tempsParNiveau = new HashMap<>();
            
            tempsParNiveau.put("Débutant", 60);
            tempsParNiveau.put("Intermédiaire", 45);
            tempsParNiveau.put("Expert", 30);
            tempsParNiveau.put("Master", 20);
            
            model.addAttribute("tempsNiveaux", tempsParNiveau);
            
            return "selection";
            
        } catch (Exception e) {
            System.out.println("ERREUR dans /selection: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/loginAporian?error=selection";
        }
    
    }
    
    @PostMapping("/selection")
    public String traiterSelection(@RequestParam("categorieId") Integer categorieId,
                                   @RequestParam("niveauId") Integer niveauId,
                                    HttpSession session) {
    
        session.setAttribute("categorieChoisie", categorieId);
        session.setAttribute("niveauChoisi", niveauId);
        
        return "redirect:/quiz";
    
    }
    
}
