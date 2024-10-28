/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import businessObjects.DegreeBO;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dto.ComputerDTO;
import dto.DegreeDTO;
import dto.LaboratoryDTO;
import dtoReports.ReportComputerDTO;
import dtoReports.ReportDegreeDto;
import exception.BusinessException;
import interfaces.IComputerBO;
import interfaces.IDegreeBO;
import interfaces.IDegreeReportBO;
import interfaces.ILaboratoryBO;
import interfaces.IReportComputerBO;
import java.awt.Font;
import java.awt.HeadlessException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


/**
 *
 * @author carli
 */
public class FrmComputerReport extends javax.swing.JFrame {

    private int page = 1;
    private int LIMIT = 10;
    //private IDegreeBO degreeBO;
    private IComputerBO computerBO;
    private ILaboratoryBO laboratoryBO;
    private IReportComputerBO reportComputerBO;
    private LaboratoryDTO laboratoryDTO;
    //private IComputerReportBO computerReportBO;
    //private IDegreeReportBO degreeReportBO;
    private List<ComputerDTO> computerList;
    private List<LaboratoryDTO> laboratoryList;
    //private List<DegreeDTO> degreeList;
    private Set<String> list;
    private List<Integer> computerListName;
    private Long idLab;
    

    /**
     * Creates new form FrmStudentManager
     *
     * @param degreeBO
     */
    public FrmComputerReport(IComputerBO computerBO,ILaboratoryBO laboratoryBO,IReportComputerBO reportComputerBO) {
        initComponents();
        this.computerBO = computerBO;
        this.laboratoryBO = laboratoryBO;
        this.reportComputerBO = reportComputerBO;
        list = new HashSet<>();
        this.loadInitialMethods();

    }

    public void loadInitialMethods() {
        this.setTitle("Administracion de Carrera ");
        this.setResizable(false);
        this.setSize(1280, 780);
        this.setLocationRelativeTo(null);
        this.customizeTableHeader();
        this.fillComboBoxLaboratory();
        this.fillComboBoxComputer();
    }
    
    private void customizeTableHeader() {
        JTableHeader header = tblComputerReport.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 10)); // Cambia el tipo, estilo y tamaño de fuente
    }

    
    private void loadDataOnTable(LocalDate startDate, LocalDate endDate) {
        try {

            // Obtén solo los clientes necesarios para la página actual
            List<ReportComputerDTO> reporteLista = this.reportComputerBO.obtenerDatosCentroComputo(this.computerListName,startDate, endDate);

            // Agrega los registros paginados a la tabla
            this.addInfoTable(reporteLista);

        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }

    }
    

    private void deleteInfoTableDegree() {
        DefaultTableModel tableModel = (DefaultTableModel) this.tblComputerReport.getModel();
        if (tableModel.getRowCount() > 0) {
            for (int row = tableModel.getRowCount() - 1; row > -1; row--) {
                tableModel.removeRow(row);
            }
        }
    }

    private void addInfoTable(List<ReportComputerDTO> reportComputerList) {
        if (reportComputerList == null) {
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.tblComputerReport.getModel();
        reportComputerList.forEach(column
                -> {
            Object[] row = new Object[6];
            row[0] = column.getLabName();
            row[1] = column.getMachineNumber();
            row[2] = column.getStudentCount();
            row[3] = column.getDate();
            row[4] = column.getUsageMinutes();
            row[5] = column.getInactivityMinutes();

            tableModel.addRow(row);
        });
    }

    private Long getSelectedIdTableDegree() {
        int selectedIndex = this.tblComputerReport.getSelectedRow();
        if (selectedIndex != -1) {
            DefaultTableModel model = (DefaultTableModel) this.tblComputerReport.getModel();
            int idIndexRow = 0;
            Long idSelectedDegree = (Long) model.getValueAt(selectedIndex,
                    idIndexRow);
            return idSelectedDegree;
        } else {
            return null;
        }
    }

    private void fillComboBoxComputer() {
        try {
            cbxAddComputer.removeAllItems();
            cbxDeleteComputer.removeAllItems();
            computerList = computerBO.computerListByAcademy(idLab);

            for (ComputerDTO computer : computerList) {
                cbxAddComputer.addItem(computer);
            }
        } catch (BusinessException ex) {
            Logger.getLogger(FrmComputerReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void fillComboBoxLaboratory() {
        try {
            laboratoryList = laboratoryBO.obtainAllLaboratory();
            

            for (LaboratoryDTO laboratory : laboratoryList) {
                cbxLaboratory.addItem(laboratory);
            }
            idLab = cbxLaboratory.getItemAt(1).getId();
        } catch (BusinessException ex) {
            Logger.getLogger(FrmComputerReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String obtainDegreeDataSeparatedByComas(JComboBox<LaboratoryDTO> comboBox) {
        StringBuilder valores = new StringBuilder();

        // Iterar sobre todos los elementos del comboBox
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            LaboratoryDTO item = comboBox.getItemAt(i);
            valores.append(item.getLabName());

            // Añadir una coma si no es el último elemento
            if (i < comboBox.getItemCount() - 1) {
                valores.append(", ");
            }
        }

        return valores.toString();
    }
    
    private String convertLocalDateToString(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("The date cannot be null.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
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
        tblComputerReport = new javax.swing.JTable();
        menuButton13 = new utilities.MenuButton();
        lblDegreeReport = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cbxAddComputer = new javax.swing.JComboBox<>();
        cbxDeleteComputer = new javax.swing.JComboBox<>();
        lbDeleteDegree = new javax.swing.JLabel();
        btnAddDegree = new javax.swing.JButton();
        btnDeleteDegree = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnPrint = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        LbAddDegree = new javax.swing.JLabel();
        lbEnd = new javax.swing.JLabel();
        lbStart = new javax.swing.JLabel();
        dpStartDate = new com.github.lgooddatepicker.components.DatePicker();
        dpFinishDate = new com.github.lgooddatepicker.components.DatePicker();
        cbxLaboratory = new javax.swing.JComboBox<>();
        LbAddDegree1 = new javax.swing.JLabel();
        btnChangeLaboratory = new javax.swing.JButton();

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

        tblComputerReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre Centro Computo", "Numero Computadora", "Cantidad de Alumnos ", "Fecha", "Minutos de uso por dia", "Minutos de inactividad por dia"
            }
        ));
        jScrollPane1.setViewportView(tblComputerReport);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 1000, 440));
        jPanel4.add(menuButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 857, -1, -1));

        lblDegreeReport.setText("Reporte Computadoras");
        lblDegreeReport.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jPanel4.add(lblDegreeReport, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, -1, -1));

        jPanel5.setBackground(new java.awt.Color(208, 216, 232));

        cbxAddComputer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAddComputerActionPerformed(evt);
            }
        });

        cbxDeleteComputer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDeleteComputerActionPerformed(evt);
            }
        });

        lbDeleteDegree.setText("Eliminar Computadoras");
        lbDeleteDegree.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnAddDegree.setText("Agregar");
        btnAddDegree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDegreeActionPerformed(evt);
            }
        });

        btnDeleteDegree.setText("Eliminar");
        btnDeleteDegree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteDegreeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbDeleteDegree, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cbxAddComputer, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAddDegree))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cbxDeleteComputer, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteDegree)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxAddComputer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddDegree))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lbDeleteDegree, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxDeleteComputer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteDegree))
                .addContainerGap())
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jPanel2.setBackground(new java.awt.Color(208, 216, 232));
        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 70, 260));

        btnPrint.setText("Imprimir");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel4.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 660, 140, 30));

        btnCreate.setText("Generar");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        jPanel4.add(btnCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 660, 140, 30));

        LbAddDegree.setText("Cambiar Laboratorio");
        LbAddDegree.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel4.add(LbAddDegree, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 150, 20));

        lbEnd.setText("Fecha Fin");
        lbEnd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel4.add(lbEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, -1, -1));

        lbStart.setText("Fecha Inicio");
        lbStart.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel4.add(lbStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 20, -1, -1));
        jPanel4.add(dpStartDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 180, 30));
        jPanel4.add(dpFinishDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 180, 30));

        cbxLaboratory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLaboratoryActionPerformed(evt);
            }
        });
        jPanel4.add(cbxLaboratory, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 220, -1));

        LbAddDegree1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        LbAddDegree1.setText("Agregar Computadoras");
        jPanel4.add(LbAddDegree1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 150, 20));

        btnChangeLaboratory.setText("Cambiar");
        btnChangeLaboratory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangeLaboratoryActionPerformed(evt);
            }
        });
        jPanel4.add(btnChangeLaboratory, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 110, -1, -1));

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

    private void cbxAddComputerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAddComputerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAddComputerActionPerformed

    private void cbxDeleteComputerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDeleteComputerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxDeleteComputerActionPerformed

    private void btnAddDegreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDegreeActionPerformed
       ComputerDTO computers = (ComputerDTO) cbxAddComputer.getSelectedItem();
        if (computers != null) {
            cbxAddComputer.removeItem(computers);
            cbxDeleteComputer.addItem(computers);
        }
    }//GEN-LAST:event_btnAddDegreeActionPerformed

    private void btnDeleteDegreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteDegreeActionPerformed
        ComputerDTO computers = (ComputerDTO) cbxDeleteComputer.getSelectedItem();
        if (computers != null) {
            cbxDeleteComputer.removeItem(computers);
            cbxAddComputer.addItem(computers);
        }
    }//GEN-LAST:event_btnDeleteDegreeActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        if (dpStartDate.getDate() == null || dpFinishDate.getDate() == null) {
            JOptionPane.showMessageDialog(this, "No se selecciono ninguna fecha.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        list.clear();
        for (int i = 0; i < cbxDeleteComputer.getItemCount(); i++) {
            ComputerDTO computer = cbxDeleteComputer.getItemAt(i);
            this.list.add(computer.getIpAdress());  
        }
        computerListName = new ArrayList(list);
        LocalDate fechaInicio = (LocalDate)dpStartDate.getDate();
        LocalDate fechaFin = (LocalDate)dpFinishDate.getDate();

        
        this.loadDataOnTable(fechaInicio, fechaFin);
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }

        if (path.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se seleccionó ninguna carpeta.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(String.format("%s/ReporteCarreras.pdf", path)));
            doc.open();

            // Descripción de los filtros
            doc.add(new Paragraph("Reporte de Ganancias por Peliculas y Ciudades", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
            doc.add(new Paragraph("Filtros Aplicados:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
            doc.add(new Paragraph("Fechas: " + dpStartDate.getText() + " a " + dpFinishDate.getText()));
            //doc.add(new Paragraph("Carreras: " + obtainDegreeDataSeparatedByComas(cbxDeleteComputer))); // Asegúrate de convertir la lista a cadena
            doc.add(new Paragraph("\n")); // Espacio en blanco

            // Tabla
            PdfPTable tbl = new PdfPTable(4);
            tbl.addCell("Ciudad");
            tbl.addCell("Pelicula");
            tbl.addCell("Fecha");
            tbl.addCell("Ganancia");
            BigDecimal suma = BigDecimal.ZERO;
            for (int i = 0; i < tblComputerReport.getRowCount(); i++) {
                String degreeName = tblComputerReport.getValueAt(i, 0).toString();
                String minuteUsage = tblComputerReport.getValueAt(i, 1).toString();
                String students = tblComputerReport.getValueAt(i, 2).toString();
                String date = tblComputerReport.getValueAt(i, 3).toString();
                tbl.addCell(degreeName);
                tbl.addCell(minuteUsage);
                tbl.addCell(students);
                tbl.addCell(date);
            }

            doc.add(tbl);
            JOptionPane.showMessageDialog(this, "Se imprimió con éxito el documento!");

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Error al crear el archivo PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DocumentException ex) {
            Logger.getLogger(FrmComputerReport.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            doc.close(); // Asegúrate de cerrar el documento en el bloque finally
        }
    }//GEN-LAST:event_btnPrintActionPerformed

    private void cbxLaboratoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLaboratoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxLaboratoryActionPerformed

    private void btnChangeLaboratoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangeLaboratoryActionPerformed
       if (cbxLaboratory !=null) {
            this.laboratoryDTO = (LaboratoryDTO)cbxLaboratory.getSelectedItem();
            this.idLab = laboratoryDTO.getId();
            this.fillComboBoxComputer();
        }
    }//GEN-LAST:event_btnChangeLaboratoryActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LbAddDegree;
    private javax.swing.JLabel LbAddDegree1;
    private javax.swing.JButton btnAddDegree;
    private javax.swing.JButton btnChangeLaboratory;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDeleteDegree;
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
    private javax.swing.JComboBox<ComputerDTO> cbxAddComputer;
    private javax.swing.JComboBox<ComputerDTO> cbxDeleteComputer;
    private javax.swing.JComboBox<LaboratoryDTO> cbxLaboratory;
    private com.github.lgooddatepicker.components.DatePicker dpFinishDate;
    private com.github.lgooddatepicker.components.DatePicker dpStartDate;
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
    private javax.swing.JTable tblComputerReport;
    // End of variables declaration//GEN-END:variables
}
