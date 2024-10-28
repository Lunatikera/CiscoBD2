/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtoReports.ReportDegreeDto;
import exception.PersistenceException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author NaderCroft
 */
public interface IDegreeReportDAO {
    public List<ReportDegreeDto> getCareerUsageReport(List<String> degreeNames, LocalDate startDate, LocalDate endDate)throws PersistenceException;
}
