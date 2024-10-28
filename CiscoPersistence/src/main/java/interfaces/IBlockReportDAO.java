/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto2.BlockReportDTO;
import exception.PersistenceException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author adane
 */
public interface IBlockReportDAO {
    
    
    public List<BlockReportDTO> getBlockReport(LocalDate startDate, LocalDate endDate)throws PersistenceException ;
}
