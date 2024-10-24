/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tblLaboratories")
public class LaboratoryEntity implements Serializable {

    @Id
    @Column(name = "idLaboratory", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "labName", nullable = false, length = 25)
    private String labName;

    @Column(name = "masterPassword", nullable = false, length = 25)
    private String masterPassword;

    @Column(name = "startTime", nullable = false)
    private LocalTime startTime;

    @Column(name = "endTime", nullable = true)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "idAcademicUnity", nullable = false)
    private AcademicUnityEntity academicUnity;

    @OneToMany(mappedBy = "laboratory", cascade = CascadeType.PERSIST)
    private List<LaboratoryRulesEntity> laboratorieRules;

    @OneToMany(mappedBy = "laboratory", cascade = CascadeType.PERSIST)
    private List<ComputerEntity> computers;

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
        if (!(object instanceof LaboratoryEntity)) {
            return false;
        }
        LaboratoryEntity other = (LaboratoryEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LaboratoryEntity[ id=" + id + " ]";
    }

}
