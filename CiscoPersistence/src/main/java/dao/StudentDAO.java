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
        EntityManager entityManager = connection.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Guardar el student en la base de datos
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            return student; // Retornar la entidad guardada, puede ser útil para obtener el ID generado
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Hacer rollback en caso de error
            }
            throw new PersistenceException("Error saving laboratory", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public StudentEntity findStudentByUniqueID(Long studentId) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
             return entityManager.createQuery(
                "SELECT s FROM StudentEntity s WHERE s.unique_ID = :uniqueID", StudentEntity.class)
                .setParameter("uniqueID", studentId)
                .getSingleResult();
            
        } catch (NoResultException e) {
            return null; // Handle case where no student is found
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<StudentEntity> studentListByDegreePaginated(Long degreeId, int offset, int limit) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            
            DegreeEntity degree = entityManager.createQuery("SELECT d FROM DegreeEntity d WHERE d.id = :degreeId", DegreeEntity.class)
                    .setParameter("degreeId", degreeId)
                    .getSingleResult();
            
            return entityManager.createQuery("SELECT s FROM StudentEntity s  inner join s.studentDegrees sd inner join sd.degree d where d.id = :degree", StudentEntity.class)
                    .setParameter("degree", degree.getId())
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

   