    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import enums.ComputerStatus;
import enums.ComputerTypes;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author carli
 */
@Entity
@Table(name = "tblComputers")
public class ComputerEntity implements Serializable {

    @Id
    @Column(name = "idComputer", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ipAdress", nullable = false, length = 25, unique = true)
    private String ipAdress;

    @Column(name = "machineNumber", nullable = false, length = 25)
    private Integer machineNumber;

    @Column(name = "status", nullable = false, length = 25)
    @Enumerated(EnumType.STRING) 
    private ComputerStatus status;
    
    @Column(name = "computerType", nullable = false, length = 25)
    @Enumerated(EnumType.STRING) 
    private ComputerTypes computerType;
    
    @ManyToOne
    @JoinColumn(name = "idLaboratory", nullable = false)
    private LaboratoryEntity laboratory;
    
      @OneToMany(mappedBy = "computer", cascade = CascadeType.PERSIST)
    private List<ComputerSoftwareEntity> computerSoftwares;
      
      @OneToMany(mappedBy = "computer", cascade = CascadeType.PERSIST)
    private List<StudentComputerEntity> studentComputers;

    public ComputerEntity(Long id, String ipAdress, Integer machineNumber, ComputerStatus status, ComputerTypes computerType) {
        this.id = id;
        this.ipAdress = ipAdress;
        this.machineNumber = machineNumber;
        this.status = status;
        this.computerType = computerType;
    }

    public ComputerEntity(Long id, String ipAdress, Integer machineNumber, ComputerStatus status, ComputerTypes computerType, LaboratoryEntity laboratory, List<ComputerSoftwareEntity> computerSoftwares, List<StudentComputerEntity> studentComputers) {
        this.id = id;
        this.ipAdress = ipAdress;
        this.machineNumber = machineNumber;
        this.status = status;
        this.computerType = computerType;
        this.laboratory = laboratory;
        this.computerSoftwares = computerSoftwares;
        this.studentComputers = studentComputers;
    }

    public ComputerEntity() {
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

    public ComputerStatus getStatus() {
        return status;
    }

    public void setStatus(ComputerStatus status) {
        this.status = status;
    }

    public LaboratoryEntity getLaboratory() {
        return laboratory;
    }

    public void setLaboratory(LaboratoryEntity laboratory) {
        this.laboratory = laboratory;
    }

    public List<ComputerSoftwareEntity> getComputerSoftwares() {
        return computerSoftwares;
    }

    public void setComputerSoftwares(List<ComputerSoftwareEntity> computerSoftwares) {
        this.computerSoftwares = computerSoftwares;
    }

    public List<StudentComputerEntity> getStudentComputers() {
        return studentComputers;
    }

    public void setStudentComputers(List<StudentComputerEntity> studentComputers) {
        this.studentComputers = studentComputers;
    }

    public ComputerTypes getComputerType() {
        return computerType;
    }

    public void setComputerType(ComputerTypes computerType) {
        this.computerType = computerType;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComputerEntity)) {
            return false;
        }
        ComputerEntity other = (ComputerEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ComputerEntity[ id=" + id + " ]";
    }

}
