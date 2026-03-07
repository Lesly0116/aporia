/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author jeanl
 */
@Entity
@Table(name = "historique")
public class Historique {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_aporian", nullable = false)
    private Aporian idAporian;
    
    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categorie idCategorie;
    
    @ManyToOne
    @JoinColumn(name = "id_niveau", nullable = false)
    private Niveau idNiveau;
    
    @Column(nullable = false)
    private double score;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp datePassage;
    
    public Historique() { }
    
    public Historique(Integer id, Aporian idAporian, Categorie idCategorie, Niveau idNiveau, double score, Timestamp datePassage) {
    
        this.id = id;
        this.idAporian = idAporian;
        this.idCategorie = idCategorie;
        this.idNiveau = idNiveau;
        this.score = score;
        this.datePassage = datePassage;
    
    }
    
    public Integer getId() {
    
        return id;
    
    }
    public void setId(Integer id) {
    
        this.id = id;
    
    }
    
    public Aporian getIdAporian() {
    
        return idAporian;
    
    }
    public void setIdAporian(Aporian idAporian) {
    
        this.idAporian = idAporian;
    
    }
    
    public Categorie getIdCategorie() {
    
        return idCategorie;
    
    }
    public void setIdCategorie(Categorie idCategorie) {
    
        this.idCategorie = idCategorie;
    
    }
    
    public Niveau getIdNiveau() {
    
        return idNiveau;
    
    }
    public void setIdNiveau(Niveau idNiveau) {
        
    this.idNiveau = idNiveau;
    
    }
    
    public double getScore() {
    
        return score;
    
    }
    public void setScore(double score) {
    
        this.score = score;
    
    }
    
    public Timestamp getDatePassage() {
    
        return datePassage;
    
    }
    public void setDatePassage(Timestamp datePassage) {
        
    this.datePassage = datePassage;
    
    }
    
}
