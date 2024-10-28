/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto2;

import java.time.LocalDate;

/**
 *
 * @author adane
 */
public class BlockReportDTO {
    
    private String studentName;
    private LocalDate blockDate;
    private LocalDate releaseDate;
    private String reason;

    public BlockReportDTO() {
    }

    public BlockReportDTO(String studentName, LocalDate blockDate, LocalDate releaseDate, String reason) {
        this.studentName = studentName;
        this.blockDate = blockDate;
        this.releaseDate = releaseDate;
        this.reason = reason;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getBlockDate() {
        return blockDate;
    }

    public void setBlockDate(LocalDate blockDate) {
        this.blockDate = blockDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    
    
}
