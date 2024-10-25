/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.ComputerDTO;
import entities.ComputerEntity;
import java.util.List;

/**
 *
 * @author NaderCroft
 */
public class ComputerMapper {
    public static ComputerDTO toDTO(ComputerEntity computerEntity) {
        return GenericMapper.map(computerEntity, ComputerDTO.class);
    }

    public static ComputerEntity toEntity(ComputerDTO computerDTO) {
        return GenericMapper.map(computerDTO, ComputerEntity.class);
    }


    public static List<ComputerDTO> toDTOList(List<ComputerEntity> computerEntities) {
        return GenericMapper.mapList(computerEntities, ComputerDTO.class);
    }

    public static List<ComputerEntity> toEntityList(List<ComputerDTO> computerDTOs) {
        return GenericMapper.mapList(computerDTOs, ComputerEntity.class);
    }
}
