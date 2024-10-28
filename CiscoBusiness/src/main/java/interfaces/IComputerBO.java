/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.ComputerDTO;
import exception.BusinessException;
import java.util.List;

/**
 *
 * @author NaderCroft
 */
public interface IComputerBO {

    public ComputerDTO saveComputer(ComputerDTO computer) throws BusinessException;

    public void deleteComputer(String computerIp) throws BusinessException;

    public void updateComputer(ComputerDTO computer) throws BusinessException;

    public ComputerDTO findByIPComputer(String computerIp) throws BusinessException;

    public List<ComputerDTO> computerListByAcademyPaginated(int offset, int limit, Long IdLab) throws BusinessException;

    public List<ComputerDTO> computerListByAcademy(Long IdLab) throws BusinessException;

    public List<ComputerDTO> getAllComputer() throws BusinessException;
    
     public ComputerDTO findComputerByID(Long Idcomp) throws BusinessException;
}
