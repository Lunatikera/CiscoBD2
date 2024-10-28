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
public class DegreeReportDAO implements IDegreeReportDAO{

    IConnectionBD connection;

    public DegreeReportDAO(IConnectionBD connection) {
        this.connection = connection;
    }
    
    @Override
    public List<ReportDegreeDto> getCareerUsageReport(List<String> degreeNames, LocalDate startDate, LocalDate endDate) throws PersistenceException {
    EntityManager entityManager = connection.getEntityManager();
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<ReportDegreeDto> query = cb.createQuery(ReportDegreeDto.class);
    Root<StudentComputerEntity> sc = query.from(StudentComputerEntity.class);

    Join<StudentComputerEntity, StudentEntity> studentJoin = sc.join("student");
    Join<StudentEntity, StudentDegreeEntity> studentDegreeJoin = studentJoin.join("studentDegrees");
    Join<StudentDegreeEntity, DegreeEntity> degreeJoin = studentDegreeJoin.join("degree");

    // Expresión para calcular minutos de uso
    Expression<Long> totalMinutes = cb.sum(
            cb.function(
                    "TIMESTAMPDIFF",
                    Long.class,
                    cb.literal("MINUTE"),
                    sc.get("startDateTime"),
                    cb.coalesce(sc.get("endDateTime"), cb.currentTimestamp())
            )
    );

    // Selección de los campos requeridos en ReportDegreeDto
    query.select(cb.construct(
            ReportDegreeDto.class,
            degreeJoin.get("degreeName"),          // Nombre de la carrera
            totalMinutes,                          // Minutos de uso
            cb.countDistinct(studentJoin.get("id")), // Cantidad de estudiantes
            cb.function("DATE", Date.class, sc.get("startDateTime")) // Fecha
    ));

    // Convertir LocalDate a java.sql.Date
    Date dateS = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    Date dateF = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    // Condiciones WHERE
    Predicate degreeNamePredicate = degreeJoin.get("degreeName").in(degreeNames);
    Predicate dateRangePredicate = cb.between(
            sc.get("startDateTime"), // Asegúrate de que esto sea de tipo Date o LocalDateTime
            dateS,
            dateF
    );

    query.where(cb.and(degreeNamePredicate, dateRangePredicate));

    // Agrupación y ordenamiento
    query.groupBy(degreeJoin.get("degreeName"), cb.function("DATE", Date.class, sc.get("startDateTime")));
    query.orderBy(cb.asc(cb.function("DATE", Date.class, sc.get("startDateTime"))), cb.asc(degreeJoin.get("degreeName")));

    return entityManager.createQuery(query).getResultList();
}
}
 
