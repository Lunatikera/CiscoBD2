/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.LaboratoryRulesDTO;
import dto.RuleDTO;
import exception.BusinessException;
import java.util.List;

/**
 *
 * @author aleja
 */
public interface ILaboratoryRulesBO {

    public void saveLaboratoryRule(LaboratoryRulesDTO laboratoryRule) throws BusinessException;

    public void deleteLaboratoryRule(Long laboratoryId, Long ruleId) throws BusinessException;
    
    public List<LaboratoryRulesDTO> getRulesNotAppliedByLaboratory(Long labId) throws BusinessException;
    
    public List<LaboratoryRulesDTO> getRulesAppliedByLaboratory(Long labId) throws BusinessException;
}
