/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.LaboratoryRulesEntity;
import exception.PersistenceException;
import java.util.List;

/**
 *
 * @author aleja
 */
public interface ILaboratoryRulesDAO {

    public List<LaboratoryRulesEntity> getRulesAppliedByLaboratory(Long idCom) throws PersistenceException;

    public List<LaboratoryRulesEntity> getRulesNotAppliedByLaboratory(Long idLab) throws PersistenceException;

    public void saveSoftwareRule(LaboratoryRulesEntity laboratoryRulesEntity) throws PersistenceException;

    public void deleteSoftwareRule(Long softwareId, Long ruleId) throws PersistenceException;
}
