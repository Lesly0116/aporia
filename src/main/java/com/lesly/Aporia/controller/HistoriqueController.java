/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import com.lesly.Aporia.service.HistoriqueService;
import com.lesly.Aporia.service.CategorieService;
import com.lesly.Aporia.service.NiveauService;
import com.lesly.Aporia.model.Historique;
import com.lesly.Aporia.model.Categorie;
import com.lesly.Aporia.model.Niveau;
import com.lesly.Aporia.model.Aporian;
import java.util.Optional;
import java.util.List;

@Controller
public class HistoriqueController {
    
    private final HistoriqueService historiqueService;
    private final CategorieService categorieService;
    private final NiveauService niveauService;
    
    public HistoriqueController(HistoriqueService historiqueService,
                                CategorieService categorieService,
                                NiveauService niveauService) {
        this.historiqueService = historiqueService;
        this.categorieService = categorieService;
        this.niveauService = niveauService;
    }
    
    @GetMapping("/historique")
    public String afficherHistorique(
            
            @RequestParam(required = false) Integer categorieId,
            @RequestParam(required = false) Integer niveauId,
            HttpSession session, 
            Model model) {
        
        if(session.getAttribute("utilisateurConnecte") == null) {
            
            return "redirect:/loginAporian";
            
        }
        
        Aporian utilisateur = (Aporian) session.getAttribute("utilisateurConnecte");
        Integer aporianId = utilisateur.getId();
        
        model.addAttribute("categories", categorieService.getAllCategories()); 
        model.addAttribute("niveaux", niveauService.getAllNiveaux());
        
        if(categorieId != null && niveauId != null) {

            Optional<Historique> historiqueFiltre = historiqueService.getHistoriqueFiltre(aporianId, categorieId, niveauId);
            
            model.addAttribute("historiqueFiltre", historiqueFiltre);
            model.addAttribute("filtreActif", true);
            
            categorieService.getCategorieById(categorieId)
                .ifPresent(c -> model.addAttribute("categorieChoisieObjet", c));
            
            niveauService.getNiveauById(niveauId)
                .ifPresent(n -> model.addAttribute("niveauChoisiObjet", n));
            
            model.addAttribute("categorieChoisie", categorieId);
            model.addAttribute("niveauChoisi", niveauId);
            
        } else {

            List<Historique> historiqueList = historiqueService.getHistoriqueByAporianId(aporianId);
            model.addAttribute("historiqueList", historiqueList);
            model.addAttribute("filtreActif", false);
        }
        
        return "historique";
    }
}