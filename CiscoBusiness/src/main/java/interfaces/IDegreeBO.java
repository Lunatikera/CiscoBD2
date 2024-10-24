/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.DegreeDTO;
import exception.BusinessException;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IDegreeBO {

   public List<DegreeDTO> getAllDegrees() throws BusinessException;
    
    public List<DegreeDTO> obterCarrerasPaguinado(int limit, int offtel) throws BusinessException;
    
    public void saveDegree (DegreeDTO degree) throws BusinessException;
    
    public void deleteDegree (Long degreeId) throws BusinessException;
    
    public void updateDegree (DegreeDTO degree) throws BusinessException;
    
    public DegreeDTO findDegreeForId (Long degreeId) throws BusinessException;

}
