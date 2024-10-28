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
    public List<ReportComputerDTO> obtenerDatosCentroComputo(LocalDate inicio, LocalDate fin) throws PersistenceException {
        try {
            EntityManager entityManager = connection.getEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ReportComputerDTO> query = cb.createQuery(ReportComputerDTO.class);
            Root<StudentComputerEntity> sc = query.from(StudentComputerEntity.class);

            Join<StudentComputerEntity, ComputerEntity> c = sc.join("computer");
            Join<ComputerEntity, LaboratoryEntity> l = c.join("laboratory");
            Join<StudentEntity, StudentDegreeEntity> sb = sc.join("student").join("studentDegrees");
            Join<StudentDegreeEntity, DegreeEntity> d = sb.join("degree");

            // Select
            query.select(cb.construct(
                    ReportComputerDTO.class,
                    l.get("labName"),
                    c.get("machineNumber"),
                    cb.count(sc.get("student").get("id")), // Count distinct students
                    cb.function("DATE", LocalDate.class, sc.get("startDateTime")),
                    cb.sum(cb.function("TIMESTAMPDIFF", Integer.class,
                            cb.literal("MINUTE"), sc.get("startDateTime"), sc.get("endDateTime"))),
                    cb.diff(
                            cb.function("TIMESTAMPDIFF", Integer.class, cb.literal("MINUTE"), l.get("startTime"), l.get("endTime")),
                            cb.nullif(cb.sum(cb.function("TIMESTAMPDIFF", Integer.class,
                                    cb.literal("MINUTE"), sc.get("startDateTime"), sc.get("endDateTime"))), 0)
                    )
            ));

            // Where
            Predicate degreePredicate = d.get("id").in(1, 2, 3, 4);
            Predicate datePredicate = cb.between(cb.function("DATE", LocalDate.class, sc.get("startDateTime")), inicio, fin);
            query.where(cb.and(degreePredicate, datePredicate));

            // Group by
            query.groupBy(l.get("labName"), c.get("machineNumber"), cb.function("DATE", LocalDate.class, sc.get("startDateTime")), l.get("startTime"), l.get("endTime"));

            // Order by
            query.orderBy(cb.asc(c.get("machineNumber")), cb.asc(cb.function("DATE", LocalDate.class, sc.get("startDateTime"))), cb.asc(l.get("labName")));

            return entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            // Log the exception (you might want to use a logging framework)
            System.err.println("Error fetching data: " + e.getMessage());
            throw new RuntimeException("Error fetching computer center data", e);
        }
    }
}
