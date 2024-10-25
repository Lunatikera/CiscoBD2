/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author aleja
 */
public class LaboratoryDTO {

    private String labName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String masterPassword;
    private Long id;

    public LaboratoryDTO(String labName, LocalTime startTime, LocalTime endTime, String masterPassword, Long id) {
        this.labName = labName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.masterPassword = masterPassword;
        this.id = id;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    public LaboratoryDTO() {
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LaboratoryDTO other = (LaboratoryDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return labName;
    }

}
