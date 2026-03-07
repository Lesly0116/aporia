/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Arrays;
import java.io.Serializable;

/**
 *
 * @author jeanl
 */

@Entity
@Table(name = "quiz")
public class Quiz implements Serializable {
    
     private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "id_categorie", nullable = false)
    private Categorie categorie;
    
    @ManyToOne
    @JoinColumn(name = "id_niveau", nullable = false)
    private Niveau niveau;
    
    @Column(nullable = false)
    private String titre;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String reponse;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mauvaiseReponse1;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mauvaiseReponse2;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mauvaiseReponse3;
    
    
    public Quiz() {}
    
    public Quiz(Categorie categorie, Niveau niveau, String titre, String question, 
                String reponse, String mauvaiseReponse1, 
                String mauvaiseReponse2, String mauvaiseReponse3) {
        this.categorie = categorie;
        this.niveau = niveau;
        this.titre = titre;
        this.question = question;
        this.reponse = reponse;
        this.mauvaiseReponse1 = mauvaiseReponse1;
        this.mauvaiseReponse2 = mauvaiseReponse2;
        this.mauvaiseReponse3 = mauvaiseReponse3;
    }
    
    // Getters et Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    public Niveau getNiveau() {
        return niveau;
    }
    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
    
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getBonneReponse() {
        return reponse;
    }
    public void setBonneReponse(String reponse) {
        this.reponse = reponse;
    }
    
    public String getMauvaiseReponse1() {
        return mauvaiseReponse1;
    }
    public void setMauvaiseReponse1(String mauvaiseReponse1) {
        this.mauvaiseReponse1 = mauvaiseReponse1;
    }
    
    public String getMauvaiseReponse2() {
        return mauvaiseReponse2;
    }
    public void setMauvaiseReponse2(String mauvaiseReponse2) {
        this.mauvaiseReponse2 = mauvaiseReponse2;
    }
    
    public String getMauvaiseReponse3() {
        return mauvaiseReponse3;
    }
    public void setMauvaiseReponse3(String mauvaiseReponse3) {
        this.mauvaiseReponse3 = mauvaiseReponse3;
    }
    
    public List<String> getAllReponses() {
        return Arrays.asList(reponse, mauvaiseReponse1, mauvaiseReponse2, mauvaiseReponse3);
    }
}