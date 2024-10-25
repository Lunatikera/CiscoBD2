/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;


import entities.AcademicUnityEntity;
import exception.PersistenceException;
import java.util.List;

/**
 *
 * @author carli
 */

public interface IAcademyUnityDAO {
    public List<AcademicUnityEntity> getAllAcademies() throws PersistenceException;
}
