/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.DegreeDTO;
import entities.DegreeEntity;
import java.util.List;

/**
 *
 * @author carli
 */
public class DegreeMapper {

    public static DegreeDTO toDTO(DegreeEntity degreeEntity) {
        return GenericMapper.map(degreeEntity, DegreeDTO.class);
    }

    public static DegreeEntity toEntity(DegreeDTO degreeDTO) {
        return GenericMapper.map(degreeDTO, DegreeEntity.class);
    }


    public static List<DegreeDTO> toDTOList(List<DegreeEntity> degreeEntities) {
        return GenericMapper.mapList(degreeEntities, DegreeDTO.class);
    }

    public static List<DegreeEntity> toEntityList(List<DegreeDTO> degreeDTOs) {
        return GenericMapper.mapList(degreeDTOs, DegreeEntity.class);
    }
}
