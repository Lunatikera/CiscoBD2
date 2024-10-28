/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.StudentDegreeEntity;
import exception.PersistenceException;
import interfaces.IStudentDegreeDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author carli
 */
public class StudentDegreeDAO implements IStudentDegreeDAO {

    IConnectionBD connection;

    public StudentDegreeDAO(IConnectionBD connection) {
        this.connection = connection;
    }

    @Override
    public void save(StudentDegreeEntity studentDegree) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(studentDegree);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException("Error saving student degree", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void update(StudentDegreeEntity studentDegree) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(studentDegree);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException("Error updating student degree", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(Long studentDegreeId) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            StudentDegreeEntity studentDegree = entityManager.find(StudentDegreeEntity.class, studentDegreeId);
            if (studentDegree != null) {
                entityManager.remove(studentDegree);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException("Error deleting student degree", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<StudentDegreeEntity> findByStudentId(Long studentId) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            TypedQuery<StudentDegreeEntity> query = entityManager.createQuery(
                    "SELECT d FROM StudentDegreeEntity d JOIN d.student s WHERE s.unique_ID = :studentId",
                    StudentDegreeEntity.class
            );
            query.setParameter("studentId", studentId);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenceException("Error finding degrees by student ID", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<StudentDegreeEntity> findByDegreeId(Long degreeId) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            TypedQuery<StudentDegreeEntity> query = entityManager.createQuery(
                    "SELECT s FROM StudentDegreeEntity s WHERE s.degree.id = :degreeId", StudentDegreeEntity.class);
            query.setParameter("degreeId", degreeId);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenceException("Error finding students by degree ID", e);
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
}
