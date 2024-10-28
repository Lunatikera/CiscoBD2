/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.ciscopresentation;

import businessObjects.ComputerBO;
import businessObjects.DegreeBO;
import businessObjects.LaboratoryBO;
import businessObjects.StudentBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.AcademyUnityDAO;
import dao.ComputerDAO;
import dao.DegreeDAO;
import dao.LaboratoryDAO;
import dao.StudentDAO;
import dto.ComputerDTO;
import dto.LaboratoryDTO;
import exception.BusinessException;
import frames.FrmSessionStarted;
import frames.FrmStudentManager;
import frames.FrmStudentStart;
import interfaces.IAcademyUnityDAO;
import interfaces.IComputerBO;
import interfaces.IComputerDAO;
import interfaces.IDegreeBO;
import interfaces.IDegreeDAO;
import interfaces.ILaboratoryBO;
import interfaces.ILaboratoryDAO;
import interfaces.IStudentBO;
import interfaces.IStudentDAO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carli
 */
public class CiscoPresentation {

    public static void main(String[] args) {

        IConnectionBD conexion = new ConnectionDB();

        IStudentDAO studentDAO = new StudentDAO(conexion);
        IComputerDAO computerDAO = new ComputerDAO(conexion);
        IAcademyUnityDAO academyUnityDAO = new AcademyUnityDAO(conexion);
        ILaboratoryDAO laboratoryDAO = new LaboratoryDAO(conexion);
        IComputerBO computerBO = new ComputerBO(computerDAO, laboratoryDAO);
        ILaboratoryBO laboratoryBO = new LaboratoryBO(laboratoryDAO, academyUnityDAO);

        try {
            ComputerDTO computerDTO = computerBO.findByIPComputer("192.168.4.9");
            LaboratoryDTO laboratoryDTO = laboratoryBO.findLaboratoryByID(computerDTO.getLabId());

            IStudentBO studentBO = new StudentBO(studentDAO);
            FrmStudentStart sessionStarted = new FrmStudentStart(computerBO,studentBO, computerDTO, laboratoryDTO);

            sessionStarted.setVisible(true);
        } catch (BusinessException ex) {
            Logger.getLogger(CiscoPresentation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
