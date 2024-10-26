/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.StudentEntity;
import exception.PersistenceException;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IStudentDAO {
    
    
    public StudentEntity saveStudent(StudentEntity student) throws PersistenceException;

    public StudentEntity findStudentByUniqueID(Long studentId) throws PersistenceException ;

    public List<StudentEntity> studentListByDegreePaginated(Long degreeId, int offset, int limit) throws PersistenceException;

    public void updateStudent(StudentEntity student) throws PersistenceException;
    
}
