/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.service;

import com.lesly.Aporia.model.Aporian;
import com.lesly.Aporia.repository.AporianRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jeanl
 */

@Service
public class AporianService {
    
    private final AporianRepository aporianRepository;
    
    public AporianService (AporianRepository aporianRepository) {
    
        this.aporianRepository = aporianRepository;
    
    }
    
    @Transactional
    public void inscrire(String email, String password) throws Exception {
    
        if(aporianRepository.existsByEmail(email)) {
        
            throw new Exception("Cet email existe deja, veuillez entrer un autre");
        
        }
        
        Aporian apo = new Aporian(email, password);
        aporianRepository.save(apo);
    
    }
    
    @Transactional(readOnly = true)
    public Aporian authentification(String email, String password) throws Exception {
    
        Aporian apo = aporianRepository
                      .findByEmailAndPassword(email, password)
                      .orElse(null);
        
        if(apo == null) {
        
            throw new Exception ("Veuillez verifier que vous avez entrer le bon mot de passe ou email !!!");
        
        }
        
        return apo;
    
    }
    
}
