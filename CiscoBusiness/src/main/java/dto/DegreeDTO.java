/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.Duration;
import java.util.Objects;

/**
 *
 * @author carli
 */
public class DegreeDTO {

    private Long id;
    private String degreeName;
    private Duration timeLimit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return degreeName;
    }

    public void setName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Duration getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Duration timeLimit) {
        this.timeLimit = timeLimit;
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
        final DegreeDTO other = (DegreeDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return degreeName ;
    }
    
    
}
