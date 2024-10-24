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
@Table(name = "tblAcademicUnities")
public class AcademicUnityEntity implements Serializable {

    @Id
    @Column(name = "idAcademicUnity", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
     @Column(name = "academicUnityName", nullable = false, length = 25)
    private String academicUnityName;
    
     @OneToMany(mappedBy = "academicUnity", cascade = CascadeType.PERSIST)
    private List<LaboratoryEntity> laboratories;


    public AcademicUnityEntity() {
    }

    public AcademicUnityEntity(String academicUnityName, List<LaboratoryEntity> laboratories) {
        this.academicUnityName = academicUnityName;
        this.laboratories = laboratories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof AcademicUnityEntity)) {
            return false;
        }
        AcademicUnityEntity other = (AcademicUnityEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AcademicUnityEntity[ id=" + id + " ]";
    }
    
}
