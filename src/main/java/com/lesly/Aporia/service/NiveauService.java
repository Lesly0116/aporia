/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lesly.Aporia.repository.NiveauRepository;
import com.lesly.Aporia.model.Niveau;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jeanl
 */

@Service
public class NiveauService {
    
    private final NiveauRepository niveauRepository;
    
    
    public NiveauService(NiveauRepository niveauRepository) {
    
        this.niveauRepository = niveauRepository; 
    
    }
    
    @Transactional(readOnly = true)
    public List<Niveau> getAllNiveaux() {
    
        return niveauRepository.findAll();
    
    }
    
    @Transactional(readOnly = true)
    public List<Niveau> getNiveauParNom(String nom) {
    
        if(nom == null || nom.trim().isEmpty()) {
        
            return List.of();
        
        }
        
        return niveauRepository.findByNom(nom);
    
    }
    
    public Optional<Niveau> getNiveauById(Integer niveauId){
    
        if(niveauId == null){
        
            return Optional.empty();
        
        }
        
            return niveauRepository.findById(niveauId);
    
    }
    
}
