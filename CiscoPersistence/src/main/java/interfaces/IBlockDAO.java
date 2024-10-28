/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.BlockEntity;
import exception.PersistenceException;
import java.util.List;

/**
 *
 * @author adane
 */
public interface IBlockDAO {
    
    public void saveBlock (BlockEntity block) throws PersistenceException;
    
    public void deleteBlock (Long degreeId) throws PersistenceException;
    
    public List<BlockEntity> blockListByRulePaginated(int offset,int limit,Long idRule)throws PersistenceException;
    
    public BlockEntity findById(Long idBlock)throws PersistenceException;
    
    
}
