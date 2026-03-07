/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.service;

import com.lesly.Aporia.repository.CategorieRepository;
import org.springframework.stereotype.Service;
import com.lesly.Aporia.model.Categorie;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author jeanl
 */

@Service
public class CategorieService {
    
    private final CategorieRepository categorieRepository;
    
    public CategorieService(CategorieRepository categorieRepository) {
    
        this.categorieRepository = categorieRepository;
    
    }
    
    @Transactional(readOnly = true)
    public List<Categorie> getCategoriesByNom(String nom) {
        
        if(nom == null || nom.trim().isEmpty()) {
            
            return List.of();
            
        }
        
        return categorieRepository.findByNom(nom);
        
    }
    
    @Transactional(readOnly = true)
    public List<Categorie> getAllCategories() {
    
        return categorieRepository.findAll();
    
    }
    
    public Optional<Categorie> getCategorieById(Integer categorieId){
    
        if(categorieId == null){
        
            return Optional.empty();
        
        }
        
            return categorieRepository.findById(categorieId);
    
    }
    
}
