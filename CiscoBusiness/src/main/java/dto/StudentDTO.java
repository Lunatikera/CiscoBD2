/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import enums.EnrollmentStatus;
import java.util.Objects;

/**
 *
 * @author carli
 */
public class StudentDTO {
 
    private Long unique_ID;
    private String names;
    private String firstLastname;
    private String secondLastName;
    private String password;
    private EnrollmentStatus enrollmentStatus;

    public StudentDTO(Long unique_ID, String names, String firstLastname, String secondLastname, String password, EnrollmentStatus enrollmentStatus) {
        this.unique_ID = unique_ID;
        this.names = names;
        this.firstLastname = firstLastname;
        this.secondLastName = secondLastname;
        this.password = password;
        this.enrollmentStatus = enrollmentStatus;
    }

    public StudentDTO(Long unique_ID, String names, String firstLastname, String secondLastname, String password) {
        this.unique_ID = unique_ID;
        this.names = names;
        this.firstLastname = firstLastname;
        this.secondLastName = secondLastname;
        this.password = password;
    }

    
    public StudentDTO() {
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

    // Getters and setters for each field
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

    public String getSecondLastname() {
        return secondLastName;
    }

    public void setSecondLastname(String secondLastname) {
        this.secondLastName = secondLastname;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final StudentDTO other = (StudentDTO) obj;
        return Objects.equals(this.unique_ID, other.unique_ID);
    }

    @Override
    public String toString() {
        return "StudentDTO{" + "unique_ID=" + unique_ID + ", names=" + names + ", firstLastname=" + firstLastname + ", secondLastname=" + secondLastName + ", password=" + password + ", enrollmentStatus=" + enrollmentStatus + '}';
    }
    
    
}