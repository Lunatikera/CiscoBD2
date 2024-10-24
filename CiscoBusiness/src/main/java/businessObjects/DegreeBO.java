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
import mappers.DegreeMapper;

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
    
}
