/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author carli
 */
@Entity
@Table(name = "tblSoftwares")
public class SoftwareEntity implements Serializable {

    @Id
    @Column(name = "idSoftware", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "softwareName", nullable = false, length = 50)
    private String softwareName;
    
    @OneToMany(mappedBy = "software", cascade = CascadeType.PERSIST)
    private List<ComputerSoftwareEntity> computerSoftwares;

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

    public List<ComputerSoftwareEntity> getComputerSoftwares() {
        return computerSoftwares;
    }

    public void setComputerSoftwares(List<ComputerSoftwareEntity> computerSoftwares) {
        this.computerSoftwares = computerSoftwares;
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
        if (!(object instanceof SoftwareEntity)) {
            return false;
        }
        SoftwareEntity other = (SoftwareEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SoftwareEntity[ id=" + id + " ]";
    }

}
