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
    private String computerCenterName; // Name of the computer center
    private Integer machineNumber;      // Computer number
    private Long studentCount;          // Number of students who used it
    private LocalDate date;             // Date of usage
    private Long usageMinutes;           // Minutes of usage per day
    private Long inactivityMinutes;      // Minutes of inactivity per day

    public ReportComputerDTO(String computerCenterName, Integer machineNumber, Long studentCount, LocalDate date, Long usageMinutes, Long inactivityMinutes) {
        this.computerCenterName = computerCenterName;
        this.machineNumber = machineNumber;
        this.studentCount = studentCount;
        this.date = date;
        this.usageMinutes = usageMinutes;
        this.inactivityMinutes = inactivityMinutes;
    }

    public String getComputerCenterName() {
        return computerCenterName;
    }

    public void setComputerCenterName(String computerCenterName) {
        this.computerCenterName = computerCenterName;
    }

    public Integer getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(Integer machineNumber) {
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
