/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.StudentDegreeDTO;
import exception.BusinessException;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IStudentDegreeBO {
       public void save(StudentDegreeDTO studentDegree) throws BusinessException;

    public void update(StudentDegreeDTO studentDegree) throws BusinessException;

    public void delete(Long studentDegreeId) throws BusinessException;

    public List<StudentDegreeDTO> findByStudentId(Long studentId) throws BusinessException;

    public List<StudentDegreeDTO> findByDegreeId(Long degreeId) throws BusinessException;
}
 

