/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.RuleEntity;
import exception.PersistenceException;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IRuleDAO {

    public List<RuleEntity> getRulesbyLaboratory(Long idLab, boolean has) throws PersistenceException;

    public RuleEntity saveRule(RuleEntity rule) throws PersistenceException;

    public RuleEntity deleteRule(Long rule) throws PersistenceException;

    public List<RuleEntity> getRules() throws PersistenceException;

    public RuleEntity getRuleById(Long ruleId) throws PersistenceException;
}
