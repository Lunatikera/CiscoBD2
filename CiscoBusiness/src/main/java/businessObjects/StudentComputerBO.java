/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dao.StudentDAO;
import dto.StudentComputerDTO;
import entities.ComputerEntity;
import entities.StudentComputerEntity;
import entities.StudentEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IComputerDAO;
import interfaces.IStudentComputerBO;
import interfaces.IStudentComputerDAO;
import interfaces.IStudentDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carli
 */
public class StudentComputerBO implements IStudentComputerBO {
    
    IStudentComputerDAO studentComputerDAO;
    IStudentDAO studentDAO;
    IComputerDAO computerDAO;
    
    public StudentComputerBO(IStudentComputerDAO studentComputerDAO, IStudentDAO studentDAO, IComputerDAO computerDAO) {
        this.studentComputerDAO = studentComputerDAO;
        this.studentDAO = studentDAO;
        this.computerDAO = computerDAO;
    }
    
    @Override
    public void saveComputerUse(StudentComputerDTO studentComputer) throws BusinessException {
        if (studentComputer == null) {
            throw new BusinessException("ComputerSoftwareDTO cannot be null.");
        }

        // Convertir el DTO a la entidad
        StudentComputerEntity studentComputerEntity = new StudentComputerEntity();
        
        try {
            // Obtener la computadora y el software correspondientes
            ComputerEntity computer = computerDAO.findByIPComputer(studentComputer.getIpAdress()); // Asumiendo que tienes un método para obtener el ID de la computadora
            StudentEntity student = studentDAO.findStudentByUniqueID(studentComputer.getUnique_ID()); // Asumiendo que tienes un método para obtener el ID del software

            // Asignar las entidades a la entidad de relación
            studentComputerEntity.setComputer(computer);
            studentComputerEntity.setStudent(student);
            studentComputerEntity.setSelectedDateTime(studentComputer.getSelectedDateTime());
            studentComputerEntity.setDegreeName(studentComputer.getDegreeName());
            // Guardar la entidad de relación
            studentComputerDAO.saveComputerUse(studentComputerEntity);

            // Devolver el DTO mapeado
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentComputerBO.class.getName()).log(Level.SEVERE, "Failed to save computer software", ex);
            throw new BusinessException("An error occurred while saving the computer software. Please try again later.");
        }
    }
}
