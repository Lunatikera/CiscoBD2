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
public class SoftwareDTO {
     private Long id;
     private String softwareName;

    public SoftwareDTO(Long id, String softwareName) {
        this.id = id;
        this.softwareName = softwareName;
    }

    public SoftwareDTO() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final SoftwareDTO other = (SoftwareDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return softwareName ;
    }
     
     
}
