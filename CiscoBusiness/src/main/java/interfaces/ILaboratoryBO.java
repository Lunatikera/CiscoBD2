/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.LaboratoryDTO;
import exception.BusinessException;
import java.util.List;

/**
 *
 * @author aleja
 */
public interface ILaboratoryBO {
    
    public List<LaboratoryDTO> laboratoryListByAcademyPaginated(Long academyID, int limit, int offset) throws BusinessException;

    public LaboratoryDTO findLaboratoryByID(Long LaboratoryId) throws BusinessException;

    public LaboratoryDTO saveLaboratory(LaboratoryDTO laboratory) throws BusinessException;

    public void updateLaboratory(LaboratoryDTO laboratory) throws BusinessException;

    public void deleteLaboratory(Long LaboratoryId) throws BusinessException;
    
    public List<LaboratoryDTO> laboratoryListByAcademy(Long academyID) throws BusinessException;
}
