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

}
