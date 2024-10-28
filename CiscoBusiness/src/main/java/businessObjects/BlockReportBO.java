/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dto2.BlockReportDTO;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IBlockReportBO;
import interfaces.IBlockReportDAO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adane
 */
public class BlockReportBO implements IBlockReportBO{

    IBlockReportDAO blockReport;
    
    

    public BlockReportBO(IBlockReportDAO blockReport) {
        this.blockReport = blockReport;
    }
    
    
    @Override
    public List<BlockReportDTO> getBlockReport(LocalDate startDate, LocalDate endDate) throws BusinessException {
        List<BlockReportDTO> listaBlockReport = new ArrayList<>();
        try {
            listaBlockReport = blockReport.getBlockReport(startDate, endDate);
            return listaBlockReport;
        } catch (PersistenceException ex) {
            Logger.getLogger(BlockReportBO.class.getName()).log(Level.SEVERE, null, ex);
        }
      return   listaBlockReport;
    }
    
}
