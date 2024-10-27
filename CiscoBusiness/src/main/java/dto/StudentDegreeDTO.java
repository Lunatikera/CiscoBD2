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

    public StudentDegreeDTO(Long id, String degreeName, Long remainingTime, Long timeLimit) {
        this.id = id;
        this.degreeName = degreeName;
        this.remainingTime = remainingTime;
        this.timeLimit = timeLimit;
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
    
    
    
}
