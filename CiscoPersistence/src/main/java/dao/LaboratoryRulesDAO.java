/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.LaboratoryRulesEntity;
import exception.PersistenceException;
import interfaces.ILaboratoryRulesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author aleja
 */
public class LaboratoryRulesDAO implements ILaboratoryRulesDAO {

    IConnectionBD connectionBD;

    public LaboratoryRulesDAO(IConnectionBD connectionBD) {
        this.connectionBD = connectionBD;
    }

    public List<LaboratoryRulesEntity> getRulesAppliedByLaboratory(Long idCom) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();

        String jpql = """
            SELECT r FROM LaboratoryRulesEntity r
            WHERE r.laboratory.id = :idCom
        """;

        TypedQuery<LaboratoryRulesEntity> query = entityManager.createQuery(jpql, LaboratoryRulesEntity.class);
        query.setParameter("idCom", idCom);
        return query.getResultList();
    }

    public List<LaboratoryRulesEntity> getRulesNotAppliedByLaboratory(Long idLab) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            // Consulta JPQL
            String jpql = """
                SELECT r FROM LaboratoryRulesEntity r
                WHERE r.rule.id NOT IN (
                    SELECT lr.rule.id 
                    FROM LaboratoryRulesEntity lr
                    WHERE lr.laboratory.id = :idLab
                )
            """;

            TypedQuery<LaboratoryRulesEntity> query = entityManager.createQuery(jpql, LaboratoryRulesEntity.class);
            query.setParameter("idLab", idLab);

            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void saveSoftwareRule(LaboratoryRulesEntity laboratoryRulesEntity) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        EntityTransaction transaction = null;
        

        try {
            transaction = entityManager.getTransaction();
            transaction.begin(); // Iniciar la transacción

            entityManager.persist(laboratoryRulesEntity); // Persistir la entidad

            transaction.commit(); // Confirmar la transacción
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Revertir cambios en caso de error
            }
            throw new PersistenceException("Error al guardar la regla de software: " + e.getMessage(), e); // Lanzar excepción personalizada
        } finally {
            if (entityManager != null) {
                entityManager.close(); // Cerrar EntityManager
            }
        }
    }

    public void deleteSoftwareRule(Long softwareId, Long ruleId) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin(); // Iniciar la transacción

            // Buscar la entidad `SoftwareRuleEntity` por `softwareId` y `ruleId`
            LaboratoryRulesEntity softwareRuleEntity = entityManager.createQuery(
                    "SELECT sr FROM LaboratoryRulesEntity sr "
                    + "WHERE sr.rule.id = :ruleId AND sr.laboratory.id = :softwareId",
                    LaboratoryRulesEntity.class)
                    .setParameter("softwareId", softwareId)
                    .setParameter("ruleId", ruleId)
                    .getSingleResult();

            // Eliminar la entidad si existe
            if (softwareRuleEntity != null) {
                entityManager.remove(softwareRuleEntity);
            }

            transaction.commit(); // Confirmar la transacción
        } catch (NoResultException e) {
            throw new PersistenceException("No se encontró la relación entre software y regla especificada.", e);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Revertir cambios en caso de error
            }
            throw new PersistenceException("Error al eliminar la regla de software: " + e.getMessage(), e); // Lanzar excepción personalizada
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close(); // Cerrar el EntityManager
            }
        }
    }

}
