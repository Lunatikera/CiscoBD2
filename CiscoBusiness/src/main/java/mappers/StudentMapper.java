/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dto.StudentDTO;
import entities.StudentEntity;
import java.util.List;

/**
 *
 * @author carli
 */
public class StudentMapper {
    
     public static StudentDTO toDTO(StudentEntity studentEntity) {
        return GenericMapper.map(studentEntity, StudentDTO.class);
    }

    public static StudentEntity toEntity(StudentDTO studentDTO) {
        return GenericMapper.map(studentDTO, StudentEntity.class);
    }

    public static List<StudentDTO> toDTOList(List<StudentEntity> studentEntities) {
        return GenericMapper.mapList(studentEntities, StudentDTO.class);
    }

    public static List<StudentEntity> toEntityList(List<StudentDTO> studentDTOs) {
        return GenericMapper.mapList(studentDTOs, StudentEntity.class);
    }
}
