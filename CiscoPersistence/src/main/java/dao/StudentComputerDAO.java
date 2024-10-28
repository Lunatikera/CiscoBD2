/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.StudentComputerEntity;
import exception.PersistenceException;
import interfaces.IStudentComputerDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author carli
 */
public class StudentComputerDAO implements IStudentComputerDAO {

    IConnectionBD connection;

    public StudentComputerDAO(IConnectionBD connection) {
        this.connection = connection;
    }

    @Override
    public void saveComputerUse(StudentComputerEntity computerSoftwareEntity) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (computerSoftwareEntity.getId() == null) {
                entityManager.persist(computerSoftwareEntity);
            } else {
                entityManager.merge(computerSoftwareEntity);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException("Error saving rule", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();  // Ensure EntityManager is closed
            }
        }
    }

}
