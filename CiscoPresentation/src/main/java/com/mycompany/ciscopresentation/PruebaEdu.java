/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ciscopresentation;

import businessObjects.AcademyUnityBO;
import businessObjects.ComputerBO;
import businessObjects.DegreeBO;
import businessObjects.DegreeReportBO;
import businessObjects.LaboratoryBO;
import businessObjects.StudentBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.AcademyUnityDAO;
import dao.ComputerDAO;
import dao.DegreeDAO;
import dao.DegreeReportDAO;
import dao.LaboratoryDAO;
import dao.StudentDAO;
import dao.StudentDegreeDAO;
import frames.FrmComputerManager;
import frames.FrmDegreeReport;
import frames.FrmStudentManager;
import interfaces.IAcademyUnityBO;
import interfaces.IAcademyUnityDAO;
import interfaces.IComputerBO;
import interfaces.IComputerDAO;
import interfaces.IDegreeBO;
import interfaces.IDegreeDAO;
import interfaces.IDegreeReportBO;
import interfaces.IDegreeReportDAO;
import interfaces.ILaboratoryBO;
import interfaces.ILaboratoryDAO;
import interfaces.IStudentBO;
import interfaces.IStudentDAO;
import interfaces.IStudentDegreeDAO;

/**
 *
 * @author NaderCroft
 */
public class PruebaEdu {
    public static void main(String[] args) {
//        IConnectionBD connectionBD = new ConnectionDB();
//        IAcademyUnityDAO academyDao = new AcademyUnityDAO(connectionBD);
//        IAcademyUnityBO academyBO = new AcademyUnityBO(academyDao);
//        ILaboratoryDAO laboratoryDAO = new LaboratoryDAO(connectionBD);
//        ILaboratoryBO laboratoryBO = new LaboratoryBO(laboratoryDAO,academyDao);
//        IComputerDAO computerDAO = new ComputerDAO(connectionBD);
//        IComputerBO computerBO = new ComputerBO(computerDAO, laboratoryDAO);
//
//        IDegreeDAO degreeDAO = new DegreeDAO(connectionBD);
//        IDegreeBO degreeBO = new DegreeBO(degreeDAO);
////        FrmComputerManager computerManager = new FrmComputerManager(computerBO, laboratoryBO, academyBO);
////        computerManager.setVisible(true);
//        IDegreeReportDAO reportDegreeDAO = new DegreeReportDAO(connectionBD);
//        IDegreeReportBO reportDegreeBO = new DegreeReportBO(reportDegreeDAO);
//        FrmDegreeReport repor = new FrmDegreeReport(degreeBO, reportDegreeBO);
//        repor.setVisible(true);

 IConnectionBD connection = new ConnectionDB();
        IStudentDAO studentDAO = new StudentDAO(connection);
        IStudentBO studentBO = new StudentBO(studentDAO);

        IStudentDegreeDAO studentDegreeDAO = new StudentDegreeDAO(connection);
        IDegreeDAO degreeDAO = new DegreeDAO(connection);
        IDegreeBO degreeBO = new DegreeBO(degreeDAO, studentDegreeDAO);

        FrmStudentManager frmStudentManager = new FrmStudentManager(studentBO, degreeBO);
        
        frmStudentManager.setVisible(true);
    }
}
