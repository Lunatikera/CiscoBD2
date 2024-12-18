/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.ConnectionDB;
import connection.IConnectionBD;
import entities.ComputerEntity;
import entities.LaboratoryEntity;
import entities.SoftwareEntity;
import entities.StudentComputerEntity;
import enums.ComputerStatus;
import exception.PersistenceException;
import interfaces.IComputerDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author carli
 */
public class ComputerDAO implements IComputerDAO {

    IConnectionBD connection;

    public ComputerDAO(IConnectionBD conection) {
        this.connection = conection;
    }

    @Override
    public List<ComputerEntity> getAllComputer() throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager(); // Obtain the EntityManager
        try {
            // Create a query to retrieve all DegreeEntity instances
            return entityManager.createQuery("SELECT c FROM ComputerEntity c", ComputerEntity.class)
                    .getResultList(); // Execute the query and return the results
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving all laboratory", e); // Handle any exceptions that may occur
        } finally {
            entityManager.close(); // Ensure the EntityManager is closed to free resources
        }
    }

    @Override
    public ComputerEntity saveComputer(ComputerEntity computer) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            // Guardar el laboratorio en la base de datos
            entityManager.persist(computer);
            entityManager.getTransaction().commit();
            return computer; // Retornar la entidad guardada, puede ser útil para obtener el ID generado
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Hacer rollback en caso de error
            }
            throw new PersistenceException("Error saving computer", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteComputer(String computerIp) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();

        try {
            entityManager.getTransaction().begin(); // Start the transaction

            ComputerEntity computerDelete = this.findByIPComputer(computerIp);
            if (computerDelete != null) {
                computerDelete.setMachineNumber(0);
                entityManager.merge(computerDelete); // Merge the changes
                entityManager.flush(); // Ensure changes are flushed to the database
            } else {
                throw new PersistenceException("Computer not found.");
            }

            entityManager.getTransaction().commit(); // Commit the transaction
        } catch (NoResultException e) {
            throw new PersistenceException("Computer not found.", e);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Rollback in case of an error
            }
            throw new PersistenceException("Error while deleting computer.", e);
        } finally {
            entityManager.close(); // Always close the entity manager
        }
    }

    @Override
    public void updateComputer(ComputerEntity computer) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            entityManager.getTransaction().begin(); // Start the transaction

            // Add other fields to update as necessary
            entityManager.merge(computer); // Update the entity
            //entityManager.flush();

            entityManager.getTransaction().commit(); // Commit the transaction
        } catch (NoResultException e) {
            throw new PersistenceException("Computer not found.", e);
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public ComputerEntity findByIPComputer(String computerIp) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        ComputerEntity newEntity= entityManager.createQuery(
                    "SELECT c FROM ComputerEntity c WHERE c.ipAdress = :ipAdress", ComputerEntity.class)
                    .setParameter("ipAdress", computerIp)
                    .getSingleResult();
        entityManager.refresh(newEntity);

        try {
            return entityManager.createQuery(
                    "SELECT c FROM ComputerEntity c WHERE c.ipAdress = :ipAdress", ComputerEntity.class)
                    .setParameter("ipAdress", computerIp)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Handle case where no computer is found
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ComputerEntity findByIdComputer(Long id) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            return entityManager.createQuery(
                    "SELECT c FROM ComputerEntity c WHERE c.id = :computerId", ComputerEntity.class)
                    .setParameter("computerId", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Manejar el caso donde no se encuentra la computadora
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ComputerEntity> computerListByAcademyPaginated(int offset, int limit, Long IdLab) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
//            LaboratoryEntity laboratory = entityManager.createQuery("SELECT l.id,l.labName,l.masterPassword,l.startTime,l.endTime,l.isDeleted FROM LaboratoryEntity l WHERE l.id = :labId", LaboratoryEntity.class)
//                    .setParameter("labId", IdLab)
//                    .getSingleResult();
            if (offset == 0) {
                return entityManager.createQuery("SELECT c FROM ComputerEntity c WHERE c.laboratory.id = :laboratory AND c.machineNumber >0", ComputerEntity.class)
                        .setParameter("laboratory", IdLab).getResultList();
            } else {
                return entityManager.createQuery("SELECT new entities.ComputerEntity(c.id,c.ipAdress,c.machineNumber,c.status,c.computerType) FROM ComputerEntity c WHERE c.laboratory.id = :laboratory AND c.machineNumber >0", ComputerEntity.class)
                        .setParameter("laboratory", IdLab)
                        .setFirstResult(offset)
                        .setMaxResults(limit)
                        .getResultList();
            }
        } catch (NoResultException e) {
            throw new PersistenceException("Laboratory not found", e);
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving computer list by laboratory", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ComputerEntity> computerListByAcademy(Long IdLab) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
//            LaboratoryEntity laboratory = entityManager.createQuery("SELECT l.id,l.labName,l.masterPassword,l.startTime,l.endTime,l.isDeleted FROM LaboratoryEntity l WHERE l.id = :labId", LaboratoryEntity.class)
//                    .setParameter("labId", IdLab)
//                    .getSingleResult();

            return entityManager.createQuery("SELECT c FROM ComputerEntity c WHERE c.laboratory.id = :laboratory AND c.machineNumber >0", ComputerEntity.class)
                    .setParameter("laboratory", IdLab).getResultList();

        } catch (NoResultException e) {
            throw new PersistenceException("Laboratory not found", e);
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving computer list by laboratory", e);
        } finally {
            entityManager.close();
        }
    }

}
