/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dto.SoftwareDTO;
import entities.SoftwareEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.ISoftwareBO;
import interfaces.ISoftwareDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.RuleMapper;
import mappers.SoftwareMapper;
import tools.Tools;

/**
 *
 * @author aleja
 */
public class SoftwareBO implements ISoftwareBO {

    ISoftwareDAO softwareDAO;

    public SoftwareBO(ISoftwareDAO softwareDAO) {
        this.softwareDAO = softwareDAO;
    }

    @Override
    public SoftwareDTO deleteSoftware(Long software) throws BusinessException {
        if (software == null || software <= 0) {
            throw new BusinessException("Invalid software ID.");
        }

        try {
            SoftwareEntity deletedSoftware = softwareDAO.deleteSoftware(software);
            return SoftwareMapper.toDTO(deletedSoftware);
        } catch (PersistenceException e) {
            throw new BusinessException("Error deleting software", e);
        }
    }

    @Override
    public SoftwareDTO saveSoftware(SoftwareDTO softwareDTO) throws BusinessException {
        // Validación de entrada
        if (softwareDTO == null) {
            throw new BusinessException("SoftwareDTO cannot be null.");
        }

        // Conversión de DTO a entidad
        SoftwareEntity softwareEntity = SoftwareMapper.toEntity(softwareDTO);

        try {
            // Guardar el software en la base de datos
            softwareEntity = softwareDAO.saveSoftware(softwareEntity);
            // Convertir la entidad guardada de vuelta a DTO
            return SoftwareMapper.toDTO(softwareEntity);

        } catch (PersistenceException e) {
            Logger.getLogger(SoftwareBO.class.getName()).log(Level.SEVERE, "Failed to save software", e);
            throw new BusinessException("An error occurred while saving the software. Please try again later.");
        }
    }

    @Override
    public List<SoftwareDTO> getSoftwarebyComputer(Long idCom, boolean has) throws BusinessException {
        if (idCom == null || idCom <= 0) {
            throw new BusinessException("Invalid software ID.");
        }

        try {
            List<SoftwareEntity> softwareEntities = softwareDAO.getSoftwarebyComputer(idCom, has);
            // Convert List<SoftwareEntity> to List<SoftwareDTO>
            return SoftwareMapper.toDTOList(softwareEntities);
        } catch (PersistenceException e) {
            throw new BusinessException("Error retrieving software by computer", e);
        }
    }

    public SoftwareDTO getSoftwareById(Long softwareId) throws BusinessException {
    if (softwareId == null || softwareId <= 0) {
        throw new BusinessException("Invalid software ID.");
    }

    try {
        SoftwareEntity softwareEntity = softwareDAO.getSoftwareById(softwareId);
        
        if (softwareEntity == null) {
            throw new BusinessException("Software not found.");
        }

        // Convert SoftwareEntity to SoftwareDTO
        return SoftwareMapper.toDTO(softwareEntity);
        
    } catch (PersistenceException e) {
        throw new BusinessException("Error retrieving software by ID", e);
    }
}
    @Override
    public List<SoftwareDTO> getSoftwareNotInstalledByComputer(Long idCom) throws BusinessException {
        if (idCom == null || idCom <= 0) {
            throw new BusinessException("Invalid computer ID.");
        }

        try {
            List<SoftwareEntity> softwareEntities = softwareDAO.softwareNoInstall(idCom);
            // Convertir List<SoftwareEntity> a List<SoftwareDTO>
            return SoftwareMapper.toDTOList(softwareEntities);
        } catch (PersistenceException e) {
            throw new BusinessException("Error retrieving software not installed on computer", e);
        }
    }
    
    @Override
     public List<SoftwareDTO> getSoftwareInstalledByComputer(Long idCom) throws BusinessException {
        if (idCom == null || idCom <= 0) {
            throw new BusinessException("Invalid computer ID.");
        }

        try {
            List<SoftwareEntity> softwareEntities = softwareDAO.getSoftwareInstalledByComputer(idCom);
            // Convertir List<SoftwareEntity> a List<SoftwareDTO>
            return SoftwareMapper.toDTOList(softwareEntities);
        } catch (PersistenceException e) {
            throw new BusinessException("Error retrieving software installed on computer", e);
        }
    }
     
    @Override
    public List<SoftwareDTO> softwareListPaginated(int limit, int page) throws BusinessException {
        if (page < 0 || limit <= 0) {

            throw new BusinessException("Invalid pagination parameters.");

        }
        int offset = Tools.ReturnOFFSETMySQL(limit, page);
        try {
            List<SoftwareEntity> software = softwareDAO.softwareListPaginated(limit, page);

            return SoftwareMapper.toDTOList(software);
        } catch (PersistenceException ex) {
            Logger.getLogger(LaboratoryBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving software list.");
        }
    }

}
