/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entities.DegreeEntity;
import exception.PersistenceException;
import java.util.List;

/**
 *
 * @author carli
 */
public interface IDegreeDAO {

    public List<DegreeEntity> getAllDegrees() throws PersistenceException;
}
