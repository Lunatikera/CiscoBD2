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

    public List<DegreeEntity> obterCarrerasPaguinado(int limit, int offtel) throws PersistenceException;

    public void saveDegree(DegreeEntity degree) throws PersistenceException;

    public void deleteDegree(Long degreeId) throws PersistenceException;

    public void updateDegree(DegreeEntity degree) throws PersistenceException;

    public DegreeEntity findDegreeForId(Long degreeId) throws PersistenceException;

    public List<DegreeEntity> getDegreesByStudent(Long studentID) throws PersistenceException;
}
