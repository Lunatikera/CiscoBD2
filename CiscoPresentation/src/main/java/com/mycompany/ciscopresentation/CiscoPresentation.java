/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ciscopresentation;

import businessObjects.DegreeBO;
import businessObjects.StudentBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.DegreeDAO;
import dao.StudentDAO;
import frames.FrmStudentManager;
import interfaces.IDegreeBO;
import interfaces.IDegreeDAO;
import interfaces.IStudentBO;
import interfaces.IStudentDAO;

/**
 *
 * @author carli
 */
public class CiscoPresentation {

    public static void main(String[] args) {
        
        IConnectionBD connectionBD= new ConnectionDB();
        IDegreeDAO degreeDAO = new DegreeDAO(connectionBD);
        IDegreeBO degreeBO = new DegreeBO(degreeDAO);
        IStudentDAO studentDAO = new StudentDAO(connectionBD);
        IStudentBO studentBO = new StudentBO(studentDAO);

        FrmStudentManager frmStudenManager = new FrmStudentManager(studentBO, degreeBO);
        frmStudenManager.setVisible(true);
    }
}
