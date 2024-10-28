/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.StudentComputerDTO;
import exception.BusinessException;

/**
 *
 * @author carli
 */
public interface IStudentComputerBO {
       public void saveComputerUse(StudentComputerDTO computerSoftwareDTO) throws BusinessException;

}
