/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.ciscopresentation;

import businessObjects.AcademyUnityBO;
import businessObjects.DegreeBO;
import businessObjects.LaboratoryBO;
import businessObjects.LaboratoryRulesBO;
import businessObjects.RuleBO;
import businessObjects.StudentBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.AcademyUnityDAO;
import dao.DegreeDAO;
import dao.LaboratoryDAO;
import dao.LaboratoryRulesDAO;
import dao.RuleDAO;
import dao.StudentDAO;
import dao.StudentDegreeDAO;
import dto.LaboratoryDTO;
import dto.RuleDTO;
import frames.FrmDegreeReport;
import frames.FrmLaboratoryManager;
import frames.FrmLaboratoryRules;
import frames.FrmStudentManager;
import interfaces.IAcademyUnityBO;
import interfaces.IAcademyUnityDAO;
import interfaces.IDegreeBO;
import interfaces.IDegreeDAO;
import interfaces.ILaboratoryBO;
import interfaces.ILaboratoryDAO;
import interfaces.ILaboratoryRulesBO;
import interfaces.ILaboratoryRulesDAO;
import interfaces.IRuleBO;
import interfaces.IRuleDAO;
import interfaces.IStudentBO;
import interfaces.IStudentDAO;
import interfaces.IStudentDegreeDAO;

/**
 *
 * @author aleja
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        IConnectionBD connectionBD = new ConnectionDB();
        ILaboratoryRulesDAO laboratoryRulesDAO = new LaboratoryRulesDAO(connectionBD);
        ILaboratoryDAO laboratoryDAO = new LaboratoryDAO(connectionBD);
        IRuleDAO ruleDAO = new RuleDAO(connectionBD);
        ILaboratoryRulesBO laboratoryRulesBO = new LaboratoryRulesBO(laboratoryDAO, ruleDAO, laboratoryRulesDAO);
        IAcademyUnityDAO academyDAO = new AcademyUnityDAO(connectionBD);
        ILaboratoryBO laboratoryBO = new LaboratoryBO(laboratoryDAO, academyDAO);
        RuleDTO rulesDTO = new RuleDTO();
        LaboratoryDTO laboratoryDTO = new LaboratoryDTO();
        IRuleBO rulesBO = new RuleBO(ruleDAO);
        IAcademyUnityBO academyBO = new AcademyUnityBO(academyDAO);

//        laboratoryDTO.setId(1L);
        FrmLaboratoryManager repor = new FrmLaboratoryManager(laboratoryBO, academyBO);
        repor.setVisible(true);

        IStudentDAO studentDAO = new StudentDAO(connectionBD);
        IStudentBO studentBO = new StudentBO(studentDAO);
        IDegreeDAO degreeDAO = new DegreeDAO(connectionBD);
        IStudentDegreeDAO studentDegreeDAO = new StudentDegreeDAO(connectionBD);
        IDegreeBO degreeBO = new DegreeBO(degreeDAO, studentDegreeDAO);
//        FrmStudentManager frmStudentManager = new FrmStudentManager(studentBO, degreeBO);
//        frmStudentManager.setVisible(true);

    }

}
