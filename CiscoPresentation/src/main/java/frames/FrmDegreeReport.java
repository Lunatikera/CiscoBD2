/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import businessObjects.DegreeBO;
import dto.DegreeDTO;
import exception.BusinessException;
import interfaces.IDegreeBO;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author carli
 */
public class FrmDegreeReport extends javax.swing.JFrame {

    private int page = 1;
    private int LIMIT = 10;
    IDegreeBO degreeBO;

    /**
     * Creates new form FrmStudentManager
     *
     * @param degreeBO
     */
    public FrmDegreeReport(IDegreeBO idegreeBO) {
        initComponents();
        this.degreeBO = idegreeBO;
        this.loadInitialMethods();

    }

    public void loadInitialMethods() {
        this.setTitle("Administracion de Carrera ");
        this.setResizable(false);
        this.setSize(1280, 780);
        this.setLocationRelativeTo(null);
        this.loadTableDegree();
        
        this.customizeTableHeader();

    }
    
    private void customizeTableHeader() {
        JTableHeader header = tblDegreeReport.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 10)); // Cambia el tipo, estilo y tamaño de fuente
    }

    

    

    private void deleteInfoTableDegree() {
        DefaultTableModel tableModel = (DefaultTableModel) this.tblDegreeReport.getModel();
        if (tableModel.getRowCount() > 0) {
            for (int row = tableModel.getRowCount() - 1; row > -1; row--) {
                tableModel.removeRow(row);
            }
        }
    }

    private void addInfoTable(List<DegreeDTO> degreeList) {
        if (degreeList == null) {
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.tblDegreeReport.getModel();
        degreeList.forEach(column
                -> {
            Object[] row = new Object[3];
            row[0] = column.getId();
            row[1] = column.getName();
            row[2] = column.getTimeLimit();

            tableModel.addRow(row);
        });
    }

    private Long getSelectedIdTableDegree() {
        int selectedIndex = this.tblDegreeReport.getSelectedRow();
        if (selectedIndex != -1) {
            DefaultTableModel model = (DefaultTableModel) this.tblDegreeReport.getModel();
            int idIndexRow = 0;
            Long idSelectedDegree = (Long) model.getValueAt(selectedIndex,
                    idIndexRow);
            return idSelectedDegree;
        } else {
            return null;
        }
    }

    public void loadTableDegree() {
        try {
            // Borrar registros previos antes de cargar los nuevos
            deleteInfoTableDegree();

            // Obtén solo los clientes necesarios para la página actual
            List<DegreeDTO> degreeList = this.degreeBO.obterCarrerasPaguinado(LIMIT, page);

            //Agrega los registros paginados a la tabla
            this.addInfoTable(degreeList);
            //Control de botones de navegación
           

        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        panelMenu2 = new panels.PanelMenu();
        jLabel2 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btnMenuStudents = new utilities.MenuButton();
        jLabel24 = new javax.swing.JLabel();
        btnMenuComputers = new utilities.MenuButton();
        jLabel25 = new javax.swing.JLabel();
        btnMenuDegree = new utilities.MenuButton();
        jLabel26 = new javax.swing.JLabel();
        btnMenuLabs = new utilities.MenuButton();
        jLabel27 = new javax.swing.JLabel();
        btnMenuBlocks = new utilities.MenuButton();
        jLabel28 = new javax.swing.JLabel();
        btnMenuAcademies = new utilities.MenuButton();
        jLabel29 = new javax.swing.JLabel();
        btnMenuSoftwares = new utilities.MenuButton();
        jLabel30 = new javax.swing.JLabel();
        btnMenuRules = new utilities.MenuButton();
        jLabel31 = new javax.swing.JLabel();
        btnMenuLabReports = new utilities.MenuButton();
        jLabel32 = new javax.swing.JLabel();
        btnMenuDegreeReports = new utilities.MenuButton();
        jLabel33 = new javax.swing.JLabel();
        btnMenuBlockReports = new utilities.MenuButton();
        jLabel34 = new javax.swing.JLabel();
        btnMenuLogOff = new utilities.MenuButton();
        jLabel35 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDegreeReport = new javax.swing.JTable();
        menuButton13 = new utilities.MenuButton();
        lblDegreeReport = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cbxAddDegree = new javax.swing.JComboBox<>();
        cbxDeleteDegree = new javax.swing.JComboBox<>();
        lbDeleteDegree = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        LbAddDegree = new javax.swing.JLabel();
        lbEnd = new javax.swing.JLabel();
        tpEnd = new com.github.lgooddatepicker.components.TimePicker();
        lbStart = new javax.swing.JLabel();
        tpStart = new com.github.lgooddatepicker.components.TimePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ItsonLogo.png"))); // NOI18N
        panelMenu2.add(jLabel2);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel18);

        btnMenuStudents.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuStudents.setText("Estudiantes");
        btnMenuStudents.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        panelMenu2.add(btnMenuStudents);

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel24);

        btnMenuComputers.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuComputers.setText("Computadora");
        btnMenuComputers.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuComputers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuComputersActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuComputers);

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel25);

        btnMenuDegree.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuDegree.setText("Carreras");
        btnMenuDegree.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuDegree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuDegreeActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuDegree);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel26);

        btnMenuLabs.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuLabs.setText("Laboratorios");
        btnMenuLabs.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuLabs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuLabsActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuLabs);

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel27);

        btnMenuBlocks.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuBlocks.setText("Bloqueos");
        btnMenuBlocks.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuBlocks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuBlocksActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuBlocks);

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel28);

        btnMenuAcademies.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuAcademies.setText("Unidades Academicas");
        btnMenuAcademies.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuAcademies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuAcademiesActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuAcademies);

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel29);

        btnMenuSoftwares.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuSoftwares.setText("Softwares");
        btnMenuSoftwares.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuSoftwares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuSoftwaresActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuSoftwares);

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel30);

        btnMenuRules.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuRules.setText("Reglas");
        btnMenuRules.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuRules.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuRulesActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuRules);

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel31);

        btnMenuLabReports.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuLabReports.setText("Reporte Laboratorios");
        btnMenuLabReports.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuLabReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuLabReportsActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuLabReports);

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel32);

        btnMenuDegreeReports.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuDegreeReports.setText("Reporte Carreras");
        btnMenuDegreeReports.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuDegreeReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuDegreeReportsActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuDegreeReports);

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel33);

        btnMenuBlockReports.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuBlockReports.setText("Reporte Bloqueos");
        btnMenuBlockReports.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuBlockReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuBlockReportsActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuBlockReports);

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel34);

        btnMenuLogOff.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuLogOff.setText("Cerrar Sesion");
        btnMenuLogOff.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuLogOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuLogOffActionPerformed(evt);
            }
        });
        panelMenu2.add(btnMenuLogOff);

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel35);

        jPanel4.setBackground(new java.awt.Color(208, 216, 232));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblDegreeReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre Carrera", "Uso por dia (Min)", "Cantidad de alumnos", "Fecha"
            }
        ));
        jScrollPane1.setViewportView(tblDegreeReport);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 1000, 440));
        jPanel4.add(menuButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 857, -1, -1));

        lblDegreeReport.setText("Reporte Carreras");
        lblDegreeReport.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jPanel4.add(lblDegreeReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, -1));

        jPanel5.setBackground(new java.awt.Color(208, 216, 232));

        cbxAddDegree.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxAddDegree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAddDegreeActionPerformed(evt);
            }
        });

        cbxDeleteDegree.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxDeleteDegree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDeleteDegreeActionPerformed(evt);
            }
        });

        lbDeleteDegree.setText("Eliminar Carrera");
        lbDeleteDegree.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxAddDegree, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxDeleteDegree, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(lbDeleteDegree)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbxAddDegree, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lbDeleteDegree, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxDeleteDegree, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jPanel2.setBackground(new java.awt.Color(208, 216, 232));
        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 70, 260));

        btnPrint.setText("Imprimir");
        jPanel4.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 660, 140, 30));

        btnCreate.setText("Generar");
        jPanel4.add(btnCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 660, 140, 30));

        LbAddDegree.setText("Agregar Carrera");
        LbAddDegree.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel4.add(LbAddDegree, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 100, 20));

        lbEnd.setText("Fecha Fin");
        lbEnd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel4.add(lbEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, -1, -1));
        jPanel4.add(tpEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 180, 30));

        lbStart.setText("Fecha Inicio");
        lbStart.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel4.add(lbStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, -1));
        jPanel4.add(tpStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 180, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(panelMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 720, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1273, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuComputersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuComputersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuComputersActionPerformed

    private void btnMenuDegreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDegreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuDegreeActionPerformed

    private void btnMenuLabsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLabsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuLabsActionPerformed

    private void btnMenuBlocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuBlocksActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuBlocksActionPerformed

    private void btnMenuAcademiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuAcademiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuAcademiesActionPerformed

    private void btnMenuSoftwaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuSoftwaresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuSoftwaresActionPerformed

    private void btnMenuRulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuRulesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuRulesActionPerformed

    private void btnMenuLabReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLabReportsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuLabReportsActionPerformed

    private void btnMenuDegreeReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDegreeReportsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuDegreeReportsActionPerformed

    private void btnMenuBlockReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuBlockReportsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuBlockReportsActionPerformed

    private void btnMenuLogOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLogOffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuLogOffActionPerformed

    private void cbxAddDegreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAddDegreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAddDegreeActionPerformed

    private void cbxDeleteDegreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDeleteDegreeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDeleteDegreeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbAddDegree;
    private javax.swing.JButton btnCreate;
    private utilities.MenuButton btnMenuAcademies;
    private utilities.MenuButton btnMenuBlockReports;
    private utilities.MenuButton btnMenuBlocks;
    private utilities.MenuButton btnMenuComputers;
    private utilities.MenuButton btnMenuDegree;
    private utilities.MenuButton btnMenuDegreeReports;
    private utilities.MenuButton btnMenuLabReports;
    private utilities.MenuButton btnMenuLabs;
    private utilities.MenuButton btnMenuLogOff;
    private utilities.MenuButton btnMenuRules;
    private utilities.MenuButton btnMenuSoftwares;
    private utilities.MenuButton btnMenuStudents;
    private javax.swing.JButton btnPrint;
    private javax.swing.JComboBox<String> cbxAddDegree;
    private javax.swing.JComboBox<String> cbxDeleteDegree;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDeleteDegree;
    private javax.swing.JLabel lbEnd;
    private javax.swing.JLabel lbStart;
    private javax.swing.JLabel lblDegreeReport;
    private utilities.MenuButton menuButton13;
    private panels.PanelMenu panelMenu2;
    private javax.swing.JTable tblDegreeReport;
    private com.github.lgooddatepicker.components.TimePicker tpEnd;
    private com.github.lgooddatepicker.components.TimePicker tpStart;
    // End of variables declaration//GEN-END:variables
}
