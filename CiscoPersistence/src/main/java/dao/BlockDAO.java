/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import entities.BlockEntity;
import exception.PersistenceException;
import interfaces.IBlockDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

/**
 *
 * @author carli
 */
public class BlockDAO implements IBlockDAO{
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
    }    }

    @Override
    public void deleteBlock(Long degreeId) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    
    try {
        transaction.begin();  // Inicia la transacción
        
        BlockEntity degree = entityManager.find(BlockEntity.class, degreeId);
        if (degree != null) {
            entityManager.remove(degree); // Pasa la entidad encontrada a `remove`
        } else {
            throw new PersistenceException("Degree not found for ID: " + degreeId);
        }
        
        transaction.commit(); // Confirma la transacción para aplicar la eliminación
    } catch (NoResultException e) {
        if (transaction.isActive()) transaction.rollback(); // Revierte en caso de excepción
        throw new PersistenceException("Degree not found.", e);
    } catch (Exception e) {
        if (transaction.isActive()) transaction.rollback(); // Revierte en caso de excepción
        throw new PersistenceException("Error deleting Degree", e);
    } finally {
        entityManager.close();
    }
    }

    @Override
    public List<BlockEntity> blockListByRulePaginated(int offset, int limit, Long idRule) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
    try {
        // Verificamos si el offset es 0 para decidir si hacer paginación
        if (offset == 0) {
            // Devuelve todos los bloques relacionados con la regla especificada
            return entityManager.createQuery("SELECT b FROM BlockEntity b WHERE b.rule.id = :ruleId", BlockEntity.class)
                                .setParameter("ruleId", idRule)
                                .getResultList();
        } else {
            // Devuelve una lista paginada
            return entityManager.createQuery("SELECT b FROM BlockEntity b WHERE b.rule.id = :ruleId", BlockEntity.class)
                                .setParameter("ruleId", idRule)
                                .setFirstResult(offset)
                                .setMaxResults(limit)
                                .getResultList();
        }
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
        try{
            return entityManager.find(BlockEntity.class, idBlock);
                    
        }catch (NoResultException e) {
            throw new PersistenceException("Dregree not found.", e);
        } finally {
            entityManager.close();
        }    }
    
}
