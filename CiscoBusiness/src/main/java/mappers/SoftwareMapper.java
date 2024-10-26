/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.SoftwareDTO;
import entities.SoftwareEntity;
import java.util.List;

/**
 *
 * @author aleja
 */
public class SoftwareMapper {
    
    public static SoftwareDTO toDTO(SoftwareEntity softwareEntity) {
        return GenericMapper.map(softwareEntity, SoftwareDTO.class);
    }

    public static SoftwareEntity toEntity(SoftwareDTO softwareDTO) {
        return GenericMapper.map(softwareDTO, SoftwareEntity.class);
    }


    public static List<SoftwareDTO> toDTOList(List<SoftwareEntity> softwareEntity) {
        return GenericMapper.mapList(softwareEntity, SoftwareDTO.class);
    }

    public static List<SoftwareEntity> toEntityList(List<SoftwareDTO> softwareDTOs) {
        return GenericMapper.mapList(softwareDTOs, SoftwareEntity.class);
    }
}
