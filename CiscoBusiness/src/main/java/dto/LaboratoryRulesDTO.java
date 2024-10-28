/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author aleja
 */
public class LaboratoryRulesDTO {
     private Long id;
     private Long rule;
     private Long laboratory;

    public LaboratoryRulesDTO(Long id, Long rule, Long laboratory) {
        this.id = id;
        this.rule = rule;
        this.laboratory = laboratory;
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
        return rule;
    }

    public void setRule(Long rule) {
        this.rule = rule;
    }

    public Long getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(Long laboratory) {
        this.laboratory = laboratory;
    }
     
     
}
