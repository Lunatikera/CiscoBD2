/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dto.StudentDegreeDTO;
import entities.StudentDegreeEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IDegreeDAO;
import interfaces.IStudentDAO;
import interfaces.IStudentDegreeBO;
import interfaces.IStudentDegreeDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carli
 */
public class StudentDegreeBO implements IStudentDegreeBO {

    IStudentDegreeDAO studentDegreeDAO;
    IStudentDAO studentDAO;
    IDegreeDAO degreeDAO;

    public StudentDegreeBO(IStudentDegreeDAO studentDegreeDAO, IStudentDAO studentDAO, IDegreeDAO degreeDAO) {
        this.studentDegreeDAO = studentDegreeDAO;
        this.studentDAO = studentDAO;
        this.degreeDAO = degreeDAO;
    }

    @Override
    public void save(StudentDegreeDTO studentDegree) throws BusinessException {
        try {
            StudentDegreeEntity studentDegreeEntity = new StudentDegreeEntity();
            studentDegreeEntity.setId(studentDegree.getId());
            studentDegreeEntity.setRemainingTime(studentDegree.getRemainingTime());
            studentDegreeEntity.setStudent(studentDAO.findStudentByUniqueID(studentDegree.getUnique_ID()));
            studentDegreeEntity.setDegree(degreeDAO.findDegreeForId(studentDegree.getIdDegree()));

            studentDegreeDAO.save(studentDegreeEntity);
        } catch (Exception e) {
            throw new BusinessException("Error saving student degree", e);
        }
    }

    @Override
    public void update(StudentDegreeDTO studentDegree) throws BusinessException {
        try {
            StudentDegreeEntity studentDegreeEntity = new StudentDegreeEntity();
            studentDegreeEntity.setId(studentDegree.getId());
            studentDegreeEntity.setRemainingTime(studentDegree.getRemainingTime());
            studentDegreeEntity.setStudent(studentDAO.findStudentByUniqueID(studentDegree.getUnique_ID()));
            studentDegreeEntity.setDegree(degreeDAO.findDegreeForId(studentDegree.getIdDegree()));

            studentDegreeDAO.update(studentDegreeEntity);
        } catch (Exception e) {
            throw new BusinessException("Error updating student degree", e);
        }
    }

    @Override
    public void delete(Long studentDegreeId) throws BusinessException {
        try {
            studentDegreeDAO.delete(studentDegreeId);
        } catch (Exception e) {
            throw new BusinessException("Error deleting student degree", e);
        }
    }

    @Override
    public List<StudentDegreeDTO> findByStudentId(Long studentId) throws BusinessException {
        List<StudentDegreeDTO> studentDegreeDTOList = new ArrayList<>();
        try {
            List<StudentDegreeEntity> studentDegreeList = studentDegreeDAO.findByStudentId(studentId);
            for (StudentDegreeEntity studentDegreeEntity : studentDegreeList) {
                StudentDegreeDTO studentDegreeDTO = new StudentDegreeDTO();
                studentDegreeDTO.setId(studentDegreeEntity.getId());
                studentDegreeDTO.setRemainingTime(studentDegreeEntity.getRemainingTime());
                studentDegreeDTO.setDegreeName(studentDegreeEntity.getDegree().getDegreeName());
                studentDegreeDTO.setTimeLimit(studentDegreeEntity.getDegree().getTimeLimit());
                studentDegreeDTO.setIdDegree(studentDegreeEntity.getDegree().getId());
                studentDegreeDTO.setUnique_ID(studentDegreeEntity.getStudent().getUnique_ID());
                studentDegreeDTOList.add(studentDegreeDTO);
            }
            return studentDegreeDTOList;

        } catch (Exception e) {
            throw new BusinessException("Error finding student degrees by student ID", e);
        }
    }

    @Override
    public List<StudentDegreeDTO> findByDegreeId(Long degreeId) throws BusinessException {
        List<StudentDegreeDTO> studentDegreeDTOList = new ArrayList<>();
        try {
            List<StudentDegreeEntity> studentDegreeList = studentDegreeDAO.findByDegreeId(degreeId);
            for (StudentDegreeEntity studentDegreeEntity : studentDegreeList) {
                StudentDegreeDTO studentDegreeDTO = new StudentDegreeDTO();
                studentDegreeDTO.setId(studentDegreeEntity.getId());
                studentDegreeDTO.setRemainingTime(studentDegreeEntity.getRemainingTime());
                studentDegreeDTO.setDegreeName(studentDegreeEntity.getDegree().getDegreeName());
                studentDegreeDTO.setTimeLimit(studentDegreeEntity.getDegree().getTimeLimit());
                studentDegreeDTO.setIdDegree(studentDegreeEntity.getDegree().getId());
                studentDegreeDTO.setUnique_ID(studentDegreeEntity.getStudent().getUnique_ID());
                studentDegreeDTOList.add(studentDegreeDTO);
            }
            return studentDegreeDTOList;

        } catch (Exception e) {
            throw new BusinessException("Error finding student degrees by degree ID", e);
        }
    }
}
