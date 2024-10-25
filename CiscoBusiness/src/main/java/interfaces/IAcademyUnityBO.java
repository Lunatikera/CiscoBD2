/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.AcademyDTO;
import exception.BusinessException;
import java.util.List;

/**
 *
 * @author aleja
 */
public interface IAcademyUnityBO {
    public List<AcademyDTO> getAllAcademies() throws BusinessException;
}
