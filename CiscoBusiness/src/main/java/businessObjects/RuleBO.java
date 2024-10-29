/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dto.RuleDTO;
import entities.RuleEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IRuleBO;
import interfaces.IRuleDAO;
import java.util.List;
import mappers.RuleMapper;

/**
 *
 * @author carli
 */
public class RuleBO implements IRuleBO {
    IRuleDAO ruleDAO;

    public RuleBO(IRuleDAO ruleDAO) {
        this.ruleDAO = ruleDAO;
    }

    @Override
    public List<RuleDTO> getRulesbyLaboratory(Long idLab, boolean has) throws BusinessException {
        if (idLab == null || idLab <= 0) {
        throw new BusinessException("Invalid laboratory ID.");
    }

    try {
        List<RuleEntity> ruleEntities = ruleDAO.getRulesbyLaboratory(idLab, has);
        // Convert List<RuleEntity> to List<RuleDTO>
        return RuleMapper.toDTOList(ruleEntities);
    } catch (PersistenceException e) {
        throw new BusinessException("Error retrieving rules by laboratory", e);
    }
}
    @Override
    public RuleDTO saveRule(RuleDTO ruleDTO) throws BusinessException {
          if (ruleDTO == null) {
        throw new BusinessException("Rule cannot be null.");
    }
    if (ruleDTO.getRuleDescription() == null || ruleDTO.getRuleDescription().isEmpty()) {
        throw new BusinessException("Rule description cannot be null or empty.");
    }

    try {
        RuleEntity ruleEntity = RuleMapper.toEntity(ruleDTO);
        RuleEntity savedRule = ruleDAO.saveRule(ruleEntity);
        return RuleMapper.toDTO(savedRule);
    } catch (PersistenceException e) {
        throw new BusinessException("Error saving rule", e);
    }
}
    @Override
    public RuleDTO deleteRule(Long ruleId) throws BusinessException {
      if (ruleId == null || ruleId <= 0) {
        throw new BusinessException("Invalid rule ID.");
    }

    try {
        RuleEntity deletedRule = ruleDAO.deleteRule(ruleId);
        return RuleMapper.toDTO(deletedRule);
    } catch (PersistenceException e) {
        throw new BusinessException("Error deleting rule", e);
    }
}
    @Override
    public List<RuleDTO> getRules() throws BusinessException {
       try {
        List<RuleEntity> ruleEntities = ruleDAO.getRules();
        if (ruleEntities == null || ruleEntities.isEmpty()) {
            throw new BusinessException("No rules found.");
        }
        return RuleMapper.toDTOList(ruleEntities);
    } catch (PersistenceException e) {
        throw new BusinessException("Error retrieving rules", e);
    }
}
    
    @Override
    public RuleDTO findDegreeForId(Long ruleId) throws BusinessException {
        try {
            RuleEntity rulesEntity = ruleDAO.getRuleById(ruleId);  // Busca la entidad por ID
            return RuleMapper.toDTO(rulesEntity);  // Convierte la entidad en DTO
            
        } catch (PersistenceException e) {
            throw new BusinessException("Error finding degree by ID", e);
        }
    }
    
}
