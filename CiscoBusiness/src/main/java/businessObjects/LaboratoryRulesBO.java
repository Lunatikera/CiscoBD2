/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;


import dto.LaboratoryRulesDTO;
import dto.RuleDTO;
import entities.LaboratoryEntity;
import entities.LaboratoryRulesEntity;
import entities.RuleEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.ILaboratoryDAO;
import interfaces.ILaboratoryRulesBO;
import interfaces.ILaboratoryRulesDAO;
import interfaces.IRuleDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.LaboratoryRuleMapper;

/**
 *
 * @author aleja
 */
public class LaboratoryRulesBO implements ILaboratoryRulesBO {

    
    ILaboratoryDAO laboratoryDAO ;
    IRuleDAO ruleDAO;
    ILaboratoryRulesDAO laboratoryRulesDAO;

    public LaboratoryRulesBO(ILaboratoryDAO laboratoryDAO, IRuleDAO ruleDAO, ILaboratoryRulesDAO laboratoryRulesDAO) {
        this.laboratoryDAO = laboratoryDAO;
        this.ruleDAO = ruleDAO;
        this.laboratoryRulesDAO = laboratoryRulesDAO;
    }

    

    @Override
    public void saveLaboratoryRule(LaboratoryRulesDTO laboratoryRule) throws BusinessException {
        if (laboratoryRule == null) {
            throw new BusinessException("LaboratoryRuleDTO cannot be null.");
        }

        // Convertir el DTO a la entidad
        LaboratoryRulesEntity laboratoryRulesEntity = new LaboratoryRulesEntity();

        try {
            LaboratoryEntity laboratory = laboratoryDAO.findLaboratoryByID(laboratoryRule.getLaboratory()); // Asumiendo que tienes un método para obtener el ID del laboratorio
            RuleEntity rule = ruleDAO.getRuleById(laboratoryRule.getRule()); // Asumiendo que tienes un método para obtener el ID de la regla

            // Asignar las entidades a la entidad de relación
            laboratoryRulesEntity.setLaboratory(laboratory);
            laboratoryRulesEntity.setRule(rule);

            // Guardar la entidad de relación
            laboratoryRulesDAO.saveSoftwareRule(laboratoryRulesEntity);
        } catch (PersistenceException ex) {
            Logger.getLogger(LaboratoryRulesBO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void deleteLaboratoryRule(Long laboratoryId, Long ruleId) throws BusinessException {
        if (laboratoryId == null || laboratoryId <= 0 || ruleId == null || ruleId <= 0) {
            throw new BusinessException("Invalid laboratory or rule ID.");
        }

        try {
            laboratoryRulesDAO.deleteSoftwareRule(laboratoryId, ruleId); // Llama al método DAO para eliminar la relación
        } catch (PersistenceException ex) {
            Logger.getLogger(LaboratoryRulesBO.class.getName()).log(Level.SEVERE, "Failed to delete laboratory rule", ex);
            throw new BusinessException("An error occurred while deleting the laboratory rule. Please try again later.");
        }
    }

    @Override
    public List<LaboratoryRulesDTO> getRulesNotAppliedByLaboratory(Long labId) throws BusinessException {
        if (labId == null || labId <= 0) {
            throw new BusinessException("Invalid laboratory ID.");
        }

        try {
            List<LaboratoryRulesEntity> ruleEntities = laboratoryRulesDAO.softwareNoInstall(labId);
            // Convertir List<RuleEntity> a List<RuleDTO>
            return LaboratoryRuleMapper.toDTOList(ruleEntities);
        } catch (PersistenceException e) {
            throw new BusinessException("Error retrieving rules not applied to laboratory", e);
        }
    }

    @Override
    public List<LaboratoryRulesDTO> getRulesAppliedByLaboratory(Long labId) throws BusinessException {
        if (labId == null || labId <= 0) {
            throw new BusinessException("Invalid laboratory ID.");
        }

        try {
            List<LaboratoryRulesEntity> ruleEntities = laboratoryRulesDAO.getSoftwareInstalledByComputer(labId);
            // Convertir List<RuleEntity> a List<RuleDTO>
            return LaboratoryRuleMapper.toDTOList(ruleEntities);
        } catch (PersistenceException e) {
            throw new BusinessException("Error retrieving rules applied to laboratory", e);
        }
    }
}
