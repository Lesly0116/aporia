/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.lesly.Aporia.model.Aporian;
import jakarta.servlet.http.HttpSession;
import com.lesly.Aporia.service.AporianService;

/**
 *
 * @author jeanl
 */

@Controller
public class LoginAporianController {
    
    private final AporianService aporianService;
    
    public LoginAporianController (AporianService aporianService){
    
        this.aporianService = aporianService;
    
    }
    
    @GetMapping("/loginAporian")
    public String afficherFormulairelogin(){
    
        return "loginAporian";
    
    }
    
    @PostMapping("/loginAporian")
    public String login(
    
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession apoSession,
                        Model model
            
                        ){
    
                            try{
                            
                                    Aporian apor = aporianService.authentification(email, password);
                                    apoSession.setAttribute("utilisateurConnecte", apor);
                                    
                                    return "redirect:/selection";
                            
                            }catch(Exception e){
                            
                                model.addAttribute("erreur", e.getMessage());
                                
                                return "loginAporian";
                                
                            }
    
    }
    
}
