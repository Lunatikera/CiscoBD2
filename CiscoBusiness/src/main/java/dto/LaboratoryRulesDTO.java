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
public class LaboratoryRulesDTO {
     private Long id;
     private Long idRule;
     private Long idLaboratory;

    public LaboratoryRulesDTO(Long id, Long rule, Long laboratory) {
        this.id = id;
        this.idRule = rule;
        this.idLaboratory = laboratory;
    }

    public LaboratoryRulesDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRule() {
        return idRule;
    }

    public void setRule(Long rule) {
        this.idRule = rule;
    }

    public Long getLaboratory() {
        return idLaboratory;
    }

    public void setLaboratory(Long laboratory) {
        this.idLaboratory = laboratory;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final LaboratoryRulesDTO other = (LaboratoryRulesDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "LaboratoryRulesDTO{" + "id=" + id + ", rule=" + idRule + ", laboratory=" + idLaboratory + '}';
    }
    
    

    
     
}
