/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtoReports;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author NaderCroft
 */
public class ReportDegreeDto {
    private String degreeName;
    private Long minutesOfUsage;
    private Long studentCount;
    private LocalDate date;

    public ReportDegreeDto(String degreeName, Long minutesOfUsage,Long studentCount,LocalDate date) {
        this.degreeName = degreeName;
        this.minutesOfUsage = minutesOfUsage;
        this.studentCount = studentCount;
        this.date = date;
    }

    public ReportDegreeDto() {
    }

    
    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Long getMinutesOfUsage() {
        return minutesOfUsage;
    }

    public void setMinutesOfUsage(Long minutesOfUsage) {
        this.minutesOfUsage = minutesOfUsage;
    }

    public Long getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    
}
