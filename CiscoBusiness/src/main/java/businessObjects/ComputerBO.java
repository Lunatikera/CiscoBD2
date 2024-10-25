/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dto.ComputerDTO;
import entities.ComputerEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IComputerBO;
import interfaces.IComputerDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.ComputerMapper;

/**
 *
 * @author NaderCroft
 */
public class ComputerBO implements IComputerBO{

    IComputerDAO computerDAO;

    public ComputerBO(IComputerDAO computerDAO) {
        this.computerDAO = computerDAO;
    }
    
    @Override
    public ComputerDTO saveComputer(ComputerDTO computer) throws BusinessException {
        if (computer == null) {
            throw new BusinessException("ComputerDTO cannot be null.");
        }

        ComputerEntity computerEntity = ComputerMapper.toEntity(computer);

        try {

            computerEntity = computerDAO.saveComputer(computerEntity);
            return ComputerMapper.toDTO(computerEntity);

        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, "Failed to save computer", ex);
            throw new BusinessException("An error occurred while saving the computer. Please try again later.");
        }
    }

    @Override
    public void deleteComputer(String computerIp) throws BusinessException {
        if (computerIp == null) {
            throw new BusinessException("Invalid computer IP.");
        }

        try {
            computerDAO.deleteComputer(computerIp);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error deleting student.");
        }
    }

    @Override
    public void updateComputer(ComputerDTO computer) throws BusinessException {
        if (computer == null || computer.getIpAdress() == null || computer.getId() <= 0) {
            throw new BusinessException("Invalid computer data.");
        }

        try {
            ComputerEntity computerEntity = ComputerMapper.toEntity(computer);
            computerDAO.updateComputer(computerEntity);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error updating student.");
        }
    }

    @Override
    public ComputerDTO findByIPComputer(String computerIp) throws BusinessException {
        if (computerIp == null) {
            throw new BusinessException("Invalid computer IP.");
        }

        try {
            ComputerEntity computer = computerDAO.findByIPComputer(computerIp);
            if (computer == null) {
                throw new BusinessException("Computer not found.");
            }
            return ComputerMapper.toDTO(computer);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error finding computer by given IP.");
        }
    }

    @Override
    public List<ComputerDTO> computerListByAcademyPaginated(int offset, int limit, Long IdLab) throws BusinessException {
        if (offset < 0 || limit <= 0) {
            throw new BusinessException("Invalid pagination parameters.");
        }

        try {
            List<ComputerEntity> computer = computerDAO.computerListByAcademyPaginated(offset, limit, IdLab);
            return ComputerMapper.toDTOList(computer);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving computer list by Laboratory.");
        }
    }
    
}
