/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.SoftwareEntity;
import exception.PersistenceException;
import interfaces.ISoftwareDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author carli
 */
public class SoftwareDAO implements ISoftwareDAO {

    IConnectionBD connectionBD;

    public SoftwareDAO(IConnectionBD connectionBD) {
        this.connectionBD = connectionBD;
    }

    @Override
    public SoftwareEntity saveSoftware(SoftwareEntity software) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (software.getId() == null) {
                entityManager.persist(software);
            } else {
                entityManager.merge(software);
            }
            entityManager.getTransaction().commit();
            return software;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new PersistenceException("Error saving rule", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();  // Ensure EntityManager is closed
            }
        }
    }

    @Override
    public SoftwareEntity deleteSoftware(Long softwareId) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            // Find the software by its ID
            SoftwareEntity software = entityManager.find(SoftwareEntity.class, softwareId);
            if (software != null) {
                entityManager.remove(software);  // Remove the found software
            } else {
                throw new PersistenceException("Software with ID " + softwareId + " not found.");
            }

            entityManager.getTransaction().commit();  // Commit the transaction
            return software;  // Return the removed software
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();  // Rollback if there was an error
            }
            throw new PersistenceException("Error deleting software", e);  // Re-throw the exception
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();  // Ensure EntityManager is closed
            }
        }
    }

    @Override
    public List<SoftwareEntity> getSoftwarebyComputer(Long idCom, boolean has) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            String jpql;
            if (has) {
                jpql = "SELECT s FROM SoftwareEntity s JOIN s.softwareComputer sc WHERE cs.computer.id = :idCom";
            } else {
                jpql = "SELECT s FROM SoftwareEntity s WHERE s NOT IN (SELECT sc.software FROM ComputerSoftwareEntity sc WHERE cs.computer.id = :idCom)";
            }

            TypedQuery<SoftwareEntity> query = entityManager.createQuery(jpql, SoftwareEntity.class);
            query.setParameter("idCom", idCom);

            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving software by computer", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();  // Ensure EntityManager is closed
            }
        }
    }

    @Override
    public SoftwareEntity getSoftwareById(Long softwareId) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            // Define la consulta JPQL para buscar software por ID
            String jpql = "SELECT s FROM SoftwareEntity s WHERE s.id = :softwareId";

            TypedQuery<SoftwareEntity> query = entityManager.createQuery(jpql, SoftwareEntity.class);
            query.setParameter("softwareId", softwareId);

            return query.getSingleResult(); // Retorna el único resultado

        } catch (NoResultException e) {
            return null; // Maneja el caso en que no se encuentra el software
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving software by ID", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();  // Asegura que el EntityManager se cierra
            }
        }
    }

    @Override
    public List<SoftwareEntity> softwareListPaginated(int limit, int page) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            // Calcular el offset
            int offset = (page - 1) * limit;

            // Verificar si el campo isDeleted está presente y es correcto
            return entityManager.createQuery("SELECT s FROM SoftwareEntity s WHERE s.id >= 1", SoftwareEntity.class)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (NoResultException e) {
            throw new PersistenceException("No software found", e);
        } catch (Exception e) {
            throw new PersistenceException("Error retrieving software list", e);
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close();  // Asegúrate de cerrar el EntityManager
            }
        }
    }

    public List<SoftwareEntity> getSoftwareInstalledByComputer(Long idCom) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();

        String jpql = """
            SELECT sw FROM SoftwareEntity sw
            JOIN  sw.computerSoftwares c
            WHERE c.computer.id = :idCom
        """;

        TypedQuery<SoftwareEntity> query = entityManager.createQuery(jpql, SoftwareEntity.class);
        query.setParameter("idCom", idCom);
        return query.getResultList();
    }

    public List<SoftwareEntity> softwareNoInstall(Long idCom) throws PersistenceException {
        EntityManager entityManager = connectionBD.getEntityManager();
        try {
            // Consulta JPQL
            String jpql = """
                SELECT s FROM SoftwareEntity s
                WHERE s.id NOT IN (
                    SELECT cs.software.id
                    FROM ComputerSoftwareEntity cs
                    WHERE cs.computer.id = :idCom
                )
            """;

            TypedQuery<SoftwareEntity> query = entityManager.createQuery(jpql, SoftwareEntity.class);
            query.setParameter("idCom", idCom);

            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

}
