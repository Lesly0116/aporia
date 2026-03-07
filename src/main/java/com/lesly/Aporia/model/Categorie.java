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
@Table(name = "categorie")
public class Categorie implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(nullable = false)
    private String nom;
    
    public Categorie () {}
    
    public Categorie(String nom) {
        
        this.nom = nom;
    
    }
    
    public Categorie (Integer id, String nom) {
    
        this.id = id;
        this.nom = nom;
    
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
    
}
