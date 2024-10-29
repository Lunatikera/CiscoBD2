/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import dtoReports.ReportComputerDTO;
import entities.ComputerEntity;
import entities.DegreeEntity;
import entities.LaboratoryEntity;
import entities.StudentComputerEntity;
import entities.StudentDegreeEntity;
import entities.StudentEntity;
import exception.PersistenceException;
import interfaces.IReportComputerDAO;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author NaderCroft
 */
public class ReportComputerDAO implements IReportComputerDAO {

    IConnectionBD connection;

    public ReportComputerDAO(IConnectionBD connection) {
        this.connection = connection;
    }

    @Override
    public List<ReportComputerDTO> obtenerDatosCentroComputo(List<Integer> degreeIds,LocalDate startDate, LocalDate endDate) throws PersistenceException {
        EntityManager entityManager = connection.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReportComputerDTO> query = cb.createQuery(ReportComputerDTO.class);

        // Define the root and joins
        Root<StudentComputerEntity> sc = query.from(StudentComputerEntity.class);
        Join<StudentComputerEntity, ComputerEntity> c = sc.join("computer");
        Join<ComputerEntity, LaboratoryEntity> l = c.join("laboratory");
        Join<StudentComputerEntity, StudentEntity> s = sc.join("student");
        Join<StudentEntity, StudentDegreeEntity> sb = s.join("degrees");
        Join<StudentDegreeEntity, DegreeEntity> d = sb.join("degree");

        // Select fields
        query.select(cb.construct(ReportComputerDTO.class,
                l.get("labName"),
                c.get("machineNumber"),
                //cb.count(cb.distinct(s.get("id"))),
                cb.function("DATE", LocalDate.class, sc.get("startDateTime")),
                cb.sum(cb.function("TimeDiffInMinutes", Integer.class, sc.get("startDateTime"), sc.get("endDateTime"))),
                cb.diff(
                        cb.function("TimeDiffInMinutes", Integer.class, l.get("startTime"), l.get("endTime")),
                        cb.coalesce(cb.sum(cb.function("TimeDiffInMinutes", Integer.class, sc.get("startDateTime"), sc.get("endDateTime"))), 0)
                )
        ));

        // Apply filters
        Predicate degreePredicate = d.get("id").in(degreeIds); // Dynamic degree IDs
        Predicate datePredicate = cb.between(cb.function("DATE", LocalDate.class, sc.get("startDateTime")), startDate, endDate);
        Predicate typePredicate = cb.equal(c.get("computerType"), "Estudiante");

        query.where(cb.and(degreePredicate, datePredicate, typePredicate));

        // Grouping
        query.groupBy(
                l.get("labName"),
                c.get("machineNumber"),
                cb.function("DATE", LocalDate.class, sc.get("startDateTime")),
                l.get("startTime"),
                l.get("endTime")
        );

        // Ordering
        query.orderBy(
                cb.asc(c.get("machineNumber")),
                cb.asc(cb.function("DATE", LocalDate.class, sc.get("startDateTime"))),
                cb.asc(l.get("labName"))
        );

        // Execute query
        return entityManager.createQuery(query).getResultList();
    }

}
