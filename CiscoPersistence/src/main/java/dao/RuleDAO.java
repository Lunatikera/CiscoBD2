/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.RuleEntity;
import exception.PersistenceException;
import interfaces.IRuleDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author carli
 */
public class RuleDAO implements IRuleDAO {

    IConnectionBD connectionBD;

    public RuleDAO(IConnectionBD connectionBD) {
        this.connectionBD = connectionBD;
    }

    @Override
    public List<RuleEntity> getRulesbyLaboratory(Long idLab, boolean has) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            String jpql;
            if (has) {
                jpql = "SELECT r FROM RuleEntity r JOIN r.laboratorieRules lr WHERE lr.laboratory.id = :idLab";
            } else {
                jpql = "SELECT r FROM RuleEntity r WHERE r NOT IN (SELECT lr.rule FROM LaboratoryRulesEntity lr WHERE lr.laboratory.id = :idLab)";
            }

            TypedQuery<RuleEntity> query = entityManager.createQuery(jpql, RuleEntity.class);
            query.setParameter("idLab", idLab);

            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving rules by laboratory", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();  // Ensure EntityManager is closed
            }
        }
    }

    @Override
    public RuleEntity saveRule(RuleEntity rule) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (rule.getId() == null) {
                entityManager.persist(rule);
            } else {
                entityManager.merge(rule);
            }
            entityManager.getTransaction().commit();
            return rule;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException("Error saving rule", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();  // Ensure EntityManager is closed
            }
        }
    }

    @Override
    public RuleEntity deleteRule(Long ruleId) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Find the rule by its ID
            RuleEntity rule = entityManager.find(RuleEntity.class, ruleId);
            if (rule != null) {
                entityManager.remove(rule);  // Remove the found rule
            } else {
                throw new PersistenceException("Rule with ID " + ruleId + " not found.");
            }

            entityManager.getTransaction().commit();  // Commit the transaction
            return rule;  // Return the removed rule
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();  // Rollback if there was an error
            }
            throw new PersistenceException("Error deleting rule", e);  // Re-throw the exception
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();  // Ensure EntityManager is closed
            }
        }
    }

    @Override
    public List<RuleEntity> getRules() throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            // Create a TypedQuery to get all RuleEntity objects
            TypedQuery<RuleEntity> query = entityManager.createQuery("SELECT r FROM RuleEntity r", RuleEntity.class);
            return query.getResultList();  // Return the list of rules
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving rules", e);  // Handle exceptions
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();  // Ensure EntityManager is closed
            }
        }
    }

    @Override
    public RuleEntity getRuleById(Long ruleId) throws PersistenceException {
    EntityManager entityManager = connectionBD.getEntityManager();
    try {
        // Define la consulta JPQL para buscar la regla por ID
        String jpql = "SELECT r FROM RuleEntity r WHERE r.id = :ruleId";

        TypedQuery<RuleEntity> query = entityManager.createQuery(jpql, RuleEntity.class);
        query.setParameter("ruleId", ruleId);

        return query.getSingleResult(); // Retorna el único resultado

    } catch (NoResultException e) {
        return null; // Maneja el caso en que no se encuentra la regla
    } catch (Exception e) {
        throw new PersistenceException("Error retrieving rule by ID", e);
    } finally {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();  // Asegura que el EntityManager se cierra
        }
    }
}
    
    
}
