/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Objects;

/**
 *
 * @author aleja
 */
public class AcademyDTO {
    
    private Long id;
    private String academicUnityName;

    public AcademyDTO(Long id, String academyName) {
        this.id = id;
        this.academicUnityName = academyName;
    }

    public AcademyDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcademyName() {
        return academicUnityName;
    }

    public void setAcademyName(String academyName) {
        this.academicUnityName = academyName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final AcademyDTO other = (AcademyDTO) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return  academicUnityName;
    }
    
    
}
