/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author carli
 */
@Entity
@Table(name = "tblComputer_Softwares")
public class ComputerSoftwareEntity implements Serializable {

    @Id
    @Column(name = "idComputer_Software", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "idComputer", nullable = false)
    private ComputerEntity computer;
    
    @ManyToOne
    @JoinColumn(name = "idSoftware", nullable = false)
    private SoftwareEntity software;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComputerEntity getComputer() {
        return computer;
    }

    public void setComputer(ComputerEntity computer) {
        this.computer = computer;
    }

    public SoftwareEntity getSoftware() {
        return software;
    }

    public void setSoftware(SoftwareEntity software) {
        this.software = software;
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
        if (!(object instanceof ComputerSoftwareEntity)) {
            return false;
        }
        ComputerSoftwareEntity other = (ComputerSoftwareEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ComputerSoftwareEntity[ id=" + id + " ]";
    }

}
