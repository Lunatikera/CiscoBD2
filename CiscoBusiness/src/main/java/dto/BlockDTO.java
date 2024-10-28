/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 *
 * @author adane
 */
public class BlockDTO {
    private Long idBlock;
    private String motive;
    private LocalDate blockDate;
    private LocalDate withdrawalDate;
    private Long idStudent;
    private Long idRule;
    private String studentName;
    

    public BlockDTO() {
    }

    public BlockDTO(Long id, String motive,LocalDate withdrawalDate ,LocalDate blockDate , Long idRule, Long idStudent) {
        this.idBlock = id;
        this.motive = motive;
        this.blockDate = blockDate;
        this.withdrawalDate = withdrawalDate;
        this.idRule = idRule;
        this.idStudent = idStudent;
    }

    public BlockDTO(Long idBlock, String motive, LocalDate blockDate, LocalDate withdrawalDate, Long idStudent) {
        this.idBlock = idBlock;
        this.motive = motive;
        this.blockDate = blockDate;
        this.withdrawalDate = withdrawalDate;
        this.idStudent = idStudent;
    }
    

    public Long getId() {
        return idBlock;
    }

    public void setId(Long id) {
        this.idBlock = id;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public LocalDate getBlockDate() {
        return blockDate;
    }

    public void setBlockDate(LocalDate blockDate) {
        this.blockDate = blockDate;
    }

    public LocalDate getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(LocalDate withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public Long getIdRule() {
        return idRule;
    }

    public void setIdRule(Long idRule) {
        this.idRule = idRule;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public String getstudentName() {
        return studentName;
    }

    public void setstudentName(String studentName) {
        this.studentName = studentName;
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
        final BlockDTO other = (BlockDTO) obj;
        return this.idBlock == other.idBlock;
    }

    
    
    
}
