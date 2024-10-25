/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import enums.ComputerStatus;
import enums.ComputerTypes;
import java.util.Objects;

/**
 *
 * @author NaderCroft
 */
public class ComputerDTO {
    private Long id;
    private String ipAdress;
    private Integer machineNumber;
    private ComputerTypes computerType;
    private ComputerStatus status;

    public ComputerDTO(Long id, String ipAdress, Integer machineNumber, ComputerTypes computerType, ComputerStatus status) {
        this.id = id;
        this.ipAdress = ipAdress;
        this.machineNumber = machineNumber;
        this.computerType = computerType;
        this.status = status;
    }

    public ComputerDTO() {
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public Integer getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(Integer machineNumber) {
        this.machineNumber = machineNumber;
    }

    public ComputerTypes getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerTypes computerType) {
        this.computerType = computerType;
    }

    public ComputerStatus getStatus() {
        return status;
    }

    public void setStatus(ComputerStatus status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final ComputerDTO other = (ComputerDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    
    
}
