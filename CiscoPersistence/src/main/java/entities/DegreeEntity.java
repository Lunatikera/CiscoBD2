/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import java.io.Serializable;
import java.time.Duration;
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
@Table(name = "tblDegrees")
public class DegreeEntity implements Serializable {

    @Id
    @Column(name = "idDegree", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "degreeName", nullable = false, length = 25)
    private String degreeName;

    @Column(name = "timeLimit", nullable = false)
      private Duration timeLimit;;
    
    @OneToMany(mappedBy = "degree", cascade = CascadeType.PERSIST)
    private List<StudentDegreeEntity> studentDegrees;

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

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Duration getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Duration timeLimit) {
        this.timeLimit = timeLimit;
    }

    public List<StudentDegreeEntity> getStudentDegrees() {
        return studentDegrees;
    }

    public void setStudentDegrees(List<StudentDegreeEntity> studentDegrees) {
        this.studentDegrees = studentDegrees;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DegreeEntity)) {
            return false;
        }
        DegreeEntity other = (DegreeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DegreeEntity[ id=" + id + " ]";
    }

}
