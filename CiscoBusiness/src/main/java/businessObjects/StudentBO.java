/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dao.StudentDAO;
import dto.LogInDTO;
import dto.StudentDTO;
import entities.StudentEntity;
import enums.EnrollmentStatus;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IStudentBO;
import interfaces.IStudentDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import mappers.StudentMapper;

/**
 *
 * @author carli
 */
public class StudentBO implements IStudentBO {

    IStudentDAO studentDAO;

    public StudentBO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public StudentDTO saveStudent(StudentDTO student) throws BusinessException {
        if (student == null) {
            throw new BusinessException("StudentDTO cannot be null.");
        }
        StudentEntity studentEntity = StudentMapper.toEntity(student);
        
        try {

            studentEntity = studentDAO.saveStudent(studentEntity);
            return StudentMapper.toDTO(studentEntity);

        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, "Failed to save student", ex);
            throw new BusinessException("An error occurred while saving the student. Please try again later.");
        }
    }

    @Override
    public StudentDTO findStudentByUniqueID(Long studentId) throws BusinessException {
        if (studentId <= 0) {
            throw new BusinessException("El ID tiene que estar en un formato valido");
        }

        try {
            StudentEntity student = studentDAO.findStudentByUniqueID(studentId);
            if (student == null) {
                throw new BusinessException("Por favor Ingresa una ID Correcta");
            }
            return StudentMapper.toDTO(student);
        } catch (PersistenceException ex) {
            throw new BusinessException("Error finding student by unique ID.");
        }
    }

    @Override
    public List<StudentDTO> studentListByDegreePaginated(Long degreeId, int offset, int limit) throws BusinessException {
        if (offset < 0 || limit <= 0) {
            throw new BusinessException("Invalid pagination parameters.");
        }
        offset = tools.Tools.ReturnOFFSETMySQL(limit, offset);

        try {
            List<StudentEntity> students = studentDAO.studentListByDegreePaginated(degreeId, offset, limit);
            return StudentMapper.toDTOList(students);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving student list by degree.");
        }
    }

    @Override
    public void updateStudent(StudentDTO student) throws BusinessException {
        if (student == null || student.getUnique_ID()== null || student.getUnique_ID()<= 0) {
            throw new BusinessException("Invalid student data.");
        }

        try {
            StudentEntity studentEntity = StudentMapper.toEntity(student);
            studentDAO.updateStudent(studentEntity);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error updating student.");
        }
    }

//    @Override
//    public void deleteStudent(Long studentId) throws BusinessException {
//        if (studentId <= 0) {
//            throw new BusinessException("Invalid student ID.");
//        }
//
//        try {
//            studentDAO.deleteStudent(studentId);
//        } catch (PersistenceException ex) {
//            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
//            throw new BusinessException("Error deleting student.");
//        }
//    }
    @Override
    public StudentDTO login(LogInDTO loginDTO) throws BusinessException {
        try {

            StudentEntity student = studentDAO.findStudentByUniqueID(loginDTO.getUniqueID());

            if (student == null) {
                throw new BusinessException("Invalid unique ID."); // Changed to BusinessException for consistency
            }

            if (!student.getPassword().equals(loginDTO.getPassword())) {
                throw new BusinessException("Invalid password."); // Changed to BusinessException for consistency
            }

            return StudentMapper.toDTO(student);

        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, "Login failed", ex);
            throw new BusinessException("An error occurred while logging in. Please try again later."); // Wrap the exception for higher-level handling
        }
    }

    @Override
    public boolean verifyID(Long uniqueID) throws BusinessException {
        try {

            StudentEntity student = studentDAO.findStudentByUniqueID(uniqueID);

            if (student == null) {
                throw new BusinessException("ID No Valido"); // Changed to BusinessException for consistency
            }
            if (student.getEnrollmentStatus() == EnrollmentStatus.No_Inscrito) {
                throw new BusinessException("El estudiante no esta Inscrito"); // Changed to BusinessException for consistency

            }

            return true;

        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, "Login failed", ex);
            throw new BusinessException("An error occurred while logging in. Please try again later."); // Wrap the exception for higher-level handling
        }
    }
}
