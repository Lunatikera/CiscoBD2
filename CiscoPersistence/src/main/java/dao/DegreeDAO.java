/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.DegreeEntity;
import exception.PersistenceException;
import interfaces.IDegreeDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

/**
 *
 * @author carli
 */
public class DegreeDAO implements IDegreeDAO {

    IConnectionBD connection;

    public DegreeDAO(IConnectionBD connection) {
        this.connection = connection;
    }
    

    @Override
    public List<DegreeEntity> getAllDegrees() throws PersistenceException {
         EntityManager entityManager = connection.getEntityManager(); // Obtain the EntityManager
    try {
        // Create a query to retrieve all DegreeEntity instances
        return entityManager.createQuery("SELECT d.id, d.degreeName, d.timeLimit FROM DegreeEntity d", DegreeEntity.class)
                .getResultList(); // Execute the query and return the results
    } catch (Exception e) {
        throw new PersistenceException("Error retrieving all degrees", e); // Handle any exceptions that may occur
    } finally {
        entityManager.close(); // Ensure the EntityManager is closed to free resources
    }
}

   

    @Override
    public void saveDegree(DegreeEntity degree) throws PersistenceException {
         EntityManager entityManager = connection.getEntityManager();
    EntityTransaction transaction = null;
    
    try {
        // Iniciar una transacción
        transaction = entityManager.getTransaction();
        transaction.begin();

        // Persistir la entidad (guardar en la base de datos)
        entityManager.persist(degree);

        // Confirmar la transacción
        transaction.commit();
    } catch (NoResultException e) {
        // Revertir la transacción si ocurre un error
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        throw new PersistenceException("Error saving degree.", e);
    } finally {
        entityManager.close(); // Cerrar el EntityManager
    }
    }

    @Override
    public void updateDegree(DegreeEntity degree) throws PersistenceException {
     EntityManager entityManager = connection.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();

    try {
        transaction.begin();  // Inicia la transacción

        DegreeEntity degreeNew = entityManager.find(DegreeEntity.class, degree.getId());
        
        if (degreeNew != null) {
            // Actualiza los campos necesarios
            degreeNew.setTimeLimit(degree.getTimeLimit());
            // Agrega otros campos que necesites actualizar

            entityManager.merge(degreeNew); // Realiza el merge de la entidad actualizada
        } else {
            throw new PersistenceException("Degree not found.");
        }
        
        transaction.commit(); // Confirma la transacción
    } catch (NoResultException e) {
        if (transaction.isActive()) transaction.rollback(); // Revierte si ocurre un error
        throw new PersistenceException("Degree not found.", e);
    } catch (Exception e) {
        if (transaction.isActive()) transaction.rollback(); // Revierte si ocurre un error
        throw new PersistenceException("Error updating Degree", e);
    } finally {
        entityManager.close(); // Cierra el EntityManager
    }
     
        } 
        @Override
    public void deleteDegree(Long degreeId) throws PersistenceException {
    EntityManager entityManager = connection.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    
    try {
        transaction.begin();  // Inicia la transacción
        
        DegreeEntity degree = entityManager.find(DegreeEntity.class, degreeId);
        if (degree != null) {
            entityManager.remove(degree); // Pasa la entidad encontrada a `remove`
        } else {
            throw new PersistenceException("Degree not found for ID: " + degreeId);
        }
        
        transaction.commit(); // Confirma la transacción para aplicar la eliminación
    } catch (NoResultException e) {
        if (transaction.isActive()) transaction.rollback(); // Revierte en caso de excepción
        throw new PersistenceException("Degree not found.", e);
    } catch (Exception e) {
        if (transaction.isActive()) transaction.rollback(); // Revierte en caso de excepción
        throw new PersistenceException("Error deleting Degree", e);
    } finally {
        entityManager.close();
    }
    
}


    @Override
    public DegreeEntity findDegreeForId(Long degreeId) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try{
            return entityManager.find(DegreeEntity.class, degreeId);
                    
        }catch (NoResultException e) {
            throw new PersistenceException("Dregree not found.", e);
        } finally {
            entityManager.close();
        }
            
       }

    @Override
    public List<DegreeEntity> obterCarrerasPaguinado(int limit, int offtel) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            
            return entityManager.createQuery("SELECT s FROM DegreeEntity s ", DegreeEntity.class)
                    .setFirstResult(offtel)
                    .setMaxResults(limit)
                    .getResultList();
            
        } catch (NoResultException e) {
            throw new PersistenceException("Degree not found", e);
        } catch (Exception e) {
            Logger.getLogger(DegreeDAO.class.getName()).log(Level.SEVERE, "Failed to save student", e);
            throw new PersistenceException("Error retrieving Degree List", e);
        } finally {
            entityManager.close();
        }
    }


    }



