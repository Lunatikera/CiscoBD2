/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ciscopresentation;

import businessObjects.ComputerBO;
import businessObjects.ComputerSoftwareBO;
import businessObjects.DegreeBO;
import businessObjects.SoftwareBO;
import businessObjects.StudentBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.ComputerDAO;
import dao.ComputerSoftwareDAO;
import dao.DegreeDAO;
import dao.LaboratoryDAO;
import dao.SoftwareDAO;
import dao.StudentDAO;
import dto.ComputerDTO;
import frames.FrmAvailableSoftware;
import frames.FrmStudentManager;
import interfaces.IComputerBO;
import interfaces.IComputerDAO;
import interfaces.IComputerSoftwareBO;
import interfaces.IComputerSoftwareDAO;
import interfaces.IDegreeBO;
import interfaces.IDegreeDAO;
import interfaces.ILaboratoryDAO;
import interfaces.ISoftwareBO;
import interfaces.ISoftwareDAO;
import interfaces.IStudentBO;
import interfaces.IStudentDAO;



/**
 *
 * @author carli
 */
public class CiscoPresentation {

    public static void main(String[] args) {
        IConnectionBD connectionBD = new ConnectionDB();
// IDegreeReportDAO reportDegreeDAO = new DegreeReportDAO(connectionBD);
//        IDegreeReportBO reportDegreeBO = new DegreeReportBO(reportDegreeDAO);
//        FrmDegreeReport repor = new FrmDegreeReport(degreeBO, reportDegreeBO);
//        repor.setVisible(true);
        ISoftwareDAO softwareDAO = new SoftwareDAO(connectionBD);
        ISoftwareBO softwareBO = new SoftwareBO(softwareDAO);
        IComputerDAO computerDAO = new ComputerDAO(connectionBD);
        IComputerSoftwareDAO computerSoftwareDAO = new ComputerSoftwareDAO(connectionBD);
        IComputerSoftwareBO computerSoftwareBO = new ComputerSoftwareBO(computerDAO, softwareDAO, computerSoftwareDAO);
        ComputerDTO computerDTO = new ComputerDTO();
        computerDTO.setId(1L);
        computerDTO.setMachineNumber(1);
        FrmAvailableSoftware repor = new FrmAvailableSoftware(computerSoftwareBO, softwareBO, computerDTO);
        repor.setVisible(true);
        
      
    }     

}
