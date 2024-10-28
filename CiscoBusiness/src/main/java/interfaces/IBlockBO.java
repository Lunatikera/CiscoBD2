/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.BlockDTO;
import entities.BlockEntity;
import exception.BusinessException;
import exception.PersistenceException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author adane
 */
public interface IBlockBO {
    
    public void saveBlock (BlockDTO block) throws BusinessException;
    
    public void deleteBlock(Long blockId, LocalDate withdrawalDate) throws BusinessException;
    
    public List<BlockDTO> blockListByRulePaginated(int offset,int limit,Long idRule)throws BusinessException;
    
    public BlockEntity findById(Long idBlock)throws BusinessException;
    
}
