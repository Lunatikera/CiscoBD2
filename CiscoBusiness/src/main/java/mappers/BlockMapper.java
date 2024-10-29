/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.BlockDTO;
import entities.BlockEntity;
import entities.RuleEntity;
import entities.StudentEntity;
import java.util.List;

/**
 *
 * @author adane
 */
public class BlockMapper {
    public static BlockDTO toDTO(BlockEntity blockEntity) {
        return GenericMapper.map(blockEntity, BlockDTO.class);
    }

    public static BlockEntity toEntity(BlockDTO blockDTO) {
        return GenericMapper.map(blockDTO, BlockEntity.class);
    }

    public static List<BlockDTO> toDTOList(List<BlockEntity> blockEntities) {
        return GenericMapper.mapList(blockEntities, BlockDTO.class);
    }

    public static List<BlockEntity> toEntityList(List<BlockDTO> blockDTOs) {
        return GenericMapper.mapList(blockDTOs, BlockEntity.class);
    }
    }
    

