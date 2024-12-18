/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import businessObjects.AcademyUnityBO;
import businessObjects.BlockBO;
import businessObjects.BlockReportBO;
import businessObjects.ComputerBO;
import businessObjects.DegreeBO;
import businessObjects.DegreeReportBO;
import businessObjects.LaboratoryBO;
import businessObjects.LaboratoryRulesBO;
import businessObjects.RuleBO;
import businessObjects.SoftwareBO;
import businessObjects.StudentBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.AcademyUnityDAO;
import dao.BlockDAO;
import dao.BlockReportDAO;
import dao.ComputerDAO;
import dao.DegreeDAO;
import dao.DegreeReportDAO;
import dao.LaboratoryDAO;
import dao.LaboratoryRulesDAO;
import dao.RuleDAO;
import dao.SoftwareDAO;
import dao.StudentDAO;
import dao.StudentDegreeDAO;
import dto.AcademyDTO;
import dto.LaboratoryDTO;
import dto.RuleDTO;
import exception.BusinessException;
import interfaces.IAcademyUnityBO;

import interfaces.IAcademyUnityDAO;
import interfaces.IBlockBO;
import interfaces.IBlockDAO;
import interfaces.IBlockReportBO;
import interfaces.IBlockReportDAO;
import interfaces.IComputerBO;
import interfaces.IComputerDAO;
import interfaces.IDegreeBO;
import interfaces.IDegreeDAO;
import interfaces.IDegreeReportBO;
import interfaces.IDegreeReportDAO;
import interfaces.ILaboratoryBO;
import interfaces.ILaboratoryDAO;
import interfaces.ILaboratoryRulesBO;
import interfaces.ILaboratoryRulesDAO;
import interfaces.IRuleBO;
import interfaces.IRuleDAO;
import interfaces.ISoftwareBO;
import interfaces.ISoftwareDAO;
import interfaces.IStudentBO;
import interfaces.IStudentDAO;
import interfaces.IStudentDegreeBO;
import interfaces.IStudentDegreeDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aleja
 */
public class FrmLaboratoryManager extends javax.swing.JFrame {

    private int page = 1;
    private int limit = 10;
    ILaboratoryBO laboratoryBO;
    IAcademyUnityBO academyBO;
    AcademyDTO academyDTO;
    List<AcademyDTO> academyList;

    /**
     * Creates new form FrmStudentManager
     */
    public FrmLaboratoryManager(ILaboratoryBO laboratoryBO, IAcademyUnityBO academyBO) {
        initComponents();
        this.academyBO = academyBO;
        this.laboratoryBO = laboratoryBO;
        this.academyList = new ArrayList<>();
        this.loadFrame();
    }

    public void loadFrame() {
        this.fillAcademyComboBox();
        this.academyDTO = cbAcademy.getItemAt(0);
        this.setTitle("Administracion de Laboratorios ");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1280, 780);
        this.setLocationRelativeTo(null);
        this.loadTableLaboratory();
        this.pageStatus();
        this.cbAcademy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshFilter();
            }
        });
    }

    private void refreshFilter() {
        if (cbAcademy.getSelectedItem() != null) {
            this.academyDTO = (AcademyDTO) cbAcademy.getSelectedItem();
            this.page = 1;
            this.loadTableLaboratory();
            this.lblLaboratory.setText(academyDTO.getAcademyName());
            this.pageStatus();
            return;
        }
        JOptionPane.showMessageDialog(this, "Error al buscar el catalogo, Intente de nuevo ", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void loadTableLaboratory() {

        if (academyDTO == null) {
            return;
        }
        try {
            // Borrar registros previos antes de cargar los nuevos
            deleteInfoTableLaboratory();

            // Obtén solo los clientes necesarios para la página actual
            List<LaboratoryDTO> laboratoryList = this.laboratoryBO.laboratoryListByAcademyPaginated(academyDTO.getId(), limit, page);

//         Agrega los registros paginados a la tabla
            this.addInfoTable(laboratoryList);
//         Control de botones de navegación
            btnLeft.setEnabled(page > 1);

        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteInfoTableLaboratory() {
        DefaultTableModel tableModel = (DefaultTableModel) this.tblLaboratory.getModel();
        if (tableModel.getRowCount() > 0) {
            for (int row = tableModel.getRowCount() - 1; row > -1; row--) {
                tableModel.removeRow(row);
            }
        }
    }

    private void addInfoTable(List<LaboratoryDTO> laboratoryList) {
        if (laboratoryList == null) {
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.tblLaboratory.getModel();
        laboratoryList.forEach(column
                -> {
            Object[] row = new Object[4];
            row[0] = column.getId();
            row[1] = column.getLabName();
            row[2] = column.getStartTime();
            row[3] = column.getEndTime();
            tableModel.addRow(row);
        });
    }

    private Long getSelectedIdTableLaboratory() {
        int selectedIndex = this.tblLaboratory.getSelectedRow();
        if (selectedIndex != -1) {
            DefaultTableModel model = (DefaultTableModel) this.tblLaboratory.getModel();
            int idIndexRow = 0;
            Long idSelectedStudent = (Long) model.getValueAt(selectedIndex, idIndexRow);
            return idSelectedStudent;
        } else {
            return null;
        }
    }

    private void fillAcademyComboBox() {
        try {
            academyList = academyBO.getAllAcademies();

            for (AcademyDTO academy : academyList) {
                cbAcademy.addItem(academy);
            }
        } catch (BusinessException ex) {
            Logger.getLogger(FrmLaboratoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void removeLaboratory() {
        Long id = this.getSelectedIdTableLaboratory();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un Laboratorio", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas eliminar el laboratorio seleccionado?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                this.laboratoryBO.deleteLaboratory(id);
                // Recargar la tabla después de eliminar
                loadTableLaboratory();
            } catch (BusinessException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
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
        tblLaboratory = new javax.swing.JTable();
        menuButton13 = new utilities.MenuButton();
        lblLaboratory = new javax.swing.JLabel();
        lblPage = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblAcademyFilter = new javax.swing.JLabel();
        cbAcademy = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnEdit = new utilities.MenuButton();
        btnDelete = new utilities.MenuButton();
        btnAdd = new utilities.MenuButton();
        btnLeft = new utilities.MenuButton();
        btnRight = new utilities.MenuButton();
        btnAddRule = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ItsonLogo.png"))); // NOI18N
        panelMenu2.add(jLabel2);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel18);

        btnMenuStudents.setForeground(new java.awt.Color(255, 255, 255));
        btnMenuStudents.setText("Estudiantes");
        btnMenuStudents.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnMenuStudents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuStudentsActionPerformed(evt);
            }
        });
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

        tblLaboratory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Hora de Apertura", "Hora de Cierre"
            }
        ));
        jScrollPane1.setViewportView(tblLaboratory);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 710, 440));
        jPanel4.add(menuButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 857, -1, -1));

        lblLaboratory.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblLaboratory.setText("Laboratorio");
        jPanel4.add(lblLaboratory, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 210, -1));

        lblPage.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPage.setText("Pagina 01");
        jPanel4.add(lblPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 640, 120, -1));

        jPanel5.setBackground(new java.awt.Color(208, 216, 232));

        lblAcademyFilter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblAcademyFilter.setText("Filtrar por Academia");

        cbAcademy.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        cbAcademy.setForeground(new java.awt.Color(0, 9, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAcademyFilter)
                    .addComponent(cbAcademy, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblAcademyFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAcademy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jPanel2.setBackground(new java.awt.Color(208, 216, 232));

        btnEdit.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit.png"))); // NOI18N
        btnEdit.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/editNormal.png"))); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jPanel2.add(btnEdit);

        btnDelete.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        btnDelete.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/deleteNormal.png"))); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDelete);

        btnAdd.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnAdd.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addNormal.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdd);

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 70, 260));

        btnLeft.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leftSelected.png"))); // NOI18N
        btnLeft.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left.png"))); // NOI18N
        btnLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLeftActionPerformed(evt);
            }
        });
        jPanel4.add(btnLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 630, -1, -1));

        btnRight.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rightSelected.png"))); // NOI18N
        btnRight.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right.png"))); // NOI18N
        btnRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRightActionPerformed(evt);
            }
        });
        jPanel4.add(btnRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 630, -1, -1));

        btnAddRule.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddRule.setText("Agregar Reglas");
        btnAddRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRuleActionPerformed(evt);
            }
        });
        jPanel4.add(btnAddRule, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 60, 140, 30));

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.removeLaboratory();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        FrmNewLaboratoryManager laboratory = new FrmNewLaboratoryManager(laboratoryBO, academyBO, academyDTO);
        laboratory.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRightActionPerformed
        // TODO add your handling code here:
        page++;
        this.pageStatus();
    }//GEN-LAST:event_btnRightActionPerformed

    private void btnMenuComputersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuComputersActionPerformed
       IConnectionBD connection = new ConnectionDB();
        IComputerDAO computerDAO = new ComputerDAO(connection);
        ILaboratoryDAO laboratoryDAO = new LaboratoryDAO(connection);
        IComputerBO computerBO =new ComputerBO(computerDAO, laboratoryDAO);
        IAcademyUnityDAO academyDAO = new AcademyUnityDAO(connection);
        ILaboratoryBO laboratoryBO = new LaboratoryBO(laboratoryDAO, academyDAO);
        IAcademyUnityBO academyBO = new AcademyUnityBO(academyDAO);
        
        
        
        FrmComputerManager frmComputerManager = new FrmComputerManager(computerBO, laboratoryBO, academyBO);
        this.dispose();
        frmComputerManager.setVisible(true);
    }//GEN-LAST:event_btnMenuComputersActionPerformed

    private void btnMenuDegreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDegreeActionPerformed
         IConnectionBD connection = new ConnectionDB();
         IDegreeDAO degreeDAO = new DegreeDAO(connection);
         IStudentDegreeDAO studentDegreeDAO=new StudentDegreeDAO(connection);
         IDegreeBO degreeBO = new DegreeBO(degreeDAO, studentDegreeDAO);
         FrmDegreeManager frmDegreeManager = new FrmDegreeManager(degreeBO);
         this.dispose();
         frmDegreeManager.setVisible(true);
    }//GEN-LAST:event_btnMenuDegreeActionPerformed

    private void btnMenuLabsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLabsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuLabsActionPerformed

    private void btnMenuBlocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuBlocksActionPerformed
        IConnectionBD connection = new ConnectionDB();
        IStudentDAO studentDAO = new StudentDAO(connection);
        IStudentBO studentBO = new StudentBO(studentDAO);
        IRuleDAO ruleDAO = new RuleDAO(connection);
        IRuleBO ruleBO = new RuleBO(ruleDAO);
        IBlockDAO blockDAO = new BlockDAO(connection);
        IBlockBO blockBO = new BlockBO(blockDAO);
        
        
        
        FrmBlockManager blockManager = new FrmBlockManager(blockBO, ruleBO, studentBO);
        this.dispose();
        blockManager.setVisible(true);
    }//GEN-LAST:event_btnMenuBlocksActionPerformed

    private void btnMenuAcademiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuAcademiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuAcademiesActionPerformed

    private void btnMenuSoftwaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuSoftwaresActionPerformed
         IConnectionBD connection = new ConnectionDB();
         ISoftwareDAO softwareDAO = new SoftwareDAO(connection);
         ISoftwareBO softwareBO = new SoftwareBO(softwareDAO);
        
        FrmSoftwareManager frmSoftwareManager = new FrmSoftwareManager(softwareBO);
        this.dispose();
        frmSoftwareManager.setVisible(true);
    }//GEN-LAST:event_btnMenuSoftwaresActionPerformed

    private void btnMenuRulesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuRulesActionPerformed
        IConnectionBD connection = new ConnectionDB();
        IRuleDAO ruleDAO = new RuleDAO(connection);
        IRuleBO ruleBO = new RuleBO(ruleDAO);
        
        FrmRulesManager frmRulesManager = new FrmRulesManager(ruleBO);
        this.dispose();
        frmRulesManager.setVisible(true);
    }//GEN-LAST:event_btnMenuRulesActionPerformed

    private void btnMenuLabReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLabReportsActionPerformed
         IConnectionBD connection = new ConnectionDB();
        IDegreeReportDAO degreeReportDAO = new DegreeReportDAO(connection);
        IDegreeDAO degreeDAO = new DegreeDAO(connection);
        IStudentDegreeDAO studentDegreeDAO = new StudentDegreeDAO(connection);
        IDegreeBO degreeBO = new DegreeBO(degreeDAO, studentDegreeDAO);
        
        IDegreeReportBO degreeReportBO = new DegreeReportBO(degreeReportDAO);
        
        FrmDegreeReport degreeReport = new FrmDegreeReport(degreeBO, degreeReportBO);
        this.disable();
        degreeReport.setVisible(true);
    }//GEN-LAST:event_btnMenuLabReportsActionPerformed

    private void btnMenuDegreeReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDegreeReportsActionPerformed
        IConnectionBD connection = new ConnectionDB();
        IDegreeReportDAO degreeReportDAO = new DegreeReportDAO(connection);
        IDegreeDAO degreeDAO = new DegreeDAO(connection);
        IStudentDegreeDAO studentDegreeDAO = new StudentDegreeDAO(connection);
        IDegreeBO degreeBO = new DegreeBO(degreeDAO, studentDegreeDAO);
        
        IDegreeReportBO degreeReportBO = new DegreeReportBO(degreeReportDAO);
        
        FrmDegreeReport degreeReport = new FrmDegreeReport(degreeBO, degreeReportBO);
        this.dispose();
        degreeReport.setVisible(true);
    }//GEN-LAST:event_btnMenuDegreeReportsActionPerformed

    private void btnMenuBlockReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuBlockReportsActionPerformed
         IConnectionBD connection = new ConnectionDB();
        IBlockReportDAO blockReportDAO = new BlockReportDAO(connection);
        IBlockReportBO blockReportBO = new BlockReportBO(blockReportDAO);
        
        FrmLocksReport frmLocksReport = new FrmLocksReport(blockReportBO);
        this.dispose();
        frmLocksReport.setVisible(true);
    }//GEN-LAST:event_btnMenuBlockReportsActionPerformed

    private void btnMenuLogOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLogOffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuLogOffActionPerformed

    private void btnLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLeftActionPerformed
        page--;
        this.pageStatus();

    }//GEN-LAST:event_btnLeftActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            if (this.getSelectedIdTableLaboratory() == null) {
                JOptionPane.showMessageDialog(this, "Por favor selecciona un Laboratorio", "Información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            LaboratoryDTO laboratoryDTO = laboratoryBO.findLaboratoryByID(this.getSelectedIdTableLaboratory());
            FrmUpdateLaboratoryManager laboratory = new FrmUpdateLaboratoryManager(laboratoryBO, academyBO, laboratoryDTO);
            laboratory.setVisible(true);
//        if (this.getSelectedIdTableLaboratory() == null) {
//            return;
//        }
//        try {
//            // TODO add your handling code here:
//            LaboratoryDTO laboratory = laboratoryBO.findLaboratoryByID(this.getSelectedIdTableLaboratory());
//            System.out.println(laboratory.getLabName());
//        } catch (BusinessException ex) {
//            Logger.getLogger(FrmLaboratoryManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
        } catch (BusinessException ex) {
            Logger.getLogger(FrmLaboratoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditActionPerformed


    private void btnMenuStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuStudentsActionPerformed
  IConnectionBD connection = new ConnectionDB();
        IStudentDAO studentDAO = new StudentDAO(connection);
        IStudentBO studentBO = new StudentBO(studentDAO);
        IDegreeDAO degreeDAO = new DegreeDAO(connection);
        IStudentDegreeDAO studentDegreeDAO= new StudentDegreeDAO(connection);
        IDegreeBO degreeBO = new DegreeBO(degreeDAO,studentDegreeDAO);
       
        FrmStudentManager frmStudentManager = new FrmStudentManager(studentBO, degreeBO);
        this.dispose();
        frmStudentManager.setVisible(true);       
    }//GEN-LAST:event_btnMenuStudentsActionPerformed

    private void btnAddRuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRuleActionPerformed
          IConnectionBD connectionBD = new ConnectionDB();
        ILaboratoryRulesDAO laboratoryRulesDAO = new LaboratoryRulesDAO(connectionBD);
        ILaboratoryDAO laboratoryDAO = new LaboratoryDAO(connectionBD);
        IRuleDAO ruleDAO = new RuleDAO(connectionBD);
        ILaboratoryRulesBO laboratoryRulesBO = new LaboratoryRulesBO(laboratoryDAO, ruleDAO, laboratoryRulesDAO);
        IAcademyUnityDAO academyDAO = new AcademyUnityDAO(connectionBD);
        ILaboratoryBO laboratoryBO = new LaboratoryBO(laboratoryDAO, academyDAO);
        RuleDTO rulesDTO = new RuleDTO();
        IRuleBO rulesBO = new RuleBO(ruleDAO);
        try {
            if (this.getSelectedIdTableLaboratory() == null) {
                JOptionPane.showMessageDialog(this, "Por favor selecciona un Laboratorio", "Información", JOptionPane.ERROR_MESSAGE);
                return;
            }
            LaboratoryDTO laboratoryDTO = laboratoryBO.findLaboratoryByID(this.getSelectedIdTableLaboratory());
            FrmLaboratoryRules laboratory = new FrmLaboratoryRules(laboratoryRulesBO, laboratoryDTO, rulesDTO, rulesBO);
            laboratory.setVisible(true);
             } catch (BusinessException ex) {
            Logger.getLogger(FrmLaboratoryManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddRuleActionPerformed

    public void pageStatus() {
        String pageNumber = String.valueOf(page);
        if (pageNumber.length() == 1) {
            pageNumber = "0" + pageNumber;
        }

        lblPage.setText("Pagina " + pageNumber);
        leftButonStatus();
        rightButonStatus();
    }

    private void leftButonStatus() {
        if (page > 1) {
            btnLeft.setEnabled(true);
            return;
        }
        btnLeft.setEnabled(false);
    }

    private void rightButonStatus() {

        if (academyDTO == null) {
            btnRight.setEnabled(false);
            return;
        }
        try {
            btnRight.setEnabled(true);
            if (this.laboratoryBO.laboratoryListByAcademyPaginated(academyDTO.getId(), limit, page + 1) == null
                    || this.laboratoryBO.laboratoryListByAcademyPaginated(academyDTO.getId(), limit, page + 1).isEmpty()) {
                btnRight.setEnabled(false);
            }
        } catch (BusinessException ex) {
            System.out.println(ex);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.MenuButton btnAdd;
    private javax.swing.JButton btnAddRule;
    private utilities.MenuButton btnDelete;
    private utilities.MenuButton btnEdit;
    private utilities.MenuButton btnLeft;
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
    private utilities.MenuButton btnRight;
    private javax.swing.JComboBox<AcademyDTO> cbAcademy;
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
    private javax.swing.JLabel lblAcademyFilter;
    private javax.swing.JLabel lblLaboratory;
    private javax.swing.JLabel lblPage;
    private utilities.MenuButton menuButton13;
    private panels.PanelMenu panelMenu2;
    private javax.swing.JTable tblLaboratory;
    // End of variables declaration//GEN-END:variables
}
