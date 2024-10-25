/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.RuleDTO;
import entities.RuleEntity;
import java.util.List;

/**
 *
 * @author carli
 */
public class RuleMapper {
  public static RuleDTO toDTO(RuleEntity ruleEntity) {
        return GenericMapper.map(ruleEntity, RuleDTO.class);
    }

    public static RuleEntity toEntity(RuleDTO ruleDTO) {
        return GenericMapper.map(ruleDTO, RuleEntity.class);
    }


    public static List<RuleDTO> toDTOList(List<RuleEntity> ruleEntities) {
        return GenericMapper.mapList(ruleEntities, RuleDTO.class);
    }

    public static List<RuleEntity> toEntityList(List<RuleDTO> ruleDTOs) {
        return GenericMapper.mapList(ruleDTOs, RuleEntity.class);
    }
}
  

