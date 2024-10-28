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
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
     public void deleteBlock(Long blockId, LocalDate withdrawalDate) throws BusinessException{
        if (blockId == null || withdrawalDate == null) {
            throw new BusinessException("Block ID and withdrawal date cannot be null.");
        }

        try {
            blockDAO.deleteBlock(blockId, withdrawalDate); // Llama al método DAO para retirar el bloque
        } catch (PersistenceException ex) {
            Logger.getLogger(BlockBO.class.getName()).log(Level.SEVERE, "Error withdrawing block", ex);
            throw new BusinessException("An error occurred while withdrawing the block. Please try again later.");
        }
    }

    @Override
    public List<BlockDTO> blockListByRulePaginated(int offset, int limit, Long idRule) throws BusinessException {
         try {
             offset = tools.Tools.ReturnOFFSETMySQL(limit, offset);
        // Llama al DAO para obtener la lista de BlockEntity
        List<BlockEntity> blockEntities = blockDAO.blockListByRulePaginated(offset, limit, idRule);
        List<BlockDTO> blockdto = BlockMapper.toDTOList(blockEntities);
             for (int i = 0; i < blockdto.size(); i++) {
                 blockdto.get(i).setIdStudent(blockEntities.get(i).getStudent().getId());
                 blockdto.get(i).setId(blockEntities.get(i).getId());
                 blockdto.get(i).setstudentName(blockEntities.get(i).getStudent().getUnique_ID().toString());
                 
             }
// Convierte la lista de BlockEntity a una lista de BlockDTO usando BlockMapper
        return blockdto;
    } catch (PersistenceException e) {
        Logger.getLogger(BlockBO.class.getName()).log(Level.SEVERE, "Failed to save student", e);
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
