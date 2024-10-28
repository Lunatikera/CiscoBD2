/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author carli
 */
public class StudentDegreeDTO {

    private Long id;
    private String degreeName;
    private Long remainingTime;
    private Long timeLimit;
    private Long unique_ID;
    private Long idDegree;

    public StudentDegreeDTO(Long id, String degreeName, Long remainingTime, Long timeLimit) {
        this.id = id;
        this.degreeName = degreeName;
        this.remainingTime = remainingTime;
        this.timeLimit = timeLimit;
    }

    public StudentDegreeDTO() {
    }

    public StudentDegreeDTO(Long id, String degreeName, Long remainingTime, Long timeLimit, Long unique_ID, Long idDegree) {
        this.id = id;
        this.degreeName = degreeName;
        this.remainingTime = remainingTime;
        this.timeLimit = timeLimit;
        this.unique_ID = unique_ID;
        this.idDegree = idDegree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Long remainingTime) {
        this.remainingTime = remainingTime;
    }

    public Long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Long getUnique_ID() {
        return unique_ID;
    }

    public void setUnique_ID(Long unique_ID) {
        this.unique_ID = unique_ID;
    }

   
    public Long getIdDegree() {
        return idDegree;
    }

    public void setIdDegree(Long idDegree) {
        this.idDegree = idDegree;
    }

    @Override
    public String toString() {
        return degreeName ;
    }
    
    
    
}
