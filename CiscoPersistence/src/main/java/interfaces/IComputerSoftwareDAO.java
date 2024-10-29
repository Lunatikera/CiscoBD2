/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import entities.ComputerSoftwareEntity;
import exception.PersistenceException;

/**
 *
 * @author aleja
 */
public interface IComputerSoftwareDAO {

    public void saveComputerSoftware(ComputerSoftwareEntity computerSoftwareEntity) throws PersistenceException;

    public void deleteComputerSoftware(Long computerId, Long softwareId) throws PersistenceException;
}
