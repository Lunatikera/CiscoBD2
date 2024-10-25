/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ciscopresentation;

import businessObjects.RuleBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.RuleDAO;
import frames.FrmRulesManager;
import frames.FrmStudentManager;
import interfaces.IRuleBO;
import interfaces.IRuleDAO;

/**
 *
 * @author carli
 */
public class CiscoPresentation {

    public static void main(String[] args) {
        IConnectionBD connectionBD= new ConnectionDB();
        IRuleDAO ruleDAO= new RuleDAO(connectionBD);
        IRuleBO ruleBO= new RuleBO(ruleDAO);
        FrmRulesManager frmRulesManager= new FrmRulesManager(ruleBO);
        frmRulesManager.setVisible(true);
    }
}
