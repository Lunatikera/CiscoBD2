/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.ciscopresentation;

import businessObjects.LaboratoryBO;
import businessObjects.LaboratoryRulesBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.AcademyUnityDAO;
import dao.LaboratoryDAO;
import dao.LaboratoryRulesDAO;
import dao.RuleDAO;
import dto.LaboratoryDTO;
import dto.RuleDTO;
import frames.FrmDegreeReport;
import frames.FrmLaboratoryRules;
import interfaces.IAcademyUnityDAO;
import interfaces.ILaboratoryBO;
import interfaces.ILaboratoryDAO;
import interfaces.ILaboratoryRulesBO;
import interfaces.ILaboratoryRulesDAO;
import interfaces.IRuleDAO;

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

        laboratoryDTO.setId(1L);
        FrmLaboratoryRules repor = new FrmLaboratoryRules(laboratoryRulesBO, laboratoryBO, laboratoryDTO, rulesDTO);
        repor.setVisible(true);

    }

}
