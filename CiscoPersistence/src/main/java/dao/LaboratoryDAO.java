/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.AcademicUnityEntity;
import entities.LaboratoryEntity;
import exception.PersistenceException;
import interfaces.ILaboratoryDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author carli
 */
public class LaboratoryDAO implements ILaboratoryDAO {

    IConnectionBD connectionBD;

    public LaboratoryDAO(IConnectionBD connectionBD) {
        this.connectionBD = connectionBD;
    }

    @Override
    public List<LaboratoryEntity> laboratoryListByAcademyPaginated(Long academyID, int limit, int offset) throws PersistenceException {

        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            

            return entityManager.createQuery("SELECT new entities.LaboratoryEntity(l.id, l.labName, l.masterPassword, l.startTime, l.endTime)  FROM LaboratoryEntity l WHERE l.academicUnity.id = :idAcademicUnity ", LaboratoryEntity.class)
                    .setParameter("idAcademicUnity", academyID)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (NoResultException e) {
            throw new PersistenceException("Academy not found", e);
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving laboratory list by academy", e);
        } finally {
            entityManager.close();
        }
    }

    public LaboratoryEntity findLaboratoryByID(Long LaboratoryId) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();

        try {
            return entityManager.find(LaboratoryEntity.class, LaboratoryId);
        } catch (Exception e) {
            throw new PersistenceException("Error finding Laboratory by ID", e);
        }

    }

    @Override
    public void updateLaboratory(LaboratoryEntity laboratory) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            // Check if the Laboratory exists
            LaboratoryEntity existingLaboratory = entityManager.createQuery("SELECT l FROM LaboratoryEntity l WHERE l.id : idLaboratory", LaboratoryEntity.class)
                    .setParameter("uniqueId", laboratory.getId())
                    .getSingleResult();

            if (existingLaboratory != null) {
                // Update fields as needed
                existingLaboratory.setLabName(laboratory.getLabName());
                existingLaboratory.setStartTime(laboratory.getStartTime());
                existingLaboratory.setEndTime(laboratory.getEndTime());
                existingLaboratory.setMasterPassword(laboratory.getMasterPassword());
                existingLaboratory.setAcademicUnity(laboratory.getAcademicUnity());

                // Add other fields to update as necessary
                entityManager.merge(existingLaboratory);
            } else {
                throw new PersistenceException("Laboratory not found.");
            }
        } catch (NoResultException e) {
            throw new PersistenceException("Laboratory not found.", e);
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public void deleteLaboratory(Long LaboratoryId) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            LaboratoryEntity laboratory = entityManager.createQuery("SELECT l FROM LaboratoryEntity l WHERE l.id = : idLaboratory", LaboratoryEntity.class)
                    .setParameter("idLaboratory", LaboratoryId)
                    .getSingleResult();
            if (laboratory != null) {
                laboratory.setIsDeleted(true);
                entityManager.merge(laboratory);
            } else {
                throw new PersistenceException("Laboratory not found.");
            }
        } catch (NoResultException e) {
            throw new PersistenceException("Laboratory not found.", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public LaboratoryEntity saveLaboratory(LaboratoryEntity laboratory) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            // Guardar el laboratorio en la base de datos
            entityManager.persist(laboratory);
            entityManager.getTransaction().commit();
            return laboratory; // Retornar la entidad guardada, puede ser útil para obtener el ID generado
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Hacer rollback en caso de error
            }
            throw new PersistenceException("Error saving laboratory", e);
        } finally {
            entityManager.close();
        }
    }
}
