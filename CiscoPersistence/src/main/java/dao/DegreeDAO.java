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
import javax.persistence.EntityManager;

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

}
