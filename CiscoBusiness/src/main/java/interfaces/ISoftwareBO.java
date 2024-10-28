/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.SoftwareDTO;
import exception.BusinessException;
import java.util.List;

/**
 *
 * @author aleja
 */
public interface ISoftwareBO {

    public SoftwareDTO deleteSoftware(Long software) throws BusinessException;

    public SoftwareDTO saveSoftware(SoftwareDTO software) throws BusinessException;

    public List<SoftwareDTO> getSoftwarebyComputer(Long idCom, boolean has) throws BusinessException;

    public List<SoftwareDTO> softwareListPaginated(int limit, int page) throws BusinessException;

    public List<SoftwareDTO> getSoftwareInstalledByComputer(Long idCom) throws BusinessException;

    public List<SoftwareDTO> getSoftwareNotInstalledByComputer(Long idCom) throws BusinessException;

    public SoftwareDTO getSoftwareById(Long softwareId) throws BusinessException;
}
