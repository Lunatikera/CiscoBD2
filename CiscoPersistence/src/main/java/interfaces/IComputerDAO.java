/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.ComputerEntity;
import exception.PersistenceException;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IComputerDAO {
    public ComputerEntity saveComputer(ComputerEntity computer)throws PersistenceException;
    
    public void deleteComputer(String computerIp)throws PersistenceException;
    
    public void updateComputer(ComputerEntity computer)throws PersistenceException;
    
    public ComputerEntity findByIPComputer(String computerIp)throws PersistenceException;
    
    public List<ComputerEntity> computerListByAcademyPaginated(int offset,int limit,Long IdLab)throws PersistenceException;
    
    public List<ComputerEntity> computerListByAcademy(Long IdLab) throws PersistenceException;
}
