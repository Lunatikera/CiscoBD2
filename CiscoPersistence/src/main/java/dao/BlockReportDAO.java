/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import dto2.BlockReportDTO;
import entities.BlockEntity;
import entities.StudentEntity;
import exception.PersistenceException;
import interfaces.IBlockReportDAO;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author adane
 */
public class BlockReportDAO implements IBlockReportDAO{

    IConnectionBD connection;

    public BlockReportDAO(IConnectionBD connectionBD) {
        this.connection = connectionBD;
    }

    @Override
    public List<BlockReportDTO> getBlockReport(LocalDate startDate, LocalDate endDate) throws PersistenceException {
          try {
        EntityManager entityManager = connection.getEntityManager();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Crear CriteriaQuery
        CriteriaQuery<BlockReportDTO> criteriaQuery = criteriaBuilder.createQuery(BlockReportDTO.class);
        
        Root<BlockEntity> blockRoot = criteriaQuery.from(BlockEntity.class);
        Join<BlockEntity, StudentEntity> studentJoin = blockRoot.join("student");

        // Selección
        criteriaQuery.select(criteriaBuilder.construct(
            BlockReportDTO.class,
            criteriaBuilder.concat(
                studentJoin.get("names"),
                criteriaBuilder.concat(
                    criteriaBuilder.concat(" ", studentJoin.get("firstLastname")),
                    criteriaBuilder.concat(" ", studentJoin.get("secondLastName"))
                )
            ),
            blockRoot.get("blockDate"),
            blockRoot.get("withdrawalDate"), // Obtiene la fecha de releaseDate o null
            blockRoot.get("motive")
        ));

        // Agregar la cláusula where
        criteriaQuery.where(criteriaBuilder.between(blockRoot.get("blockDate"), startDate, endDate));

        return entityManager.createQuery(criteriaQuery).getResultList();
    } catch (Exception e) {
        throw new PersistenceException("Error fetching block report", e);
    }
}
    }
    
    
    
    
    
    

