/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.LaboratoryRulesDTO;
import entities.LaboratoryEntity;
import entities.LaboratoryRulesEntity;
import java.util.List;

/**
 *
 * @author aleja
 */
public class LaboratoryRuleMapper {
    public static LaboratoryRulesDTO toDTO(LaboratoryRulesEntity laboratoryEntity) {
        return GenericMapper.map(laboratoryEntity, LaboratoryRulesDTO.class);
    }

    public static LaboratoryRulesEntity toEntity(LaboratoryRulesDTO laboratoryDTO) {
        return GenericMapper.map(laboratoryDTO, LaboratoryRulesEntity.class);
    }


    public static List<LaboratoryRulesDTO> toDTOList(List<LaboratoryRulesEntity> laboratoryEntities) {
        return GenericMapper.mapList(laboratoryEntities, LaboratoryRulesDTO.class);
    }

    public static List<LaboratoryRulesEntity> toEntityList(List<LaboratoryRulesDTO> laboratoryDTO) {
        return GenericMapper.mapList(laboratoryDTO, LaboratoryRulesEntity.class);
    }
}
