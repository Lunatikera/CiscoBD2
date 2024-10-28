/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDateTime;

/**
 *
 * @author carli
 */
public class StudentComputerDTO {

    private Long id;

    private LocalDateTime startDateTime;

    private LocalDateTime selectedDateTime;

    private String degreeName;

    private LocalDateTime endDateTime;

    private Long idstudent;

    private Long idcomputer;

    public StudentComputerDTO() {
    }

    public StudentComputerDTO(Long id, LocalDateTime startDateTime, LocalDateTime selectedDateTime, String degreeName, LocalDateTime endDateTime, Long idstudent, Long idcomputer) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.selectedDateTime = selectedDateTime;
        this.degreeName = degreeName;
        this.endDateTime = endDateTime;
        this.idstudent = idstudent;
        this.idcomputer = idcomputer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getSelectedDateTime() {
        return selectedDateTime;
    }

    public void setSelectedDateTime(LocalDateTime selectedDateTime) {
        this.selectedDateTime = selectedDateTime;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Long getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(Long idstudent) {
        this.idstudent = idstudent;
    }

    public Long getIdcomputer() {
        return idcomputer;
    }

    public void setIdcomputer(Long idcomputer) {
        this.idcomputer = idcomputer;
    }
    
}
