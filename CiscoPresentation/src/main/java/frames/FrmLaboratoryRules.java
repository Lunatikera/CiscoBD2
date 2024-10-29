/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import businessObjects.LaboratoryBO;
import businessObjects.LaboratoryRulesBO;
import businessObjects.RuleBO;
import dto.LaboratoryDTO;
import dto.LaboratoryRulesDTO;
import dto.RuleDTO;
import dto.SoftwareDTO;
import exception.BusinessException;
import interfaces.IAcademyUnityDAO;
import interfaces.ILaboratoryBO;
import interfaces.ILaboratoryDAO;
import interfaces.ILaboratoryRulesBO;
import interfaces.ILaboratoryRulesDAO;
import interfaces.IRuleBO;
import interfaces.IRuleDAO;
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
public class FrmLaboratoryRules extends javax.swing.JFrame {

    RuleDTO rulesDTO;
    IRuleBO rulesBO;
    LaboratoryDTO laboratoryDTO;
    List<RuleDTO> rulesListDTO;
    List<LaboratoryRulesDTO> ruleList;
    LaboratoryRulesDTO laboratoryRulesDTO;
    ILaboratoryRulesBO laboratoryRulesBO;
    private int page = 1;
    private int limit = 10;

    /**
     * Creates new form FrmAvailableSoftware
     */
    public FrmLaboratoryRules(ILaboratoryRulesBO laboratoryRulesBO, LaboratoryDTO laboratoryDTO, RuleDTO rulesDTO, IRuleBO rulesBO) {
        initComponents();
        this.rulesDTO = rulesDTO;
        this.rulesListDTO = new ArrayList();
        this.ruleList = new ArrayList();
        this.laboratoryDTO = laboratoryDTO;
        this.laboratoryRulesBO = laboratoryRulesBO;
        this.rulesBO = rulesBO;
        this.loadFrame();
    }

    public void loadFrame() {
        this.setTitle("Software Disponibles ");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1051, 489);
        this.setLocationRelativeTo(null);
        this.loadTableInstalados();
        this.loadTableDisponibles();

        this.lblIdComputer.setText("Laboratorio " + laboratoryDTO.getLabName() + toString());
    }

    private void deleteInfoTableInstalados() {
        DefaultTableModel tableModel = (DefaultTableModel) this.tblInstalados.getModel();
        if (tableModel.getRowCount() > 0) {
            for (int row = tableModel.getRowCount() - 1; row > -1; row--) {
                tableModel.removeRow(row);
            }
        }
    }

    private void deleteInfoTableDisponible() {
        DefaultTableModel tableModel = (DefaultTableModel) this.tblDisponibles.getModel();
        if (tableModel.getRowCount() > 0) {
            for (int row = tableModel.getRowCount() - 1; row > -1; row--) {
                tableModel.removeRow(row);
            }
        }
    }

    private void addInfoTableDisponibles(List<RuleDTO> ruleList) {
        if (ruleList == null) {
            return;
        }
        System.out.println(ruleList);

        DefaultTableModel tableModel = (DefaultTableModel) this.tblDisponibles.getModel();
        ruleList.forEach(RuleDTO
                -> {

            Object[] row = new Object[1];
            row[0] = RuleDTO;
            tableModel.addRow(row);
        });
    }

    private void addInfoTableInstalado(List<LaboratoryRulesDTO> ruleList) {
        if (ruleList == null) {
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.tblInstalados.getModel();
        ruleList.forEach(LaboratoryRulesDTO
                -> {
            System.out.println(LaboratoryRulesDTO.getRule());
            try {

                rulesDTO = rulesBO.findDegreeForId(LaboratoryRulesDTO.getRule());

                System.out.println(rulesDTO);
            } catch (BusinessException ex) {
                Logger.getLogger(FrmLaboratoryRules.class.getName()).log(Level.SEVERE, null, ex);
            }

            Object[] row = new Object[1];
            row[0] = rulesDTO;
            tableModel.addRow(row);
        });
    }

    private RuleDTO getTableSoftware() {
        int selectedIndex = this.tblDisponibles.getSelectedRow();
        if (selectedIndex != -1) {
            DefaultTableModel model = (DefaultTableModel) this.tblDisponibles.getModel();
            int idIndexRow = 0;
            RuleDTO SelectedRule = (RuleDTO) model.getValueAt(selectedIndex,
                    idIndexRow);
            return SelectedRule;
        } else {
            return null;
        }
    }

    private RuleDTO getTableSoftwareInstalados() {
        int selectedIndex = this.tblInstalados.getSelectedRow();
        if (selectedIndex != -1) {
            DefaultTableModel model = (DefaultTableModel) this.tblInstalados.getModel();
            int idIndexRow = 0;
            RuleDTO SelectedRule = (RuleDTO) model.getValueAt(selectedIndex,
                    idIndexRow);
            return SelectedRule;
        } else {
            return null;
        }
    }

    public void loadTableDisponibles() {
        if (this.laboratoryDTO == null) {
            throw new IllegalStateException("laboratoryDTO is not initialized.");
        }
        try {
            // Borrar registros previos antes de cargar los nuevos
            deleteInfoTableDisponible();

            // Obtén solo los clientes necesarios para la página actual
            rulesListDTO = rulesBO.getRules();

            System.out.println(getRules());
            List<RuleDTO> temp = new ArrayList<>();
            for (int i = 0; i < rulesListDTO.size(); i++) {
                if (!getRules().contains(rulesListDTO.get(i))) {
                    temp.add(rulesListDTO.get(i));
                    System.out.println(temp);
                }

            }
            
            //Agrega los registros paginados a la tabla
            this.addInfoTableDisponibles(temp);
            //Control de botones de navegación

        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadTableInstalados() {
        if (this.laboratoryDTO == null) {
            throw new IllegalStateException("laboratoryDTO is not initialized.");
        }
        try {
            // Borrar registros previos antes de cargar los nuevos
            deleteInfoTableInstalados();

            // Obtén solo los clientes necesarios para la página actual
            ruleList = this.laboratoryRulesBO.getRulesAppliedByLaboratory(laboratoryDTO.getId());

            //Agrega los registros paginados a la tabla
            this.addInfoTableInstalado(ruleList);
            //Control de botones de navegación

        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private List<RuleDTO> getRules(){
        List<RuleDTO> newList = new ArrayList<>();
        for (LaboratoryRulesDTO ruleDTO : ruleList) {
            
            try {
                newList.add(rulesBO.findDegreeForId(ruleDTO.getRule()));
            
            } catch (BusinessException ex) {
                Logger.getLogger(FrmLaboratoryRules.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return newList;
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
        lblName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInstalados = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDisponibles = new javax.swing.JTable();
        lblName1 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblName.setText("Reglas Aplicadas");

        tblInstalados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Instalados"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblInstalados);

        tblDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Disponibles"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDisponibles);

        lblName1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblName1.setText("Reglas Disponibles");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(lblName1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar)
                            .addComponent(btnEliminar))
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(136, 136, 136))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar)
                        .addGap(145, 145, 145)
                        .addComponent(btnEliminar)
                        .addGap(108, 108, 108))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        RuleDTO rulesDTO = this.getTableSoftware();
        if (rulesDTO == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione una regla para agregar", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LaboratoryRulesDTO laboratoryRulesDTO = new LaboratoryRulesDTO();
        laboratoryRulesDTO.setLaboratory(laboratoryDTO.getId());
        laboratoryRulesDTO.setRule(rulesDTO.getId());
        try {
            laboratoryRulesBO.saveLaboratoryRule(laboratoryRulesDTO);
            this.loadTableInstalados();
            this.loadTableDisponibles();
        } catch (BusinessException ex) {
            Logger.getLogger(FrmLaboratoryRules.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        RuleDTO laboratoryRulesDTO = this.getTableSoftwareInstalados();
        if (rulesDTO == null) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione un software para eliminar", "Información", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            laboratoryRulesBO.deleteLaboratoryRule(laboratoryDTO.getId(), rulesDTO.getId());
            this.loadTableInstalados();
            this.loadTableDisponibles();
        } catch (BusinessException ex) {
            Logger.getLogger(FrmLaboratoryRules.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName1;
    private javax.swing.JTable tblDisponibles;
    private javax.swing.JTable tblInstalados;
    // End of variables declaration//GEN-END:variables
}
