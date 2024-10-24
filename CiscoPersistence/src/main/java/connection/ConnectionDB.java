/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author carli
 */
public class ConnectionDB implements IConnectionBD {
     private EntityManagerFactory entityManagerFactory;

    public ConnectionDB() {
        try {
            this.entityManagerFactory = Persistence.createEntityManagerFactory("PacganBD");
        } catch (Exception e) {
            System.err.println("Error al inicializar el EntityManagerFactory: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene un EntityManager para realizar operaciones de persistencia.
     *
     * @return El EntityManager creado a partir de la EntityManagerFactory.
     * @throws IllegalStateException Si la EntityManagerFactory no está
     * inicializada.
     */
    @Override
    public EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            throw new IllegalStateException("EntityManagerFactory no esta inicializado.");
        }
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}


