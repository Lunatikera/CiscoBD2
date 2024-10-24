/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dto.AcademyDTO;
import entities.AcademicUnityEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IAcademyUnityBO;
import interfaces.IAcademyUnityDAO;
import static java.time.Clock.offset;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.AcademyUnityMapper;

/**
 *
 * @author aleja
 */
public class AcademyUnityBO implements IAcademyUnityBO{

    IAcademyUnityDAO academyUnityDAO;

    public AcademyUnityBO(IAcademyUnityDAO academyUnityDAO) {
        this.academyUnityDAO = academyUnityDAO;
    }
    
    
    @Override
    public List<AcademyDTO> getAllAcademies() throws BusinessException {
    
        try {
            List<AcademicUnityEntity> academy = academyUnityDAO.getAllAcademies();
            return AcademyUnityMapper.toDTOList(academy);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving academy list.");
        }
    }
    
}
