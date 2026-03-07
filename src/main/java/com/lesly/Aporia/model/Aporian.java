/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lesly.Aporia.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author jeanl
 */

@Entity
@Table(name = "aporian")
public class Aporian implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp dateInscript;
    
    public Aporian() {}
    
    public Aporian (Integer id, String email, String password, Timestamp dateInscript) {
    
        this.id = id;
        this.email = email;
        this.password = password;
        this.dateInscript = dateInscript;
    
    }
    
    public Aporian (String email, String password) {
    
        this.email = email;
        this.password = password;
        //this.dateInscript = new Timestamp(System.currentTimeMillis());
    
    }
    
    public Integer getId() {
    
        return id;
    }
    public void setId(Integer id) {
    
        this.id = id;
        
    }
    
    public String getEmail() {
    
        return email;
    
    }
    public void setEmail(String email) {
    
        this.email = email;
    
    }
    
    public String getPassword() {
    
        return password;
        
    }
    public void setPassword(String password) {
    
        this.password = password;
        
    }
    
    public Timestamp getDateInscript() {
    
        return dateInscript;
    
    }
    public void setDateInscript(Timestamp dateInscript){
    
        this.dateInscript = dateInscript;
    
    }
    
}
