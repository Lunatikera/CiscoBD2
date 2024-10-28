/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dto.BlockDTO;
import entities.BlockEntity;
import exception.BusinessException;
import java.util.List;

/**
 *
 * @author adane
 */
public interface IBlockBO {
    
    public void saveBlock (BlockDTO block) throws BusinessException;
    
    public void deleteBlock (Long degreeId) throws BusinessException;
    
    public List<BlockDTO> blockListByRulePaginated(int offset,int limit,Long idRule)throws BusinessException;
    
    public BlockEntity findById(Long idBlock)throws BusinessException;
    
}
