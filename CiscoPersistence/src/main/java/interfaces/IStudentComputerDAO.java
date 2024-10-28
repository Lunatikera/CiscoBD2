/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.StudentComputerEntity;
import exception.PersistenceException;

/**
 *
 * @author carli
 */
public interface IStudentComputerDAO {
    public void saveComputerUse(StudentComputerEntity computerSoftwareEntity)throws PersistenceException;
    
}
