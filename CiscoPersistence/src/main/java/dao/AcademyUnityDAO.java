/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.AcademicUnityEntity;
import exception.PersistenceException;
import interfaces.IAcademyUnityDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author carli
 */
public class AcademyUnityDAO implements IAcademyUnityDAO{

     IConnectionBD connectionBD;

    public AcademyUnityDAO(IConnectionBD connectionBD) {
        this.connectionBD = connectionBD;
    }
     
    @Override
    public List<AcademicUnityEntity> getAllAcademies() throws PersistenceException {
    EntityManager entityManager = connectionBD.getEntityManager(); // Obtain the EntityManager
    try {
        // Create a query to retrieve all DegreeEntity instances
        return entityManager.createQuery("SELECT a FROM AcademicUnityEntity a", AcademicUnityEntity.class)
                .getResultList(); // Execute the query and return the results
    } catch (Exception e) {
        throw new PersistenceException("Error retrieving all academy", e); // Handle any exceptions that may occur
    } finally {
        entityManager.close(); // Ensure the EntityManager is closed to free resources
    }    }
    
    
    
}
