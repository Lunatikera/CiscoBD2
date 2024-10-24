/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ciscopresentation;

import businessObjects.AcademyUnityBO;
import businessObjects.LaboratoryBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.AcademyUnityDAO;
import dao.LaboratoryDAO;
import frames.FrmLaboratoryManager;
import frames.FrmStudentManager;
import interfaces.IAcademyUnityBO;
import interfaces.IAcademyUnityDAO;
import interfaces.ILaboratoryBO;
import interfaces.ILaboratoryDAO;

/**
 *
 * @author carli
 */
public class CiscoPresentation {

    public static void main(String[] args) {
        IConnectionBD conexionBD= new ConnectionDB();
        IAcademyUnityDAO academyDAO = new AcademyUnityDAO(conexionBD);
        ILaboratoryDAO laboratoryDAO = new LaboratoryDAO(conexionBD);
        ILaboratoryBO laboratoryBO = new LaboratoryBO(laboratoryDAO);
        IAcademyUnityBO academyBO = new AcademyUnityBO(academyDAO);
        FrmLaboratoryManager frmLaboratoryManager= new FrmLaboratoryManager(laboratoryBO, academyBO);
        frmLaboratoryManager.setVisible(true);
    }
}
