/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtoReports.ReportComputerDTO;
import exception.PersistenceException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author NaderCroft
 */
public interface IReportComputerDAO {
    public List<ReportComputerDTO> obtenerDatosCentroComputo(LocalDate inicio, LocalDate fin)throws PersistenceException;
}
