/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.model;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 *
 * @author jeanl
 */

@Entity
@Table(name = "niveau")
public class Niveau implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    
    public Niveau() {}
    
    public Niveau(Integer id, String nom, String description) {
    
        this.id = id;
        this.nom = nom;
        this.description = description;
    
    }
    
    public Integer getId() {
    
        return id;
    
    }
    public void setId(Integer id) {
    
        this.id = id;
    
    }
    
    public String getNom() {
    
        return nom;
        
    }
    public void setNom(String nom) {
    
        this.nom = nom;
    
    }
    
    public String getDescription() {
    
        return description;
    
    }
    public void setDescription(String description) {
    
        this.description = description;
    
    }
    
}
