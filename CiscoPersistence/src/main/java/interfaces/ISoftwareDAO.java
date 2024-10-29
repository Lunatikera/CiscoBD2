/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.SoftwareEntity;
import exception.PersistenceException;
import java.util.List;

/**
 *
 * @author aleja
 */
public interface ISoftwareDAO {

    public SoftwareEntity deleteSoftware(Long softwareId) throws PersistenceException;

    public SoftwareEntity saveSoftware(SoftwareEntity software) throws PersistenceException;

    public List<SoftwareEntity> getSoftwarebyComputer(Long idCom, boolean has) throws PersistenceException;

    public List<SoftwareEntity> softwareListPaginated(int limit, int page) throws PersistenceException;

    public List<SoftwareEntity> softwareNoInstall(Long idCom) throws PersistenceException;

    public List<SoftwareEntity> getSoftwareInstalledByComputer(Long idCom) throws PersistenceException;
    
    public SoftwareEntity getSoftwareById(Long softwareId) throws PersistenceException;
}
