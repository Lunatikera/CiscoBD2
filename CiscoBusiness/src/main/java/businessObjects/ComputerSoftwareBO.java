/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dto.ComputerDTO;
import dto.ComputerSoftwareDTO;
import dto.SoftwareDTO;
import entities.ComputerEntity;
import entities.ComputerSoftwareEntity;
import entities.SoftwareEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IComputerDAO;
import interfaces.IComputerSoftwareBO;
import interfaces.IComputerSoftwareDAO;
import interfaces.ISoftwareDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aleja
 */
public class ComputerSoftwareBO implements IComputerSoftwareBO {

    IComputerDAO computerDAO;
    ISoftwareDAO softwareDAO;
    IComputerSoftwareDAO computerSoftwareDAO;

    public ComputerSoftwareBO(IComputerDAO computerDAO, ISoftwareDAO softwareDAO, IComputerSoftwareDAO computerSoftwareDAO) {
        this.computerDAO = computerDAO;
        this.softwareDAO = softwareDAO;
        this.computerSoftwareDAO = computerSoftwareDAO;
    }
    

    public void saveComputerSoftware(ComputerSoftwareDTO computerSoftware) throws BusinessException {
        if (computerSoftware == null) {
            throw new BusinessException("ComputerSoftwareDTO cannot be null.");
        }

        // Convertir el DTO a la entidad
        ComputerSoftwareEntity computerSoftwareEntity = new ComputerSoftwareEntity();

        try {
            // Obtener la computadora y el software correspondientes
            ComputerEntity computer = computerDAO.findByIdComputer(computerSoftware.getComputer()); // Asumiendo que tienes un método para obtener el ID de la computadora
            SoftwareEntity software = softwareDAO.getSoftwareById(computerSoftware.getSoftware()); // Asumiendo que tienes un método para obtener el ID del software

            // Asignar las entidades a la entidad de relación
            computerSoftwareEntity.setComputer(computer);
            computerSoftwareEntity.setSoftware(software);

            // Guardar la entidad de relación
            computerSoftwareDAO.saveComputerSoftware(computerSoftwareEntity);

            // Devolver el DTO mapeado
        } catch (PersistenceException ex) {
            Logger.getLogger(ComputerSoftwareBO.class.getName()).log(Level.SEVERE, "Failed to save computer software", ex);
            throw new BusinessException("An error occurred while saving the computer software. Please try again later.");
        }
    }
    
    @Override
public void deleteComputerSoftware(Long computerId, Long softwareId) throws BusinessException {
    if (computerId == null || computerId <= 0 || softwareId == null || softwareId <= 0) {
        throw new BusinessException("Invalid computer or software ID.");
    }

    try {
        computerSoftwareDAO.deleteComputerSoftware(computerId, softwareId); // Llama al método DAO para eliminar la relación
    } catch (PersistenceException ex) {
        Logger.getLogger(ComputerSoftwareBO.class.getName()).log(Level.SEVERE, "Failed to delete computer software", ex);
        throw new BusinessException("An error occurred while deleting the computer software. Please try again later.");
    }
}
    
}
