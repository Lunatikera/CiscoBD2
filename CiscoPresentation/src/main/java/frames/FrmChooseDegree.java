/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import dto.ComputerDTO;
import dto.LaboratoryDTO;
import dto.StudentDTO;
import dto.StudentDegreeDTO;
import exception.BusinessException;
import interfaces.IComputerBO;
import interfaces.IDegreeBO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carli
 */
public class FrmChooseDegree extends javax.swing.JFrame {
    IComputerBO computerBO;
    IDegreeBO degreeBO;
    StudentDTO studentDTO;
    ComputerDTO computerDTO;
    LaboratoryDTO laboratoryDTO;
    List< StudentDegreeDTO> degreeList;

    /**
     * Creates new form FrmStudentStart
     */
    public FrmChooseDegree(IComputerBO computerBO, IDegreeBO degreeBO, StudentDTO studentDTO, ComputerDTO computerDTO, LaboratoryDTO laboratoryDTO) {
        initComponents();
        this.computerBO=computerBO;
        this.degreeBO = degreeBO;
        this.studentDTO = studentDTO;
        this.computerDTO = computerDTO;
        this.laboratoryDTO = laboratoryDTO;
        this.setSize(1280, 720);
        this.setLocationRelativeTo(null);
        fillDegrees();
        fillFields();

        this.cbDegree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillFields();
            }
        });
    }

    private void fillFields() {
        StudentDegreeDTO studentDegreeDTO = degreeList.get(cbDegree.getSelectedIndex());
        lblTotalTIme.setText(" Tiempo Total " + studentDegreeDTO.getTimeLimit() + " minutos");
        lblRemainingTime.setText(" Tiempo Restante " + studentDegreeDTO.getRemainingTime() + " minutos");

    }

    private void fillDegrees() {
        try {
            degreeList = degreeBO.getDegreesByStudent(studentDTO.getUnique_ID());

            for (StudentDegreeDTO degree : degreeList) {
                cbDegree.addItem(degree);
            }
            cbDegree.setSelectedIndex(0);
        } catch (BusinessException ex) {
            Logger.getLogger(FrmLaboratoryManager.class.getName()).log(Level.SEVERE, null, ex);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblStudent = new javax.swing.JLabel();
        lblStudent1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cbDegree = new javax.swing.JComboBox<>();
        lblTotalTIme = new javax.swing.JLabel();
        lblRemainingTime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(91, 134, 229));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(208, 216, 232));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/itsonLogoBigger.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel2)
                .addGap(94, 94, 94)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(264, 264, 264)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel2)))
                .addContainerGap(197, Short.MAX_VALUE))
        );

        lblStudent.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblStudent.setText("Bienvenido");

        lblStudent1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblStudent1.setText("Carrera");

        jButton1.setText("Continuar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblTotalTIme.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTotalTIme.setText("Tiempo total");

        lblRemainingTime.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblRemainingTime.setText("Tiempo disponible");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(lblStudent))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbDegree, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(lblStudent1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRemainingTime, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTotalTIme, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(152, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lblStudent)
                .addGap(41, 41, 41)
                .addComponent(lblStudent1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDegree, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblTotalTIme)
                .addGap(18, 18, 18)
                .addComponent(lblRemainingTime)
                .addGap(38, 38, 38)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 1050, 560));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        StudentDegreeDTO studentDegreeDTO = (StudentDegreeDTO) cbDegree.getSelectedItem();
        FrmChooseComputer chooseComputer = new FrmChooseComputer(computerBO, studentDTO, studentDegreeDTO, laboratoryDTO, computerDTO);
        chooseComputer.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<StudentDegreeDTO> cbDegree;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblRemainingTime;
    private javax.swing.JLabel lblStudent;
    private javax.swing.JLabel lblStudent1;
    private javax.swing.JLabel lblTotalTIme;
    // End of variables declaration//GEN-END:variables
}