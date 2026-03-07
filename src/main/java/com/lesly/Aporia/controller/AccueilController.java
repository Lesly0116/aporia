/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.lesly.Aporia.model.Aporian;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;

/**
 *
 * @author jeanl
 */

@Controller
public class AccueilController {
    
    @GetMapping("/")
    public String aporiAccueil() {
    
        return "redirect:/accueil";
    
    }
    
    @GetMapping("/accueil")
    public String pageAccueil() {
    
        return "accueil";
    
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session, Model model) {
    // Récupérer l'email pour l'afficher sur la page de déconnexion
    Aporian utilisateur = (Aporian) session.getAttribute("utilisateurConnecte");
    if (utilisateur != null) {
        model.addAttribute("email", utilisateur.getEmail());
    }
    
    // Invalider la session
    session.invalidate();
    
    return "logout";
}
    
}
