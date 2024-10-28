/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.ComputerSoftwareEntity;
import exception.PersistenceException;
import interfaces.IComputerSoftwareDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

/**
 *
 * @author aleja
 */
public class ComputerSoftwareDAO implements IComputerSoftwareDAO {

    IConnectionBD connection;

    public ComputerSoftwareDAO(IConnectionBD connection) {
        this.connection = connection;
    }

    public void saveComputerSoftware(ComputerSoftwareEntity computerSoftwareEntity) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        EntityTransaction transaction = null;
        transaction = entityManager.getTransaction();
        try {
            transaction.begin(); // Iniciar la transacción
            entityManager.persist(computerSoftwareEntity); // Persistir la entidad
            transaction.commit(); // Confirmar la transacción
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Revertir cambios en caso de error
            }
            throw new PersistenceException("Error al guardar el software de computadora: " + e.getMessage(), e); // Lanzar excepción personalizada
        }
    }

    public void deleteComputerSoftware(Long computerId, Long softwareId) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin(); // Iniciar la transacción

            // Buscar la entidad `ComputerSoftwareEntity` por `computerId` y `softwareId`
            ComputerSoftwareEntity computerSoftwareEntity = entityManager.createQuery(
                    "SELECT cs FROM ComputerSoftwareEntity cs "
                    + "WHERE cs.computer.id = :computerId AND cs.software.id = :softwareId",
                    ComputerSoftwareEntity.class)
                    .setParameter("computerId", computerId)
                    .setParameter("softwareId", softwareId)
                    .getSingleResult();

            // Eliminar la entidad si existe
            if (computerSoftwareEntity != null) {
                entityManager.remove(computerSoftwareEntity);
            }

            transaction.commit(); // Confirmar la transacción
        } catch (NoResultException e) {
            // Caso en que no se encuentre la relación especificada
            throw new PersistenceException("No se encontró la relación entre computadora y software especificada.", e);
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Revertir cambios en caso de error
            }
            throw new PersistenceException("Error al eliminar el software de computadora: " + e.getMessage(), e); // Lanzar excepción personalizada
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                entityManager.close(); // Cerrar el EntityManager
            }
        }
    }
}
