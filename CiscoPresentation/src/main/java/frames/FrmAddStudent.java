/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import dto.StudentDTO;
import enums.EnrollmentStatus;
import interfaces.IDegreeBO;
import interfaces.IStudentBO;
import javax.swing.JOptionPane;

/**
 *
 * @author aleja
 */
public class FrmAddStudent extends javax.swing.JFrame {

    IStudentBO studentBO;
    IDegreeBO degreeBO;
    EnrollmentStatus enrollmentStatus;

    /**
     * Creates new form FrmStudentManager
     */
    public FrmAddStudent(IStudentBO studentBO, IDegreeBO degreeBO) {
        initComponents();
        this.studentBO = studentBO;
        this.enrollmentStatus = EnrollmentStatus.Inscrito;
        this.degreeBO = degreeBO;
        this.loadFrame();
    }

    public void loadFrame() {
        this.setTitle("Agregar Estudiantes ");
        this.setResizable(false);
        this.setSize(598, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.llenarComboClasificaciones();
    }
    

    private void llenarComboClasificaciones() {
        for (EnrollmentStatus clasi : enrollmentStatus.values()) {
            cbStatus.addItem(clasi);
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
        jPanel4 = new javax.swing.JPanel();
        menuButton13 = new utilities.MenuButton();
        lbNewStudent = new javax.swing.JLabel();
        lblSecondLastName = new javax.swing.JLabel();
        javax.swing.JLabel lblLastName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblNameStudent = new javax.swing.JLabel();
        btnContinue = new javax.swing.JButton();
        txtNameStudent = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        txtSecondLastName = new javax.swing.JTextField();
        btnReturn = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        cbStatus = new javax.swing.JComboBox<>();
        lblStatus = new javax.swing.JLabel();
        lblNameStudent1 = new javax.swing.JLabel();
        txtUniqueID = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(208, 216, 232));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel4.add(menuButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 857, -1, -1));

        lbNewStudent.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lbNewStudent.setText("Agregar Estudiante");
        jPanel4.add(lbNewStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 340, -1));

        lblSecondLastName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblSecondLastName.setText("Apelldio Materno:");
        jPanel4.add(lblSecondLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        lblLastName.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblLastName.setText("Apellido Paterno:");
        jPanel4.add(lblLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        lblPassword.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPassword.setText("Contraseña:");
        jPanel4.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, -1, -1));

        lblNameStudent.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNameStudent.setText("ID Unico:");
        jPanel4.add(lblNameStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        btnContinue.setText("Continuar");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });
        jPanel4.add(btnContinue, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, 180, 40));

        txtNameStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameStudentActionPerformed(evt);
            }
        });
        jPanel4.add(txtNameStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 250, 30));
        jPanel4.add(txtLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 200, 250, 30));
        jPanel4.add(txtSecondLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 250, 30));

        btnReturn.setText("Regresar");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });
        jPanel4.add(btnReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 180, 40));
        jPanel4.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 250, 30));

        jPanel4.add(cbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 250, 30));

        lblStatus.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblStatus.setText("Estado:");
        jPanel4.add(lblStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, -1, -1));

        lblNameStudent1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblNameStudent1.setText("Nombre:");
        jPanel4.add(lblNameStudent1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, -1, -1));

        txtUniqueID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUniqueIDActionPerformed(evt);
            }
        });
        jPanel4.add(txtUniqueID, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 250, 30));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 600, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameStudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameStudentActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        try {
            String studentName = txtNameStudent.getText().trim();
            String studentLastName = txtLastName.getText().trim();
            String studentSecondLastName = txtSecondLastName.getText().trim();
            String Password = txtPassword.getText().trim();
            

            if (studentName.isEmpty() || studentLastName.isEmpty() || studentSecondLastName.isEmpty() || Password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Crear el objeto Clientes y establecer los valores
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setUnique_ID(Long.valueOf(txtUniqueID.getText()));
            studentDTO.setNames(studentName);
            studentDTO.setFirstLastname(studentLastName);
            studentDTO.setSecondLastname(studentSecondLastName);
            studentDTO.setPassword(Password);
            studentDTO.setEnrollmentStatus((EnrollmentStatus) cbStatus.getSelectedItem());
            

            studentBO.saveStudent(studentDTO);

            JOptionPane.showMessageDialog(this, "Estudiante guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
            FrmStudentManager frmStudentManager = new FrmStudentManager(studentBO, degreeBO);
            frmStudentManager.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Se ha producido un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

    private void txtUniqueIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUniqueIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUniqueIDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContinue;
    private javax.swing.JButton btnReturn;
    private javax.swing.JComboBox<EnrollmentStatus> cbStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbNewStudent;
    private javax.swing.JLabel lblNameStudent;
    private javax.swing.JLabel lblNameStudent1;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSecondLastName;
    private javax.swing.JLabel lblStatus;
    private utilities.MenuButton menuButton13;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtNameStudent;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSecondLastName;
    private javax.swing.JTextField txtUniqueID;
    // End of variables declaration//GEN-END:variables
}
