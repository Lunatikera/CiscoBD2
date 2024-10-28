/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import entities.ComputerEntity;
import entities.SoftwareEntity;

/**
 *
 * @author aleja
 */
public class ComputerSoftwareDTO {

    private Long id;
    private Long computer;
    private Long software;

    public ComputerSoftwareDTO(Long id, Long computer, Long software) {
        this.id = id;
        this.computer = computer;
        this.software = software;
    }

    public ComputerSoftwareDTO() {
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComputer() {
        return computer;
    }

    public void setComputer(Long computer) {
        this.computer = computer;
    }

    public Long getSoftware() {
        return software;
    }

    public void setSoftware(Long software) {
        this.software = software;
    }

    
    
}
