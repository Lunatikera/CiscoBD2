/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.StudentDegreeEntity;
import exception.PersistenceException;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IStudentDegreeDAO {

    void save(StudentDegreeEntity studentDegree) throws PersistenceException;

    void update(StudentDegreeEntity studentDegree) throws PersistenceException;

    void delete(Long studentDegreeId) throws PersistenceException;

    List<StudentDegreeEntity> findByStudentId(Long studentId) throws PersistenceException;

    List<StudentDegreeEntity> findByDegreeId(Long degreeId) throws PersistenceException;
}
