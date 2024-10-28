/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtoReports.ReportComputerDTO;
import exception.BusinessException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author NaderCroft
 */
public interface IReportComputerBO {
    public List<ReportComputerDTO> obtenerDatosCentroComputo(List<Integer> degreeIds,LocalDate startDate, LocalDate endDate) throws BusinessException;
}
