/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;


import dto.ComputerDTO;
import dto.DegreeDTO;
import dto.LaboratoryDTO;
import entities.LaboratoryEntity;
import enums.ComputerStatus;
import enums.ComputerTypes;
import exception.BusinessException;
import interfaces.IComputerBO;
import interfaces.ILaboratoryBO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mappers.LaboratoryMapper;

/**
 *
 * @author carli
 */
public class FrmEditComputer extends javax.swing.JFrame {
    
    private IComputerBO computerBO;
    private ILaboratoryBO laboratoryBO;
    private LaboratoryDTO laboratoryDTO;
    private List<LaboratoryDTO> laboratoryList;
    private ComputerDTO computerDTO;
    private FrmComputerManager computerManager;
    /**
     * Creates new form FrmStudentManager
     */
    public FrmEditComputer(IComputerBO computerBO,ILaboratoryBO laboratoryBO,ComputerDTO computerID,FrmComputerManager computerManager) throws BusinessException {
        initComponents();
        this.computerBO = computerBO;
        this.laboratoryBO = laboratoryBO;
        this.computerDTO = computerID;
        this.computerManager = computerManager;
        loadInitialComponents();
    }

    public void loadInitialComponents() throws BusinessException {
        this.setTitle("Administracion de Computadoras");
//        this.laboratoryDTO = cbLaboratory.getItemAt(0);
        this.setResizable(false);
        this.setSize(992, 720);
        this.setLocationRelativeTo(null);
        this.loadTextOnFields();
        
    }
    
    private void fillLaboratoryComboBox() {
//        try {
//            laboratoryList = laboratoryBO.findLaboratoryByID(lab);
//
//            for (LaboratoryDTO laboratory : laboratoryList) {
//                cbLaboratory.addItem(laboratory);
//            }
//        } catch (BusinessException ex) {
//            Logger.getLogger(FrmLaboratoryManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    private void loadTextOnFields() throws BusinessException{
            
            txtIP.setText(computerDTO.getIpAdress());
            txtMachineNumber.setText(String.valueOf(computerDTO.getMachineNumber()));
            if (computerDTO.getStatus()== ComputerStatus.Disponible){
                cbStatus.setSelectedItem(ComputerStatus.Disponible);
            }else cbStatus.setSelectedItem(ComputerStatus.No_Disponible);
            
            if (computerDTO.getComputerType() == ComputerTypes.Estudiante) {
                cbType.setSelectedItem(ComputerTypes.Estudiante);
            }else cbType.setSelectedItem(ComputerTypes.Administrativo);
            txtIP.setEditable(true);
            txtMachineNumber.setEditable(true);
            
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
        jPanel4 = new javax.swing.JPanel();
        menuButton13 = new utilities.MenuButton();
        jPanel5 = new javax.swing.JPanel();
        lblStudent = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtIP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMachineNumber = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnAccept = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();
        cbStatus = new javax.swing.JComboBox<>();
        cbType = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ItsonLogo.png"))); // NOI18N
        panelMenu2.add(jLabel2);

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lineaBlanca.png"))); // NOI18N
        panelMenu2.add(jLabel18);

        jPanel4.setBackground(new java.awt.Color(208, 216, 232));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(menuButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 857, -1, -1));

        jPanel5.setBackground(new java.awt.Color(208, 216, 232));

        lblStudent.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblStudent.setText("Editar Computadora");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("IP");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStudent)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jPanel2.setBackground(new java.awt.Color(208, 216, 232));
        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 70, 260));

        txtIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIPActionPerformed(evt);
            }
        });
        jPanel4.add(txtIP, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 440, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setText("Machine Number");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        txtMachineNumber.setEnabled(false);
        txtMachineNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMachineNumberActionPerformed(evt);
            }
        });
        jPanel4.add(txtMachineNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 440, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel4.setText("Status");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setText("Type");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, -1, -1));

        btnAccept.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnAccept.setText("Aceptar");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });
        jPanel4.add(btnAccept, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 550, 210, 60));

        btnCancel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnCancel1.setText("Cancelar");
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, 210, 60));

        cbStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Disponible", "No_Disponible" }));
        jPanel4.add(cbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        cbType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estudiante", "Administrativo" }));
        jPanel4.add(cbType, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, -1, -1));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(panelMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 743, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(panelMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 992, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIPActionPerformed

    private void txtMachineNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMachineNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMachineNumberActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        try {
            computerDTO.setIpAdress(txtIP.getText());
            if (cbStatus.getSelectedItem() == "Disponible") {
                computerDTO.setStatus(ComputerStatus.Disponible);
        }else{
            computerDTO.setStatus(ComputerStatus.No_Disponible);
            }
            
            if (cbType.getSelectedItem()== "Estudiante") {
            computerDTO.setComputerType(ComputerTypes.Estudiante);
        }else{
            computerDTO.setComputerType(ComputerTypes.Administrativo);
            }
            
            computerBO.updateComputer(computerDTO);
        } catch (BusinessException ex) {
            Logger.getLogger(FrmEditComputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.computerManager.loadInitialComponents();
        this.dispose();
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnCancel1ActionPerformed
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JComboBox<String> cbStatus;
    private javax.swing.JComboBox<String> cbType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblStudent;
    private utilities.MenuButton menuButton13;
    private panels.PanelMenu panelMenu2;
    private javax.swing.JTextField txtIP;
    private javax.swing.JTextField txtMachineNumber;
    // End of variables declaration//GEN-END:variables
}
