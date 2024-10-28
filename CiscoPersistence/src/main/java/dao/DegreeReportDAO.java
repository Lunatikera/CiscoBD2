/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.IConnectionBD;
import dtoReports.ReportDegreeDto;
import entities.DegreeEntity;
import entities.StudentComputerEntity;
import entities.StudentDegreeEntity;
import entities.StudentEntity;
import exception.PersistenceException;
import interfaces.IDegreeReportDAO;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author NaderCroft
 */
public class DegreeReportDAO implements IDegreeReportDAO {

    IConnectionBD connection;

    public DegreeReportDAO(IConnectionBD connection) {
        this.connection = connection;
    }

    @Override
    public List<ReportDegreeDto> getCareerUsageReport(List<String> degreeNames, LocalDate startDate, LocalDate endDate) throws PersistenceException {
        String literal = "MINUTE";
        EntityManager entityManager = connection.getEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ReportDegreeDto> cq = cb.createQuery(ReportDegreeDto.class);

        Root<StudentComputerEntity> sc = cq.from(StudentComputerEntity.class);
        Join<StudentComputerEntity, StudentEntity> s = sc.join("student");
        Join<StudentDegreeEntity, DegreeEntity> sd = s.join("studentDegrees");
        Join<DegreeEntity, DegreeEntity> d = sd.join("degree");

        // Filtrar por los nombres de grado dinámicos
        Predicate degreePredicate = d.get("degreeName").in(degreeNames);

        // Filtrar por rango de fechas
        Predicate dateRangePredicate = cb.between(
                cb.function("DATE", LocalDate.class, sc.get("startDateTime")),
                startDate, endDate
        );

        cq.select(cb.construct(ReportDegreeDto.class,
                d.get("degreeName"),
                cb.sum(cb.function("TimeDiffInMinutes", Long.class, sc.get("startDateTime"),
                        cb.coalesce(sc.get("endDateTime"), cb.currentTimestamp()))),
                cb.countDistinct(s.get("id")) // Conteo de estudiantes únicos
                ,
                 cb.function("DATE", LocalDate.class, sc.get("startDateTime")) // Fecha
        ))
                .where(cb.and(degreePredicate, dateRangePredicate))
                .groupBy(d.get("degreeName"), cb.function("DATE", LocalDate.class, sc.get("startDateTime")))
                .orderBy(cb.asc(cb.function("DATE", LocalDate.class, sc.get("startDateTime"))), cb.asc(d.get("degreeName")));

        return entityManager.createQuery(cq).getResultList();
    }

}
