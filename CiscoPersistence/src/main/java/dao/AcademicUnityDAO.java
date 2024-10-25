/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import exception.PersistenceException;
import interfaces.IAcademicUnityDAO;

/**
 *
 * @author carli
 */
public class AcademicUnityDAO implements IAcademicUnityDAO {
    IConnectionBD connection;

    public AcademicUnityDAO(IConnectionBD connection) {
        this.connection = connection;
    }
    
    @Override
    public void getAcademiesList() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
