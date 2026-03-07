/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.controller;

import com.lesly.Aporia.service.CategorieService;
import com.lesly.Aporia.model.Aporian;
import com.lesly.Aporia.model.Categorie;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import java.util.List;

/**
 *
 * @author jeanl
 */

@Controller

public class CategorieController {
    
    private final CategorieService categorieService;
    
    public CategorieController (CategorieService categorieService){
    
        this.categorieService = categorieService;
    
    }
    
    @GetMapping("/categorie")
    public String afficherCategorie(HttpSession session,
                                    Model model) {
    
                Aporian Apo = (Aporian) session.getAttribute("utilisateurConnecte");
                
                if(Apo == null) {
                
                    return "redirect:/loginAporian";
                
                }
                
                List<Categorie> categories = categorieService.getAllCategories();
                model.addAttribute("categories", categories);
                model.addAttribute("Apo", Apo);
                
                return "categoties";
    
    }
    
}
