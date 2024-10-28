/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dtoReports.ReportComputerDTO;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IReportComputerBO;
import interfaces.IReportComputerDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NaderCroft
 */
public class ReportComputerBO implements IReportComputerBO{

    IReportComputerDAO reportComputerDAO;

    public ReportComputerBO(IReportComputerDAO reportComputerDAO) {
        this.reportComputerDAO = reportComputerDAO;
    }
    
    
    @Override
    public List<ReportComputerDTO> obtenerDatosCentroComputo(LocalDate inicio, LocalDate fin) throws BusinessException {
        try{
                return this.reportComputerDAO.obtenerDatosCentroComputo(inicio, fin);
        }catch(PersistenceException ex) {
            Logger.getLogger(LaboratoryBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving ReportDegree list");
        }
    }
    }
