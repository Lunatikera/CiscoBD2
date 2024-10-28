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
@Table(name = "tblStudent_Degrees")
public class StudentDegreeEntity implements Serializable {

    @Id
    @Column(name = "idStudent_Degree", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "remainingTime", nullable = true)
    private Long remainingTime;

    @ManyToOne
    @JoinColumn(name = "idDegree", nullable = false)
    private DegreeEntity degree;

    @ManyToOne
    @JoinColumn(name = "idStudent", nullable = false)
    private StudentEntity student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DegreeEntity getDegree() {
        return degree;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setDegree(DegreeEntity degree) {
        this.degree = degree;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public Long getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(Long remainingTime) {
        this.remainingTime = remainingTime;
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
        if (!(object instanceof StudentDegreeEntity)) {
            return false;
        }
        StudentDegreeEntity other = (StudentDegreeEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StudentDegreeEntity{" + "id=" + id + ", remainingTime=" + remainingTime + ", degree=" + degree + ", student=" + student + '}';
    }

   

}
