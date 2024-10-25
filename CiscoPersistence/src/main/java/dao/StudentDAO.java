/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.DegreeEntity;
import entities.StudentEntity;
import exception.PersistenceException;
import interfaces.IStudentDAO;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

/**
 *
 * @author carli
 */
public class StudentDAO implements IStudentDAO {

    IConnectionBD connection;

    public StudentDAO(IConnectionBD connection) {
        this.connection = connection;
    }

    @Override
    public StudentEntity saveStudent(StudentEntity student) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public StudentEntity findStudentByUniqueID(Long studentId) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            return entityManager.createQuery(
                    "SELECT s.id, s.firstLastname,s.secondLastName,s.unique_ID,s.password,s.enrollmentStatus FROM StudentEntity s WHERE s.unique_ID = :uniqueID", StudentEntity.class)
                    .setParameter("uniqueID", studentId)
                    .getSingleResult();
            
        } catch (NoResultException e) {
            return null; // Handle case where no student is found
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<StudentEntity> studentListByDegreePaginated(String degreeName, int offset, int limit) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            DegreeEntity degree = entityManager.createQuery("SELECT d FROM DegreeEntity d WHERE d.degreeName = :degreeName", DegreeEntity.class)
                    .setParameter("degreeName", degreeName)
                    .getSingleResult();

            return entityManager.createQuery("SELECT s FROM StudentEntity s WHERE s.degree = :degree AND s.isDeleted = false", StudentEntity.class)
                    .setParameter("degree", degree)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (NoResultException e) {
            throw new PersistenceException("Degree not found", e);
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving student list by degree", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateStudent(StudentEntity student) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            // Check if the student exists
            StudentEntity existingStudent = entityManager.createQuery("SELECT s FROM StudentEntity s WHERE s.unique_ID = :uniqueId", StudentEntity.class)
                    .setParameter("uniqueId", student.getUnique_ID())
                    .getSingleResult();

            if (existingStudent != null) {
                // Update fields as needed
                existingStudent.setNames(student.getNames());
                existingStudent.setFirstLastname(student.getFirstLastname());
                existingStudent.setSecondLastName(student.getSecondLastName());
                existingStudent.setPassword(student.getPassword());
                existingStudent.setEnrollmentStatus(student.getEnrollmentStatus());
                // Add other fields to update as necessary

                entityManager.merge(existingStudent); // Update the entity
            } else {
                throw new PersistenceException("Student not found.");
            }
        } catch (NoResultException e) {
            throw new PersistenceException("Student not found.", e);
        } finally {
            entityManager.close(); // Close the EntityManager
        }
    }
}

   