/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.LogInDTO;
import dto.StudentDTO;
import exception.BusinessException;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IStudentBO {

    public StudentDTO saveStudent(StudentDTO student) throws BusinessException;

    public StudentDTO findStudentByUniqueID(Long studentId) throws BusinessException;

    public List<StudentDTO> studentListByDegreePaginated(Long degreeId, int offset, int limit) throws BusinessException;

    public void updateStudent(StudentDTO student) throws BusinessException;

    public StudentDTO login(LogInDTO loginDTO) throws BusinessException;

    public boolean verifyID(Long uniqueID) throws BusinessException;
    
    public StudentDTO findStudentByID(Long studentId) throws BusinessException;

}
