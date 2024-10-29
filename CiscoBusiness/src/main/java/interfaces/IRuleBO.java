/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.RuleDTO;
import exception.BusinessException;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IRuleBO {

    public List<RuleDTO> getRulesbyLaboratory(Long idLab, boolean has) throws BusinessException;

    public RuleDTO saveRule(RuleDTO rule) throws BusinessException;

    public RuleDTO deleteRule(Long rule) throws BusinessException;

    public List<RuleDTO> getRules() throws BusinessException;

    public RuleDTO findDegreeForId(Long ruleId) throws BusinessException;
}
