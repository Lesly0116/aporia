package com.lesly.Aporia.dto;

public class ResultatQuestion {
    private String question;
    private String reponseDonnee;
    private String bonneReponse;
    private boolean correcte;
    
    public ResultatQuestion(String question, String reponseDonnee, 
                           String bonneReponse, boolean correcte) {
        this.question = question;
        this.reponseDonnee = reponseDonnee;
        this.bonneReponse = bonneReponse;
        this.correcte = correcte;
    }
    
    // Getters et setters
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    
    public String getReponseDonnee() { return reponseDonnee; }
    public void setReponseDonnee(String reponseDonnee) { this.reponseDonnee = reponseDonnee; }
    
    public String getBonneReponse() { return bonneReponse; }
    public void setBonneReponse(String bonneReponse) { this.bonneReponse = bonneReponse; }
    
    public boolean isCorrecte() { return correcte; }
    public void setCorrecte(boolean correcte) { this.correcte = correcte; }
}