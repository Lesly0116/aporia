/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.controller;

import com.lesly.Aporia.service.AporianService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jeanl
 */

@Controller
public class InscriptAporianController {
    
    private final AporianService aporianService;
    
    public InscriptAporianController (AporianService aporianService) {
    
        this.aporianService = aporianService;
    
    }
    
    @GetMapping("/inscriptionApo")
    public String afficherFormulaireInscript (){
    
        return "inscriptionApo";
    
    }
    
    
    @PostMapping("/inscriptionApo")
    public String inscrire ( @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model ) {
                
            if(email == null || email.trim().isEmpty()) {
            
                model.addAttribute("erreur","L' email est obligatoire");
                
                return "inscriptionApo";
            
            }
            
            if(password == null || password.trim().isEmpty()){
            
                model.addAttribute("erreur", "Le password est obligatoire");
                
                return "inscriptionApo";
            
            }
            
            try{
            
                aporianService.inscrire(email,password);
                
                return "redirect:/loginAporian?inscriptionApo=success";
            
            }catch(Exception e){
            
                model.addAttribute("erreur", e.getMessage());
                return "inscriptionApo";
            
            }
        
    }
    
}
