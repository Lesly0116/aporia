package com.lesly.Aporia.dto;

import java.util.List;

public class ResultatQuiz {
    private int score;
    private int totalQuestions;
    private List<ResultatQuestion> details;
    
    public ResultatQuiz(int score, int totalQuestions, List<ResultatQuestion> details) {
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.details = details;
    }
    
    // Getters
    public int getScore() { return score; }
    public int getTotalQuestions() { return totalQuestions; }
    public int getPourcentage() { 
        return totalQuestions > 0 ? (score * 100) / totalQuestions : 0; 
    }
    public List<ResultatQuestion> getDetails() { return details; }
    
    // Setters (si nécessaire)
    public void setScore(int score) { this.score = score; }
    public void setTotalQuestions(int totalQuestions) { this.totalQuestions = totalQuestions; }
    public void setDetails(List<ResultatQuestion> details) { this.details = details; }
}