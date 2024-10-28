/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;


import businessObjects.BlockReportBO;
import businessObjects.DegreeBO;
import businessObjects.RuleBO;
import businessObjects.SoftwareBO;
import businessObjects.StudentBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.BlockReportDAO;
import dao.DegreeDAO;
import dao.RuleDAO;
import dao.SoftwareDAO;
import dao.StudentDAO;
import dto.AcademyDTO;
import dto.ComputerDTO;
import dto.DegreeDTO;
import dto.LaboratoryDTO;
import exception.BusinessException;
import interfaces.IAcademyUnityBO;
import interfaces.IBlockReportBO;
import interfaces.IBlockReportDAO;
import interfaces.IComputerBO;
import interfaces.IDegreeBO;
import interfaces.IDegreeDAO;
import interfaces.ILaboratoryBO;
import interfaces.IRuleBO;
import interfaces.IRuleDAO;
import interfaces.ISoftwareBO;
import interfaces.ISoftwareDAO;
import interfaces.IStudentBO;
import interfaces.IStudentDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author carli
 */
public class FrmComputerManager extends javax.swing.JFrame {
    
    private IComputerBO computerBO;
    private ILaboratoryBO laboratoryBO;
    private LaboratoryDTO laboratoryDTO;
    private AcademyDTO academyDTO;
    private IAcademyUnityBO academyBO;
    private List<LaboratoryDTO> laboratoryList;
    private List<AcademyDTO> academyList;
    private int page = 1;
    private int limit = 10;
    private Long lab = 1L;
    private Long academy = 1L;
    /**
     * Creates new form FrmStudentManager
     */
    public FrmComputerManager(IComputerBO computerBO,ILaboratoryBO laboratoryBO,IAcademyUnityBO academyBO) {
        initComponents();
        this.computerBO = computerBO;
        this.laboratoryBO = laboratoryBO;
        this.academyBO = academyBO;
        loadInitialComponents();
    }

    public void loadInitialComponents() {
        this.setTitle("Administracion de Computadoras");
//        this.laboratoryDTO = cbLaboratory.getItemAt(0);
        this.setResizable(false);
        this.setSize(1280, 780);
        this.setLocationRelativeTo(null);
        this.loadTableComputer();
        this.pageStatus();
        this.fillLaboratoryComboBox();
        this.fillAcademyComboBox();
    }
    
    private void fillLaboratoryComboBox() {
        try {
            cbLaboratory.removeAllItems();
            laboratoryList = laboratoryBO.laboratoryListByAcademy(academy);

            for (LaboratoryDTO laboratory : laboratoryList) {
                cbLaboratory.addItem(laboratory);
            }
        } catch (BusinessException ex) {
            Logger.getLogger(FrmLaboratoryManager.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void deleteInfoTableComputers() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblComputer.getModel();
        if (modeloTabla.getRowCount() > 0) {
            for (int row = modeloTabla.getRowCount() - 1; row > -1; row--) {
                modeloTabla.removeRow(row);
            }
        }
    }
    private void leftButonStatus() {
        if (page > 1) {
            btnLeft.setEnabled(true);
            return;
        }
        btnLeft.setEnabled(false);
    }

    private void loadTableComputer() {
        try {
            // Borrar registros previos antes de cargar los nuevos
            deleteInfoTableComputers();


            // Obtén solo los clientes necesarios para la página actual
            List<ComputerDTO> computerList = this.computerBO.computerListByAcademyPaginated(page, limit, lab);

         //Agrega los registros paginados a la tabla

    this.addInfoTable(computerList);
          //Control de botones de navegación
                    btnLeft.setEnabled(page > 1);
        
                } catch (BusinessException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
                }
    }
    private void rightButonStatus() {

        try {
            btnRight.setEnabled(true);
            if (this.computerBO.computerListByAcademyPaginated(page, limit, lab) == null
                    || this.computerBO.computerListByAcademyPaginated(page+1, limit, lab).isEmpty()) {
                btnRight.setEnabled(false);
            }
        } catch (BusinessException ex) {
            System.out.println(ex);
        }
    }
    
    public void pageStatus() {
        String pageNumber = String.valueOf(page);
        if (pageNumber.length() == 1) {
            pageNumber = "0" + pageNumber;
        }

        lblPage.setText("Pagina " + pageNumber);
        leftButonStatus();
        rightButonStatus();
    }
    
    private void addInfoTable(List<ComputerDTO> computerList) {
        if (computerList == null) {
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.tblComputer.getModel();
        computerList.forEach(column
                -> {
            Object[] row = new Object[5];
            row[0] = column.getId();
            row[1] = column.getIpAdress();
            row[2] = column.getMachineNumber();
            row[3] = column.getStatus();
            row[4] = column.getComputerType();
            tableModel.addRow(row);
        });
    }
    private String getSelectedIdTableComputer() {
        int selectedIndex = this.tblComputer.getSelectedRow();
        if (selectedIndex != -1) {
            DefaultTableModel model = (DefaultTableModel) this.tblComputer.getModel();
            int idIndexRow = 1;
            String idSelectedStudent = (String) model.getValueAt(selectedIndex,
                    idIndexRow);
            return idSelectedStudent;
        } else {
            return null;
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
        tblComputer = new javax.swing.JTable();
        menuButton13 = new utilities.MenuButton();
        lblStudent = new javax.swing.JLabel();
        lblPage = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblDegreeFilter = new javax.swing.JLabel();
        cbAcademy = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new utilities.MenuButton();
        btnEdit = new utilities.MenuButton();
        btnDelete = new utilities.MenuButton();
        btnLeft = new utilities.MenuButton();
        btnRight = new utilities.MenuButton();
        lblDegreeFilter1 = new javax.swing.JLabel();
        cbLaboratory = new javax.swing.JComboBox<>();
        btnGoLab = new javax.swing.JButton();
        btnGoAcademy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        tblComputer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "IP Address", "Machine Number", "Status", "Type"
            }
        ));
        jScrollPane1.setViewportView(tblComputer);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 710, 440));
        jPanel4.add(menuButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 857, -1, -1));

        lblStudent.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblStudent.setText("Computadoras");
        jPanel4.add(lblStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, -1, -1));

        lblPage.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPage.setText("Pagina 01");
        jPanel4.add(lblPage, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 640, 120, -1));

        jPanel5.setBackground(new java.awt.Color(208, 216, 232));

        lblDegreeFilter.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDegreeFilter.setText("Filtrar por Academia");

        cbAcademy.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDegreeFilter)
                .addGap(104, 104, 104))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbAcademy, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDegreeFilter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbAcademy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jPanel2.setBackground(new java.awt.Color(208, 216, 232));

        btnAdd.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        btnAdd.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addNormal.png"))); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdd);

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

        lblDegreeFilter1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblDegreeFilter1.setText("Filtrar por Laboratorio");
        jPanel4.add(lblDegreeFilter1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 60, -1, -1));

        cbLaboratory.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPanel4.add(cbLaboratory, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 220, 30));

        btnGoLab.setText("Ir");
        btnGoLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoLabActionPerformed(evt);
            }
        });
        jPanel4.add(btnGoLab, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, -1, -1));

        btnGoAcademy.setText("Ir");
        btnGoAcademy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoAcademyActionPerformed(evt);
            }
        });
        jPanel4.add(btnGoAcademy, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

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
        
        try {
            FrmDeleteComputer deleteComputer = new FrmDeleteComputer(computerBO, laboratoryBO, getSelectedIdTableComputer());
            deleteComputer.setVisible(true);
        } catch (BusinessException ex) {
            Logger.getLogger(FrmComputerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        FrmAddComputer addComputer = new FrmAddComputer(computerBO, laboratoryBO, this.lab);
        addComputer.setVisible(true);
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRightActionPerformed
        page++;
        this.pageStatus();
    }//GEN-LAST:event_btnRightActionPerformed

    private void btnMenuComputersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuComputersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuComputersActionPerformed

    private void btnMenuDegreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDegreeActionPerformed
         IConnectionBD connection = new ConnectionDB();
         IDegreeDAO degreeDAO = new DegreeDAO(connection);
         IDegreeBO degreeBO = new DegreeBO(degreeDAO);
         FrmDegreeManager frmDegreeManager = new FrmDegreeManager(degreeBO);
         this.dispose();
         frmDegreeManager.setVisible(true);
    }//GEN-LAST:event_btnMenuDegreeActionPerformed

    private void btnMenuLabsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuLabsActionPerformed
        FrmLaboratoryManager laboratory = new FrmLaboratoryManager(laboratoryBO, academyBO);
        this.dispose();
        laboratory.setVisible(true);
    }//GEN-LAST:event_btnMenuLabsActionPerformed

    private void btnMenuBlocksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuBlocksActionPerformed
        // TODO add your handling code here:
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
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuLabReportsActionPerformed

    private void btnMenuDegreeReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuDegreeReportsActionPerformed
        // TODO add your handling code here:
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

    private void btnGoLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoLabActionPerformed
        if (cbLaboratory !=null) {
            this.laboratoryDTO = (LaboratoryDTO)cbLaboratory.getSelectedItem();
            this.lab = laboratoryDTO.getId();
            this.loadTableComputer();
        }
    }//GEN-LAST:event_btnGoLabActionPerformed

    private void btnGoAcademyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoAcademyActionPerformed
        if (cbAcademy !=null) {
            this.academyDTO = (AcademyDTO)cbAcademy.getSelectedItem();
            this.academy = academyDTO.getId();
            this.fillLaboratoryComboBox();
            this.laboratoryDTO = (LaboratoryDTO)cbLaboratory.getSelectedItem();
            this.lab = laboratoryDTO.getId();
            this.loadTableComputer();
        }
    }//GEN-LAST:event_btnGoAcademyActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            
            ComputerDTO com = computerBO.findByIPComputer(getSelectedIdTableComputer());
            
            FrmEditComputer editComputer = new FrmEditComputer(computerBO, laboratoryBO, com,this);
            
            editComputer.setVisible(true);
        } catch (BusinessException ex) {
            Logger.getLogger(FrmComputerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnMenuStudentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuStudentsActionPerformed
          IConnectionBD connection = new ConnectionDB();
        IStudentDAO studentDAO = new StudentDAO(connection);
        IStudentBO studentBO = new StudentBO(studentDAO);
        IDegreeDAO degreeDAO = new DegreeDAO(connection);
        IDegreeBO degreeBO = new DegreeBO(degreeDAO);
       
        FrmStudentManager frmStudentManager = new FrmStudentManager(studentBO, degreeBO);
        this.dispose();
        frmStudentManager.setVisible(true);
    }//GEN-LAST:event_btnMenuStudentsActionPerformed
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.MenuButton btnAdd;
    private utilities.MenuButton btnDelete;
    private utilities.MenuButton btnEdit;
    private javax.swing.JButton btnGoAcademy;
    private javax.swing.JButton btnGoLab;
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
    private javax.swing.JComboBox<LaboratoryDTO> cbLaboratory;
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
    private javax.swing.JLabel lblDegreeFilter;
    private javax.swing.JLabel lblDegreeFilter1;
    private javax.swing.JLabel lblPage;
    private javax.swing.JLabel lblStudent;
    private utilities.MenuButton menuButton13;
    private panels.PanelMenu panelMenu2;
    private javax.swing.JTable tblComputer;
    // End of variables declaration//GEN-END:variables
}
