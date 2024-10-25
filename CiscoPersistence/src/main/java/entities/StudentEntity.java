/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import enums.EnrollmentStatus;
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
@Table(name = "tblStudents")
public class StudentEntity implements Serializable {

    @Id
    @Column(name = "idStudent", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unique_ID", nullable = false, length = 25, unique = true)
    private Long unique_ID;

    @Column(name = "password", nullable = false, length = 25)
    private String password;

    @Column(name = "names", nullable = false, length = 25)
    private String names;

    @Column(name = "firstLastname", nullable = false, length = 25)
    private String firstLastname;

    @Column(name = "secondLastName", nullable = false, length = 25)
    private String secondLastName;

    @Column(name = "enrollmentStatus", nullable = false, length = 25)
    private EnrollmentStatus enrollmentStatus;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private List<StudentComputerEntity> studentComputers;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private List<BlockEntity> blocks;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST)
    private List<StudentDegreeEntity> studentDegrees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUnique_ID() {
        return unique_ID;
    }

    public void setUnique_ID(Long unique_ID) {
        this.unique_ID = unique_ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getFirstLastname() {
        return firstLastname;
    }

    public void setFirstLastname(String firstLastname) {
        this.firstLastname = firstLastname;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public List<StudentComputerEntity> getStudentComputers() {
        return studentComputers;
    }

    public void setStudentComputers(List<StudentComputerEntity> studentComputers) {
        this.studentComputers = studentComputers;
    }

    public List<BlockEntity> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<BlockEntity> blocks) {
        this.blocks = blocks;
    }

    public List<StudentDegreeEntity> getStudentDegrees() {
        return studentDegrees;
    }

    public void setStudentDegrees(List<StudentDegreeEntity> studentDegrees) {
        this.studentDegrees = studentDegrees;
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
        if (!(object instanceof StudentEntity)) {
            return false;
        }
        StudentEntity other = (StudentEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.StudentEntity[ id=" + id + " ]";
    }

}
