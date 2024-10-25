/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dto.DegreeDTO;
import entities.DegreeEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IDegreeBO;
import interfaces.IDegreeDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.DegreeMapper;
import tools.Tools;

/**
 *
 * @author carli
 */
public class DegreeBO implements IDegreeBO{
    IDegreeDAO degreeDAO;

    public DegreeBO(IDegreeDAO degreeDAO) {
        this.degreeDAO = degreeDAO;
    }

    @Override
    public List<DegreeDTO> getAllDegrees() throws BusinessException {
        try {
        List<DegreeEntity> degreeEntities = degreeDAO.getAllDegrees(); // Retrieve all DegreeEntity instances
        return DegreeMapper.toDTOList(degreeEntities); // Collect results into a List<DegreeDTO>
    } catch (PersistenceException e) {
        throw new BusinessException("Error retrieving degrees", e); // Handle persistence exceptions
    }
}

    @Override
    public List<DegreeDTO> obterCarrerasPaguinado(int limit, int page) throws BusinessException {
        try {
           int offset =  Tools.ReturnOFFSETMySQL(limit, page);
        List<DegreeEntity> degreeEntities = degreeDAO.obterCarrerasPaguinado(limit, offset);  // Llama al método paginado en el DAO
        return DegreeMapper.toDTOList(degreeEntities);  // Convierte las entidades a DTO
    } catch (PersistenceException e) {
        Logger.getLogger(DegreeBO.class.getName()).log(Level.SEVERE, "Failed to save student", e);
        throw new BusinessException("Error retrieving paginated degrees", e);
    }
    }

    @Override
    public void saveDegree(DegreeDTO degree) throws BusinessException {
        try {
        DegreeEntity degreeEntity = DegreeMapper.toEntity(degree);  // Convierte el DTO a una entidad
        degreeDAO.saveDegree(degreeEntity);  // Llama al DAO para guardar la entidad
    } catch (PersistenceException e) {
        throw new BusinessException("Error saving degree", e);
    }
    }

    @Override
    public void deleteDegree(Long degreeId) throws BusinessException {
        try {
        degreeDAO.deleteDegree(degreeId);  // Llama al DAO para eliminar la entidad
    } catch (PersistenceException e) {
        throw new BusinessException("Error deleting degree", e);
    }
    }

    @Override
    public void updateDegree(DegreeDTO degree) throws BusinessException {
        try {
        DegreeEntity degreeEntity = DegreeMapper.toEntity(degree);  // Convierte el DTO a entidad
        degreeDAO.updateDegree(degreeEntity);  // Llama al DAO para actualizar la entidad
    } catch (PersistenceException e) {
        throw new BusinessException("Error updating degree", e);
    }
    }

    @Override
    public DegreeDTO findDegreeForId(Long degreeId) throws BusinessException {
        try {
        DegreeEntity degreeEntity = degreeDAO.findDegreeForId(degreeId);  // Busca la entidad por ID
        return DegreeMapper.toDTO(degreeEntity);  // Convierte la entidad en DTO
    } catch (PersistenceException e) {
        throw new BusinessException("Error finding degree by ID", e);
    }
    }
    
}
