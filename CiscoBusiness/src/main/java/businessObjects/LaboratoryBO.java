/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dto.LaboratoryDTO;
import entities.LaboratoryEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IAcademyUnityDAO;
import interfaces.ILaboratoryBO;
import interfaces.ILaboratoryDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.LaboratoryMapper;
import tools.Tools;

/**
 *
 * @author aleja
 */
public class LaboratoryBO implements ILaboratoryBO {

    ILaboratoryDAO laboratoryDAO;
    IAcademyUnityDAO academyDAO;

    public LaboratoryBO(ILaboratoryDAO laboratoryDAO, IAcademyUnityDAO academyDAO) {
        this.laboratoryDAO = laboratoryDAO;
        this.academyDAO=academyDAO;
    }
    
    @Override
    public List<LaboratoryDTO> getAllLaboratory() throws BusinessException {
    
        try {
            List<LaboratoryEntity> laboratory = laboratoryDAO.getAllLaboratorys();
            return LaboratoryMapper.toDTOList(laboratory);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving academy list.");
        }
    }

    @Override
    public List<LaboratoryDTO> laboratoryListByAcademyPaginated(Long academyID, int limit, int page) throws BusinessException {
        if (page < 0 || limit <= 0) {
            
            throw new BusinessException("Invalid pagination parameters.");
            
        }
        int offset = Tools.ReturnOFFSETMySQL(limit, page);
        try {
            List<LaboratoryEntity> laboratory = laboratoryDAO.laboratoryListByAcademyPaginated(academyID, limit, offset);
           
            
            return LaboratoryMapper.toDTOList(laboratory);
        } catch (PersistenceException ex) {
            Logger.getLogger(LaboratoryBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving laboratory list by academy.");
        }
    }

    @Override
    public LaboratoryDTO findLaboratoryByID(Long LaboratoryId) throws BusinessException {
        if (LaboratoryId <= 0) {
            throw new BusinessException("Invalid laboratory ID.");
        }

        try {
            LaboratoryEntity laboratory = laboratoryDAO.findLaboratoryByID(LaboratoryId);
            
            if (laboratory == null) {
                throw new BusinessException("Student not found.");
            }
            LaboratoryDTO laboratoryDTO = LaboratoryMapper.toDTO(laboratory);
            laboratoryDTO.setIdAcademy(laboratory.getAcademicUnity().getId());
            return laboratoryDTO;
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error finding student by unique ID.");
        }
    }

    @Override
    public LaboratoryDTO saveLaboratory(LaboratoryDTO laboratory) throws BusinessException {
        if (laboratory == null) {
            throw new BusinessException("LaboratoryDTO cannot be null.");
        }

        LaboratoryEntity laboratoryEntity = LaboratoryMapper.toEntity(laboratory);
        
        
        try {
            laboratoryEntity.setAcademicUnity(academyDAO.findAcademyByID(laboratory.getIdAcademy()));
            laboratoryEntity = laboratoryDAO.saveLaboratory(laboratoryEntity);
            return LaboratoryMapper.toDTO(laboratoryEntity);

        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, "Failed to save laboratory", ex);
            throw new BusinessException("An error occurred while saving the laboratory. Please try again later.");
        }
    }

    @Override
    public void updateLaboratory(LaboratoryDTO laboratory) throws BusinessException {
        if (laboratory == null || laboratory.getId() == null || laboratory.getId() <= 0) {
            throw new BusinessException("Invalid laboratory data.");
        }

        try {
            
            LaboratoryEntity laboratoryEntity = LaboratoryMapper.toEntity(laboratory);
            laboratoryEntity.setAcademicUnity(academyDAO.findAcademyByID(laboratory.getIdAcademy()));
            laboratoryDAO.updateLaboratory(laboratoryEntity);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error updating laboratory.");
        }
    }

    @Override
    public void deleteLaboratory(Long LaboratoryId) throws BusinessException {
       // Validar el ID del laboratorio
    if (LaboratoryId == null || LaboratoryId <= 0) {
        throw new BusinessException("Invalid laboratory ID.");
    }

    try {
        laboratoryDAO.deleteLaboratory(LaboratoryId);
    } catch (PersistenceException ex) {
        Logger.getLogger(LaboratoryBO.class.getName()).log(Level.SEVERE, "Error deleting laboratory with ID: " + LaboratoryId, ex);
        throw new BusinessException("Error deleting laboratory with ID: " + LaboratoryId);
    }
    }
    @Override
    public List<LaboratoryDTO> laboratoryListByAcademy(Long academyID) throws BusinessException{
        try {
            List<LaboratoryEntity> laboratory = laboratoryDAO.laboratoryListByAcademy(academyID);
            
            return LaboratoryMapper.toDTOList(laboratory);
        } catch (PersistenceException ex) {
            Logger.getLogger(LaboratoryBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving laboratory list by academy.");
        }
    }
    
}
