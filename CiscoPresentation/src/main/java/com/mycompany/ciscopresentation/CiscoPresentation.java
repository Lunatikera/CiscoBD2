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
import enums.ComputerTypes;
import exception.BusinessException;
import frames.FrmBlockedComputer;
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
            ComputerDTO computerDTO = computerBO.findByIPComputer("192.168.4.8");
            LaboratoryDTO laboratoryDTO = laboratoryBO.findLaboratoryByID(computerDTO.getLabId());
            IStudentBO studentBO = new StudentBO(studentDAO);
            
            if (computerDTO.getComputerType() == ComputerTypes.Administrativo) {
                FrmStudentStart sessionStarted = new FrmStudentStart(computerBO, studentBO, computerDTO, laboratoryDTO);
                sessionStarted.setVisible(true);
                
            }
            
            if (computerDTO.getComputerType() == ComputerTypes.Estudiante) {
                FrmBlockedComputer blockedComputer = new FrmBlockedComputer(studentBO, computerBO,computerDTO);
                blockedComputer.setVisible(true);
            }
            
        } catch (BusinessException ex) {
            Logger.getLogger(CiscoPresentation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
