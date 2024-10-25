/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.AcademyDTO;
import entities.AcademicUnityEntity;
import java.util.List;

/**
 *
 * @author aleja
 */
public class AcademyUnityMapper {
    public static AcademyDTO toDTO(AcademicUnityEntity academyEntity) {
        return GenericMapper.map(academyEntity, AcademyDTO.class);
    }

    public static AcademicUnityEntity toEntity(AcademyDTO academyDTO) {
        return GenericMapper.map(academyDTO, AcademicUnityEntity.class);
    }


    public static List<AcademyDTO> toDTOList(List<AcademicUnityEntity> academyEntities) {
        return GenericMapper.mapList(academyEntities, AcademyDTO.class);
    }

    public static List<AcademicUnityEntity> toEntityList(List<AcademyDTO> academyDTOs) {
        return GenericMapper.mapList(academyDTOs, AcademicUnityEntity.class);
    }
}
