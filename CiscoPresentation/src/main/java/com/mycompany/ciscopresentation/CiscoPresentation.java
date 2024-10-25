/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ciscopresentation;


import businessObjects.DegreeBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.DegreeDAO;
import frames.FrmDegreeManager;
import frames.FrmStudentManager;
import interfaces.IDegreeBO;
import interfaces.IDegreeDAO;

/**
 *
 * @author carli
 */
public class CiscoPresentation {

    public static void main(String[] args) {
        IConnectionBD connectionBD = new ConnectionDB();
        IDegreeDAO degreeDAO = new DegreeDAO(connectionBD);
        IDegreeBO degreeBO = new DegreeBO(degreeDAO);
        FrmDegreeManager frmDegreeManager = new FrmDegreeManager(degreeBO);
        frmDegreeManager.setVisible(true);
//        FrmStudentManager frmStudentManager= new FrmStudentManager();
//        frmStudentManager.setVisible(true);
    }
}
