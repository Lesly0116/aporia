/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.controller;

import org.springframework.stereotype.Controller;
import com.lesly.Aporia.service.NiveauService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.lesly.Aporia.model.Niveau;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author jeanl
 */

@Controller
public class NiveauController {
    
    private final NiveauService niveauService;
    
    public NiveauController(NiveauService niveauService) {
    
        this.niveauService = niveauService;
    
    }
    
    @GetMapping("/niveau")
    public String afficherNiveau(HttpSession session, Model model) {
    
        if(session.getAttribute("utilisateurConnecte")  == null ) {
        
            return "redirect:/LoginAporian";
        
        }
        
        List<Niveau> niveau = niveauService.getAllNiveaux();
        model.addAttribute("niveau",niveau);
        
        return "niveau";
    
    }
    
    @GetMapping("/niveau/recherche")
    public String rechercherNiveaux(String nom, Model model) {
    
        List<Niveau> niveaux = niveauService.getNiveauParNom(nom);
        model.addAttribute("niveaux",niveaux);
        
        return "niveaux";
    
    }
    
}
