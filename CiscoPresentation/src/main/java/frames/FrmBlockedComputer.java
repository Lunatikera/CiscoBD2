/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import businessObjects.ComputerBO;
import businessObjects.StudentBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.ComputerDAO;
import dao.LaboratoryDAO;
import dao.StudentDAO;
import dto.ComputerDTO;
import dto.StudentDTO;
import enums.ComputerStatus;
import exception.BusinessException;
import interfaces.IComputerBO;
import interfaces.IComputerDAO;
import interfaces.ILaboratoryDAO;
import interfaces.IStudentBO;
import interfaces.IStudentDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import utilities.IpGetter;

/**
 *
 * @author carli
 */
public class FrmBlockedComputer extends javax.swing.JFrame {

    private IStudentBO studentBO;
    private IComputerBO computerBO;
    private Timer methodTimer;
    private ComputerDTO computerDTO;

    public FrmBlockedComputer(IStudentBO studentBO, IComputerBO computerBO, ComputerDTO computerDTO) {
        initComponents();
        this.studentBO = studentBO;
        this.computerBO = computerBO;
        this.computerDTO = computerDTO;
        lblpc.setText(computerDTO.getMachineNumber().toString());
        startMethodTimer();
    }

    private void startMethodTimer() {
        methodTimer = new Timer(5000, new ActionListener() { // 1000 ms = 1 second
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyComputerStatus(); // Call your method here
            }
        });
        methodTimer.start(); // Start the timer
    }

    private void verifyComputerStatus() {
        try {
             IConnectionBD connectionBD=new ConnectionDB();
                IStudentDAO studentDAO=new StudentDAO(connectionBD);
                IComputerDAO computerDAO= new ComputerDAO(connectionBD);
                ILaboratoryDAO laboratoryDAO= new LaboratoryDAO(connectionBD);
                IComputerBO computerBO=new ComputerBO(computerDAO, laboratoryDAO);
                IStudentBO newstudentBO=new StudentBO(studentDAO);
                
            computerDTO = computerBO.findByIPComputer(computerDTO.getIpAdress());
            System.out.println("aaaaaa");
            if (computerDTO.getStatus() == ComputerStatus.No_Disponible) {
                if (methodTimer != null) {
                    methodTimer.stop(); // Stop the method timer
                }
               
                StudentDTO student = newstudentBO.getStudentByComputerSession(computerDTO.getId());
                FrmUnlockComputer unlockComputer = new FrmUnlockComputer(newstudentBO, student, computerDTO);
                unlockComputer.setVisible(true);
                this.dispose();

            }

        } catch (BusinessException ex) {
            Logger.getLogger(FrmBlockedComputer.class.getName()).log(Level.SEVERE, null, ex);
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
        lblpc = new javax.swing.JLabel();
        lblStudent2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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
                .addGap(67, 67, 67)
                .addComponent(jLabel2)
                .addGap(108, 108, 108)
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
                        .addGap(214, 214, 214)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblStudent.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N

        lblpc.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblpc.setText("01");

        lblStudent2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblStudent2.setText("Maquina Bloqueada");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcBig.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblStudent2)
                            .addComponent(lblStudent))
                        .addGap(126, 126, 126))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(137, 137, 137))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(lblpc)
                        .addGap(304, 304, 304))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblStudent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(lblStudent2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblpc)
                .addGap(181, 181, 181))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblStudent;
    private javax.swing.JLabel lblStudent2;
    private javax.swing.JLabel lblpc;
    // End of variables declaration//GEN-END:variables
}
