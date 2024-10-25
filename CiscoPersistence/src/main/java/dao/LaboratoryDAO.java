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
import javax.persistence.EntityTransaction;
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

            return entityManager.createQuery("SELECT l   FROM LaboratoryEntity l WHERE l.academicUnity.id = :idAcademicUnity ", LaboratoryEntity.class)
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
        EntityTransaction entityTrans = entityManager.getTransaction();
        try {
            entityTrans.begin();
            entityManager.merge(laboratory);
            entityTrans.commit();

        } catch (Exception e) {
            if(entityTrans.isActive()){
                entityTrans.rollback();
            }
            
           throw new PersistenceException("Error al editar el laboratorio", e);
        }finally{
            entityManager.close();
        }
        
        
    }

    @Override
    public void deleteLaboratory(Long laboratoryId) throws PersistenceException {
    EntityManager entityManager = connectionBD.getEntityManager();
    EntityTransaction transaction = null;
    
    try {
        // Iniciar una transacción
        transaction = entityManager.getTransaction();
        transaction.begin();
        
        // Buscar el laboratorio por ID
        LaboratoryEntity laboratory = entityManager.find(LaboratoryEntity.class, laboratoryId);
        
        if (laboratory != null) {
            laboratory.setIsDeleted(true); // Marcar como eliminado
            entityManager.merge(laboratory); // Guardar cambios
        } else {
            throw new PersistenceException("Laboratory not found.");
        }
        
        // Confirmar la transacción
        transaction.commit();
    } catch (NoResultException e) {
        throw new PersistenceException("Laboratory not found.", e);
    } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback(); // Revertir si hay un error
        }
        throw new PersistenceException("Error deleting laboratory.", e);
    } finally {
        entityManager.close(); // Asegúrate de cerrar el EntityManager
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
