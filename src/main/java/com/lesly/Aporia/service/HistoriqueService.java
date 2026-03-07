/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.service;

import org.springframework.stereotype.Service;
import com.lesly.Aporia.repository.HistoriqueRepository;
import com.lesly.Aporia.model.Historique;
        
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
        
/**
 *
 * @author jeanl
 */

@Service
public class HistoriqueService {
    
    private final HistoriqueRepository historiqueRepository;
    
    public HistoriqueService(HistoriqueRepository historiqueRepository) {
    
        this.historiqueRepository = historiqueRepository;
    
    }
    
    @Transactional(readOnly = true)
    public Optional<Historique> getHistoriqueFiltre(Integer aporianId, Integer categorieId, Integer niveauId){
    
        if(aporianId == null || categorieId == null || niveauId == null) {
        
            return Optional.empty();
        
        }
        
            return historiqueRepository.findByIdAporianIdAndIdCategorieIdAndIdNiveauId(aporianId, categorieId, niveauId);
    
    }
    
    @Transactional(readOnly = true)
    public List<Historique> getHistoriqueByAporianId(Integer aporianId) {
    
        if(aporianId == null) {
        
            return List.of();
        
        }
        
        return historiqueRepository.findByIdAporianIdOrderByDatePassageDesc(aporianId);
    
    }
    
     public void sauvegarder(Historique historique) {
        historiqueRepository.save(historique);
    }
    
}
