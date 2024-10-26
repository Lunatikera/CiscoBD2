/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ciscopresentation;

import businessObjects.DegreeBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.RuleDAO;
import frames.FrmRulesManager;
import interfaces.IRuleBO;
import interfaces.IRuleDAO;
import businessObjects.AcademyUnityBO;
import businessObjects.LaboratoryBO;
import businessObjects.SoftwareBO;
import dao.AcademyUnityDAO;
import dao.LaboratoryDAO;
import frames.FrmLaboratoryManager;
import frames.FrmNewLaboratoryManager;
import interfaces.IAcademyUnityBO;
import interfaces.IAcademyUnityDAO;
import interfaces.ILaboratoryBO;
import interfaces.ILaboratoryDAO;
import dao.DegreeDAO;
import dao.SoftwareDAO;
import dto.SoftwareDTO;
import frames.FrmDegreeManager;
import frames.FrmSoftwareManager;
import frames.FrmStudentManager;
import interfaces.IDegreeBO;
import interfaces.IDegreeDAO;
import interfaces.ISoftwareBO;
import interfaces.ISoftwareDAO;

/**
 *
 * @author carli
 */
public class CiscoPresentation {

    public static void main(String[] args) {
        IConnectionBD connectionBD = new ConnectionDB();
//        IRuleDAO ruleDAO= new RuleDAO(connectionBD);
//        IRuleBO ruleBO= new RuleBO(ruleDAO);
//        FrmRulesManager frmRulesManager= new FrmRulesManager(ruleBO);
//        frmRulesManager.setVisible(true);

        ISoftwareDAO softwareDAO = new SoftwareDAO(connectionBD);
        ISoftwareBO softwareBO = new SoftwareBO(softwareDAO);

        FrmSoftwareManager frmSoftwareManager = new FrmSoftwareManager(softwareBO);
        frmSoftwareManager.setVisible(true);

//        IAcademyUnityDAO academyDAO = new AcademyUnityDAO(connectionBD);
//        ILaboratoryDAO laboratoryDAO = new LaboratoryDAO(connectionBD);
//        ILaboratoryBO laboratoryBO = new LaboratoryBO(laboratoryDAO, academyDAO);
//        IAcademyUnityBO academyBO = new AcademyUnityBO(academyDAO);
//        FrmLaboratoryManager frmLaboratoryManager = new FrmLaboratoryManager(laboratoryBO, academyBO);
//        frmLaboratoryManager.setVisible(true);
//        IDegreeDAO degreeDAO = new DegreeDAO(connectionBD);
//        IDegreeBO degreeBO = new DegreeBO(degreeDAO);
//        FrmDegreeManager frmDegreeManager = new FrmDegreeManager(degreeBO);
//        frmDegreeManager.setVisible(true);
//        FrmStudentManager frmStudentManager= new FrmStudentManager();
//        frmStudentManager.setVisible(true);
    }
}
