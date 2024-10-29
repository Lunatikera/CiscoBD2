/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import businessObjects.SoftwareBO;
import dto.ComputerDTO;
import dto.ComputerSoftwareDTO;
import dto.LaboratoryDTO;
import dto.SoftwareDTO;
import exception.BusinessException;
import interfaces.IComputerBO;
import interfaces.IComputerSoftwareBO;
import interfaces.ILaboratoryBO;
import interfaces.ISoftwareBO;
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

    ISoftwareBO softwareBO;
    ComputerDTO computerDTO;
    IComputerSoftwareBO computerSoftwareBO;
    private int page = 1;
    private int limit = 10;

    /**
     * Creates new form FrmAvailableSoftware
     */
    public FrmLaboratoryRules(IComputerSoftwareBO computerSoftwareBO, ISoftwareBO softwareBO, ComputerDTO computerDTO) {
        initComponents();
        this.computerSoftwareBO=computerSoftwareBO;
        this.softwareBO = softwareBO;
        this.computerDTO = computerDTO;
        this.loadFrame();
    }

    public void loadFrame() {
        this.setTitle("Software Disponibles ");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setSize(1051, 489);
        this.setLocationRelativeTo(null);
        this.loadTableDisponibles();
        this.loadTableInstalados();
        
        this.lblIdComputer.setText("Computadora "+computerDTO.getMachineNumber().toString());
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

    private void addInfoTableDisponibles(List<SoftwareDTO> softwareList) {
        if (softwareList == null) {
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.tblDisponibles.getModel();
        softwareList.forEach(softwareDTO
                -> {
            Object[] row = new Object[1];
            row[0] = softwareDTO;
            tableModel.addRow(row);
        });
    }

    private void addInfoTableInstalado(List<SoftwareDTO> softwareList) {
        if (softwareList == null) {
            return;
        }

        DefaultTableModel tableModel = (DefaultTableModel) this.tblInstalados.getModel();
        softwareList.forEach(softwareDTO
                -> {
            Object[] row = new Object[1];
            row[0] = softwareDTO;
            tableModel.addRow(row);
        });
    }

    private SoftwareDTO getTableSoftware() {
        int selectedIndex = this.tblDisponibles.getSelectedRow();
        if (selectedIndex != -1) {
            DefaultTableModel model = (DefaultTableModel) this.tblDisponibles.getModel();
            int idIndexRow = 0;
            SoftwareDTO SelectedSoftware = (SoftwareDTO) model.getValueAt(selectedIndex,
                    idIndexRow);
            return SelectedSoftware;
        } else {
            return null;
        }
    }

    private SoftwareDTO getTableSoftwareInstalados() {
        int selectedIndex = this.tblInstalados.getSelectedRow();
        if (selectedIndex != -1) {
            DefaultTableModel model = (DefaultTableModel) this.tblInstalados.getModel();
            int idIndexRow = 0;
            SoftwareDTO SelectedSoftware = (SoftwareDTO) model.getValueAt(selectedIndex,
                    idIndexRow);
            return SelectedSoftware;
        } else {
            return null;
        }
    }

    public void loadTableDisponibles() {
        if (this.computerDTO == null) {
            throw new IllegalStateException("computerDTO is not initialized.");
        }
        try {
            // Borrar registros previos antes de cargar los nuevos
            deleteInfoTableDisponible();

            // Obtén solo los clientes necesarios para la página actual
            List<SoftwareDTO> softwareList = this.softwareBO.getSoftwareNotInstalledByComputer(computerDTO.getId());

            //Agrega los registros paginados a la tabla
            this.addInfoTableDisponibles(softwareList);
            //Control de botones de navegación

        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadTableInstalados() {
        if (this.computerDTO == null) {
            throw new IllegalStateException("computerDTO is not initialized.");
        }
        try {
            // Borrar registros previos antes de cargar los nuevos
            deleteInfoTableInstalados();

            // Obtén solo los clientes necesarios para la página actual
            List<SoftwareDTO> softwareList = this.softwareBO.getSoftwareInstalledByComputer(computerDTO.getId());

            //Agrega los registros paginados a la tabla
            this.addInfoTableInstalado(softwareList);
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
        lblName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInstalados = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDisponibles = new javax.swing.JTable();
        lblName1 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblIdComputer = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        lblName.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblName.setText("Aplicaciones Instaladas");

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
        lblName1.setText("Aplicaciones Disponibles");

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

        lblIdComputer.setText("jLabel1");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIdComputer, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblName)
                        .addGap(136, 136, 136))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdComputer)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        SoftwareDTO software = this.getTableSoftware();
        if(software == null){
            JOptionPane.showMessageDialog(this, "Por favor seleccione un software para agregar", "Información", JOptionPane.ERROR_MESSAGE);
            return ;
        }
        ComputerSoftwareDTO computerSoftwareDTO = new ComputerSoftwareDTO();
        computerSoftwareDTO.setComputer(computerDTO.getId());
        computerSoftwareDTO.setSoftware(software.getId());
        try {
            computerSoftwareBO.saveComputerSoftware(computerSoftwareDTO);
            this.loadTableInstalados();
            this.loadTableDisponibles();
        } catch (BusinessException ex) {
            Logger.getLogger(FrmLaboratoryRules.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        SoftwareDTO software = this.getTableSoftwareInstalados();
        if(software == null){
            JOptionPane.showMessageDialog(this, "Por favor seleccione un software para eliminar", "Información", JOptionPane.ERROR_MESSAGE);
            return ;
        }
        try {
            computerSoftwareBO.deleteComputerSoftware(computerDTO.getId(), software.getId());
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
    private javax.swing.JLabel lblIdComputer;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblName1;
    private javax.swing.JTable tblDisponibles;
    private javax.swing.JTable tblInstalados;
    // End of variables declaration//GEN-END:variables
}
