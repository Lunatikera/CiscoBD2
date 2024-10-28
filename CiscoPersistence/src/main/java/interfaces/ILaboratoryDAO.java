/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.LaboratoryEntity;
import exception.PersistenceException;
import java.util.List;

/**
 *
 * @author carli
 */
public interface ILaboratoryDAO {

    public List<LaboratoryEntity> laboratoryListByAcademyPaginated(Long academyID, int limit, int offset) throws PersistenceException;

    public LaboratoryEntity findLaboratoryByID(Long LaboratoryId) throws PersistenceException;

    public LaboratoryEntity saveLaboratory(LaboratoryEntity laboratory) throws PersistenceException;

    public void updateLaboratory(LaboratoryEntity laboratory) throws PersistenceException;

    public void deleteLaboratory(Long LaboratoryId) throws PersistenceException;
    
    public List<LaboratoryEntity> laboratoryListByAcademy(Long academyID) throws PersistenceException;
    
    public List<LaboratoryEntity> obtainAllLaboratory()throws PersistenceException;

}
