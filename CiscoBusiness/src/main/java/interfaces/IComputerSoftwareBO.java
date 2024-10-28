/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.ComputerSoftwareDTO;
import exception.BusinessException;

/**
 *
 * @author aleja
 */
public interface IComputerSoftwareBO {
    
    public void saveComputerSoftware(ComputerSoftwareDTO computerSoftware) throws BusinessException;
    
    public void deleteComputerSoftware(Long computerId, Long softwareId) throws BusinessException;
}
