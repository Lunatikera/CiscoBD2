/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author carli
 */
public class GenericMapper {

    public static <S, T> T map(S source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }

        try {
            T target = targetClass.getDeclaredConstructor().newInstance();

            for (Field sourceField : source.getClass().getDeclaredFields()) {
                sourceField.setAccessible(true); // Allow access to private fields
                Object value = sourceField.get(source); // Get value from source

                try {
                    Field targetField = targetClass.getDeclaredField(sourceField.getName());
                    targetField.setAccessible(true); // Allow access to private fields
                    targetField.set(target, value); // Set value to target
                } catch (NoSuchFieldException e) {
                    // Ignore if the target class does not have the field
                }
            }

            return target;

        } catch (Exception e) {
            throw new RuntimeException("Error mapping from " + source.getClass().getSimpleName() + " to " + targetClass.getSimpleName(), e);
        }

    }

    public static <S, T> List<T> mapList(List<S> sourceList, Class<T> targetClass) {
        return sourceList.stream()
                .map(source -> map(source, targetClass))
                .collect(Collectors.toList());
    }
}
