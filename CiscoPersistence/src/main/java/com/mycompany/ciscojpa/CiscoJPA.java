/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ciscojpa;

import entities.AcademicUnityEntity;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author carli
 */
public class CiscoJPA {

    public static void main(String[] args) {
  // Crear una fábrica de administradores de entidades
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CiscoJPA");
        EntityManager em = emf.createEntityManager();

        try {
            // Crear una nueva instancia de AcademicUnityEntity
            AcademicUnityEntity academicUnity = new AcademicUnityEntity("Science Department", new ArrayList<>());

            // Iniciar una transacción
            em.getTransaction().begin();

            // Persistir la entidad en la base de datos
            em.persist(academicUnity);

            // Confirmar la transacción
            em.getTransaction().commit();

            // Imprimir la entidad persistida con su ID asignado
            System.out.println("Entidad persistida: " + academicUnity);

        } catch (Exception e) {
            // Si hay un error, deshacer la transacción
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Cerrar el EntityManager y la EntityManagerFactory
            em.close();
            emf.close();
        }
    }
}
   