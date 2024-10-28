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
            return entityManager.createQuery("SELECT d FROM DegreeEntity d", DegreeEntity.class)
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

        try {
            DegreeEntity degreeNew = this.findDegreeForId(degree.getId());
            if (degreeNew != null) {
                // Update fields as needed
                degreeNew.setTimeLimit(degree.getTimeLimit());

                // Add other fields to update as necessary
                entityManager.merge(degreeNew); // Update the entity
            } else {
                throw new PersistenceException("Student not found.");
            }
        } catch (NoResultException e) {
            throw new PersistenceException("Student not found.", e);
        } finally {
            entityManager.close(); // Close the EntityManager
        }

    }

    @Override
    public void deleteDegree(Long degreeId) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            entityManager.find(DegreeEntity.class, degreeId);

            if (degreeId != null) {
                entityManager.remove(degreeId);
            } else {
                throw new PersistenceException("Degree not found.");
            }
        } catch (NoResultException e) {
            throw new PersistenceException("Degree not found.", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public DegreeEntity findDegreeForId(Long degreeId) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            return entityManager.find(DegreeEntity.class, degreeId);

        } catch (NoResultException e) {
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

    @Override
    public List<DegreeEntity> getDegreesByStudent(Long studentID) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            
                 return entityManager.createQuery(
                "SELECT d FROM DegreeEntity d JOIN d.studentDegrees sd JOIN sd.student s WHERE s.unique_ID = :studentID", DegreeEntity.class)
                .setParameter("studentID", studentID)
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
