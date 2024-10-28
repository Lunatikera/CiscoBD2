/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtoReports;

import java.time.LocalDate;

/**
 *
 * @author NaderCroft
 */
public class ReportComputerDTO {
    private String labName; // Name of the computer center
    private String machineNumber;      // Computer number
    private Long studentCount;          // Number of students who used it
    private LocalDate date;             // Date of usage
    private Long usageMinutes;           // Minutes of usage per day
    private Long inactivityMinutes;      // Minutes of inactivity per day

    public ReportComputerDTO(String labName, String machineNumber, Long studentCount, LocalDate date, Long usageMinutes, Long inactivityMinutes) {
        this.labName = labName;
        this.machineNumber = machineNumber;
        this.studentCount = studentCount;
        this.date = date;
        this.usageMinutes = usageMinutes;
        this.inactivityMinutes = inactivityMinutes;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
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

    public Long getUsageMinutes() {
        return usageMinutes;
    }

    public void setUsageMinutes(Long usageMinutes) {
        this.usageMinutes = usageMinutes;
    }

    public Long getInactivityMinutes() {
        return inactivityMinutes;
    }

    public void setInactivityMinutes(Long inactivityMinutes) {
        this.inactivityMinutes = inactivityMinutes;
    }

    
    
    
}
