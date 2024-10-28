/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.BlockEntity;
import exception.PersistenceException;
import interfaces.IBlockDAO;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

/**
 *
 * @author carli
 */
public class BlockDAO implements IBlockDAO {

    IConnectionBD connection;

    public BlockDAO(IConnectionBD connection) {
        this.connection = connection;
    }

    @Override
    public void saveBlock(BlockEntity block) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager(); // Obtiene el EntityManager
        EntityTransaction transaction = entityManager.getTransaction(); // Inicia una transacción

        try {
            transaction.begin(); // Comienza la transacción
            entityManager.persist(block); // Guarda la entidad en la base de datos
            transaction.commit(); // Confirma la transacción
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Revierte la transacción en caso de error
            }
            throw new PersistenceException("Error adding block", e); // Lanza la excepción
        } finally {
            entityManager.close(); // Cierra el EntityManager
        }
    }

    @Override
    public void deleteBlock(Long blockId, LocalDate withdrawalDate) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();  // Inicia la transacción

            BlockEntity block = entityManager.find(BlockEntity.class, blockId);
            if (block != null) {
                block.setWithdrawalDate(withdrawalDate); // Establece la fecha de retiro
                entityManager.merge(block); // Actualiza la entidad
            } else {
                throw new PersistenceException("Block not found for ID: " + blockId);
            }

            transaction.commit(); // Confirma la transacción para aplicar la actualización
        } catch (NoResultException e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Revierte en caso de excepción
            }
            throw new PersistenceException("Block not found.", e);
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Revierte en caso de excepción
            }
            throw new PersistenceException("Error updating block withdrawal date", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<BlockEntity> blockListByRulePaginated(int offset, int limit, Long idRule) throws PersistenceException {
        if (idRule == null) {
            throw new PersistenceException("El id de la regla no puede ser nulo.");
        }

        EntityManager entityManager = connection.getEntityManager();
        try {

            return entityManager.createQuery("SELECT b FROM BlockEntity b WHERE b.rule.id = :idRule and b.withdrawalDate is null", BlockEntity.class)
                    .setParameter("idRule", idRule)
                    .setFirstResult(offset)
                    .setMaxResults(limit)
                    .getResultList();

        } catch (NoResultException e) {
            throw new PersistenceException("No se encontraron bloques", e);
        } catch (Exception e) {
            throw new PersistenceException("Error al recuperar la lista de bloques por regla", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public BlockEntity findById(Long idBlock) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        try {
            return entityManager.find(BlockEntity.class, idBlock);

        } catch (NoResultException e) {
            throw new PersistenceException("Dregree not found.", e);
        } finally {
            entityManager.close();
        }
    }

}
