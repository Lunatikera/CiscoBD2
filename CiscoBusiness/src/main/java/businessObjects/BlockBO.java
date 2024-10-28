/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessObjects;

import dto.BlockDTO;
import entities.BlockEntity;
import exception.BusinessException;
import exception.PersistenceException;
import interfaces.IBlockBO;
import interfaces.IBlockDAO;
import java.util.List;
import mappers.BlockMapper;

/**
 *
 * @author adane
 */
public class BlockBO implements IBlockBO{
  IBlockDAO blockDAO; // Dependencia del DAO

    // Constructor
    public BlockBO( IBlockDAO blockDAO) {
        this.blockDAO = blockDAO;
    }

   

    @Override
    public void deleteBlock(Long blockId) throws BusinessException {
        try {
            blockDAO.deleteBlock(blockId); // Llama al método del DAO para eliminar el bloque
        } catch (PersistenceException e) {
            throw new BusinessException("Error deleting block", e); // Manejo de excepciones
        }
    }

    @Override
    public List<BlockDTO> blockListByRulePaginated(int offset, int limit, Long idRule) throws BusinessException {
         try {
        // Llama al DAO para obtener la lista de BlockEntity
        List<BlockEntity> blockEntities = blockDAO.blockListByRulePaginated(offset, limit, idRule);
        
        // Convierte la lista de BlockEntity a una lista de BlockDTO usando BlockMapper
        return BlockMapper.toDTOList(blockEntities);
    } catch (PersistenceException e) {
        throw new BusinessException("Error fetching block list", e); // Manejo de excepciones
    }
    }

    @Override
    public BlockEntity findById(Long idBlock) throws BusinessException {
        try {
            return blockDAO.findById(idBlock); // Llama al DAO para encontrar un bloque por ID
        } catch (PersistenceException e) {
            throw new BusinessException("Error finding block", e); // Manejo de excepciones
        }
    }

    @Override
    public void saveBlock(BlockDTO block) throws BusinessException {
        try {
        BlockEntity blockEntity = BlockMapper.toEntity(block);
        blockDAO.saveBlock(blockEntity); // Guardar en la base de datos
    } catch (PersistenceException e) {
        throw new BusinessException("Error saving block", e);
    }
    }
    
}
