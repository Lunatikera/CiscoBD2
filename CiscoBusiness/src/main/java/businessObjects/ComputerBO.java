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
import interfaces.ILaboratoryDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mappers.ComputerMapper;
import tools.Tools;

/**
 *
 * @author NaderCroft
 */
public class ComputerBO implements IComputerBO {

    IComputerDAO computerDAO;
    ILaboratoryDAO laboratoryDAO;

    public ComputerBO(IComputerDAO computerDAO, ILaboratoryDAO laboratoryDAO) {
        this.computerDAO = computerDAO;
        this.laboratoryDAO = laboratoryDAO;
    }

    @Override
    public List<ComputerDTO> getAllComputer() throws BusinessException {

        try {
            List<ComputerEntity> academy = computerDAO.getAllComputer();
            return ComputerMapper.toDTOList(academy);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving computer list.");
        }
    }

    @Override
    public ComputerDTO saveComputer(ComputerDTO computer) throws BusinessException {
        if (computer == null) {
            throw new BusinessException("ComputerDTO cannot be null.");
        }

        ComputerEntity computerEntity = ComputerMapper.toEntity(computer);

        try {
            computerEntity.setLaboratory(laboratoryDAO.findLaboratoryByID(computer.getLabId()));
            System.out.println(computerEntity);
            computerEntity = computerDAO.saveComputer(computerEntity);
            System.out.println(computerEntity);

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
            computerEntity.setLaboratory(laboratoryDAO.findLaboratoryByID(computer.getLabId()));
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

            ComputerDTO computerDto = ComputerMapper.toDTO(computer);
            computerDto.setLabId(computer.getLaboratory().getId());
            return computerDto;
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
        offset = Tools.ReturnOFFSETMySQL(limit, offset);
        try {
            List<ComputerEntity> computer = computerDAO.computerListByAcademyPaginated(offset, limit, IdLab);
            return ComputerMapper.toDTOList(computer);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving computer list by Laboratory.");
        }
    }

    public List<ComputerDTO> computerListByAcademy(Long IdLab) throws BusinessException {
        try {
            List<ComputerEntity> computer = computerDAO.computerListByAcademy(IdLab);
            return ComputerMapper.toDTOList(computer);
        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error retrieving computer list by Laboratory.");
        }

    }

    @Override
    public ComputerDTO findComputerByID(Long Idcomp) throws BusinessException {
        if (Idcomp <= 0) {
            throw new BusinessException("Invalid computer ID.");
        }

        try {
            ComputerEntity computer = computerDAO.findByIdComputer(Idcomp);

            if (computer == null) {
                throw new BusinessException("Computer not found.");
            }

            ComputerDTO computerDTO = ComputerMapper.toDTO(computer);
            // Aquí puedes agregar más datos del DTO si es necesario
            return computerDTO;

        } catch (PersistenceException ex) {
            Logger.getLogger(StudentBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException("Error finding computer by unique ID.");
        }
    }

}
