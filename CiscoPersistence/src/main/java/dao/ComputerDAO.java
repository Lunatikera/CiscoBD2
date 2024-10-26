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
public class ComputerDAO implements IComputerDAO{
    IConnectionBD connection;

    public ComputerDAO(IConnectionBD conection) {
        this.connection = conection;
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
            ComputerEntity computerDe = entityManager.createQuery("SELECT c.id,c.ipAdress,c.machineNumber,c.status,c.computerType FROM ComputerEntity c WHERE c.ipAdress = :ipAdress", ComputerEntity.class)
                    .setParameter("ipAdress", computerIp)
                    .getSingleResult();

            if (computerDe != null) {
                computerDe.setStatus(ComputerStatus.No_Disponible);
                entityManager.merge(computerDe);
            } else {
                throw new PersistenceException("Computer not found.");
            }
        } catch (NoResultException e) {
            throw new PersistenceException("Computer not found.", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateComputer(ComputerEntity computer) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            // Check if the student exists
            ComputerEntity existingComputer = entityManager.createQuery("SELECT c.id,c.ipAdress,c.machineNumber,c.status,c.computerType FROM ComputerEntity c WHERE c.ipAdress = :ipAdress", ComputerEntity.class)
                    .setParameter("ipAdress", computer.getIpAdress())
                    .getSingleResult();

            if (existingComputer != null) {
                // Update fields as needed
                existingComputer.setComputerType(computer.getComputerType());
                existingComputer.setMachineNumber(computer.getMachineNumber());
                existingComputer.setStatus(computer.getStatus());
                // Add other fields to update as necessary

                entityManager.merge(existingComputer); // Update the entity
            } else {
                throw new PersistenceException("Computer not found.");
            }
        } catch (NoResultException e) {
            throw new PersistenceException("Computer not found.", e);
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }

    @Override
    public ComputerEntity findByIPComputer(String computerIp) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            return entityManager.createQuery(
                    "SELECT c.id,c.ipAdress,c.machineNumber,c.status,c.computerType FROM ComputerEntity c WHERE c.ipAdress = :ipAdress", ComputerEntity.class)
                    .setParameter("ipAdress", computerIp)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null; // Handle case where no computer is found
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ComputerEntity> computerListByAcademyPaginated(int offset, int limit,Long IdLab) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
//            LaboratoryEntity laboratory = entityManager.createQuery("SELECT l.id,l.labName,l.masterPassword,l.startTime,l.endTime,l.isDeleted FROM LaboratoryEntity l WHERE l.id = :labId", LaboratoryEntity.class)
//                    .setParameter("labId", IdLab)
//                    .getSingleResult();
            
            return entityManager.createQuery("SELECT new entities.ComputerEntity(c.id,c.ipAdress,c.machineNumber,c.status,c.computerType) FROM ComputerEntity c WHERE c.laboratory.id = :laboratory", ComputerEntity.class)
                    .setParameter("laboratory", IdLab)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (NoResultException e) {
            throw new PersistenceException("Laboratory not found", e);
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving computer list by laboratory", e);
        } finally {
            entityManager.close();
        }
    }
    
    

}
