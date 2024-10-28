/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ciscopresentation;

import businessObjects.AcademyUnityBO;
import businessObjects.ComputerBO;
import businessObjects.LaboratoryBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.AcademyUnityDAO;
import dao.ComputerDAO;
import dao.LaboratoryDAO;
import frames.FrmComputerManager;
import interfaces.IAcademyUnityBO;
import interfaces.IAcademyUnityDAO;
import interfaces.IComputerBO;
import interfaces.IComputerDAO;
import interfaces.ILaboratoryBO;
import interfaces.ILaboratoryDAO;

/**
 *
 * @author NaderCroft
 */
public class PruebaEdu {
    public static void main(String[] args) {
        IConnectionBD connectionBD = new ConnectionDB();
        IAcademyUnityDAO academyDao = new AcademyUnityDAO(connectionBD);
        IAcademyUnityBO academyBO = new AcademyUnityBO(academyDao);
        ILaboratoryDAO laboratoryDAO = new LaboratoryDAO(connectionBD);
        ILaboratoryBO laboratoryBO = new LaboratoryBO(laboratoryDAO,academyDao);
        IComputerDAO computerDAO = new ComputerDAO(connectionBD);
        IComputerBO computerBO = new ComputerBO(computerDAO, laboratoryDAO);
//        FrmComputerManager computerManager = new FrmComputerManager(computerBO, laboratoryBO, academyBO);
//        computerManager.setVisible(true);
    }
}
