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

    public void save(StudentDegreeEntity studentDegree) throws PersistenceException;

    public void update(StudentDegreeEntity studentDegree) throws PersistenceException;

    public void delete(Long studentDegreeId) throws PersistenceException;

    public List<StudentDegreeEntity> findByStudentId(Long studentId) throws PersistenceException;

    public List<StudentDegreeEntity> findByDegreeId(Long degreeId) throws PersistenceException;
}
