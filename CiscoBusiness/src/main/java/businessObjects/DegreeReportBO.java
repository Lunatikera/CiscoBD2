/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;


import dtoReports.ReportDegreeDto;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IDegreeReportBO;
import interfaces.IDegreeReportDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NaderCroft
 */
public class DegreeReportBO implements IDegreeReportBO{
    IDegreeReportDAO degreeDAO;

    public DegreeReportBO(IDegreeReportDAO degreeDAO) {
        this.degreeDAO = degreeDAO;
    }
    
    
    @Override
    public List<ReportDegreeDto> getCareerUsageReport(List<String> degreeNames, LocalDate startDate, LocalDate endDate) throws BusinessException {
        try{
//            List<ReportDegreeDto> reportList = this.degreeDAO.getCareerUsageReport(degreeNames, startDate, endDate);
//            return reportList;
                return this.degreeDAO.getCareerUsageReport(degreeNames, startDate, endDate);
        }catch(PersistenceException ex) {
            Logger.getLogger(LaboratoryBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving ReportDegree list");
        }
    }
    
}
