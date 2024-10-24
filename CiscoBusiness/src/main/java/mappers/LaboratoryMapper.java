/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.LaboratoryDTO;
import entities.LaboratoryEntity;
import java.util.List;

/**
 *
 * @author aleja
 */
public class LaboratoryMapper {
    
    public static LaboratoryDTO toDTO(LaboratoryEntity laboratoryEntity) {
        return GenericMapper.map(laboratoryEntity, LaboratoryDTO.class);
    }

    public static LaboratoryEntity toEntity(LaboratoryDTO laboratoryDTO) {
        return GenericMapper.map(laboratoryDTO, LaboratoryEntity.class);
    }


    public static List<LaboratoryDTO> toDTOList(List<LaboratoryEntity> laboratoryEntities) {
        return GenericMapper.mapList(laboratoryEntities, LaboratoryDTO.class);
    }

    public static List<LaboratoryEntity> toEntityList(List<LaboratoryDTO> laboratoryDTOs) {
        return GenericMapper.mapList(laboratoryDTOs, LaboratoryEntity.class);
    }
}
