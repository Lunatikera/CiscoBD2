/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import businessObjects.SoftwareBO;
import businessObjects.StudentComputerBO;
import connection.ConnectionDB;
import connection.IConnectionBD;
import dao.ComputerDAO;
import dao.SoftwareDAO;
import dao.StudentComputerDAO;
import dao.StudentDAO;
import dto.ComputerDTO;
import dto.LaboratoryDTO;
import dto.StudentDTO;
import dto.StudentDegreeDTO;
import enums.ComputerStatus;
import enums.ComputerTypes;
import exception.BusinessException;
import interfaces.IComputerBO;
import interfaces.IComputerDAO;
import interfaces.ISoftwareBO;
import interfaces.ISoftwareDAO;
import interfaces.IStudentComputerBO;
import interfaces.IStudentComputerDAO;
import interfaces.IStudentDAO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import utilities.ComputerButton;

/**
 *
 * @author carli
 */
public class FrmChooseComputer extends javax.swing.JFrame {

    private int page = 1;
    private final int LIMITE = 18;
    IComputerBO computerBO;
    StudentDegreeDTO studentDegreeDTO;
    StudentDTO studentDTO;
    LaboratoryDTO laboratoryDTO;
    ComputerDTO computerDTO;
    private ComputerButton[] botones;
    private List<ComputerDTO> loadedPCs;

    public FrmChooseComputer(IComputerBO computerBO, StudentDTO studentDTO, StudentDegreeDTO studentDegreeDTO, LaboratoryDTO laboratoryDTO, ComputerDTO computerDTO) {
        initComponents();
        this.computerBO = computerBO;
        this.studentDegreeDTO = studentDegreeDTO;
        this.studentDTO = studentDTO;
        this.laboratoryDTO = laboratoryDTO;
        this.computerDTO = computerDTO;
        this.loadedPCs = new ArrayList<>();
        this.botones = new ComputerButton[]{computer, computer1, computer2, computer3, computer4,
            computer5, computer6, computer7, computer8, computer9, computer10, computer11, computer12, computer13, computer14, computer15, computer16, computer17};
        this.setLocationRelativeTo(null);
        loadFrame();

    }

    private void loadFrame() {
        this.setTitle("Laboratorio " + laboratoryDTO.getLabName());
        this.setLocationRelativeTo(null);
        this.loadComputers();
        this.pageStatus();

    }

    public void loadComputers() {
        try {
            List<ComputerDTO> computerList = this.computerBO.computerListByAcademyPaginated(page, LIMITE, this.computerDTO.getLabId());
            loadedPCs.clear();
            loadedPCs.addAll(computerList);
            this.fillFields(computerList);
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Informacion", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void fillFields(List<ComputerDTO> computerList) {
        for (int i = 0; i < computerList.size(); i++) {

            botones[i].setEnabled(true);
            botones[i].setNumber(computerList.get(i).getMachineNumber());
            if (computerList.get(i).getComputerType() == ComputerTypes.Administrativo || computerList.get(i).getStatus()==ComputerStatus.No_Disponible) {
                botones[i].setVisible(false);

            }
        }
        // Limpiar botones y etiquetas restantes
        for (int i = computerList.size(); i < LIMITE; i++) {
            botones[i].setVisible(false);
            botones[i].setEnabled(false);

        }

    }

    private void pageStatus() {
        String numPagina = String.valueOf(page);
        if (numPagina.length() == 1) {
            numPagina = "0" + numPagina;
        }

        lblPage.setText("Pagina " + numPagina);
        leftBtnStatus();
        rightBtnStatus();
    }

    private void leftBtnStatus() {
        if (this.page > 1) {
            btnLeft.setEnabled(true);
            return;
        }
        btnLeft.setEnabled(false);
    }

    private void rightBtnStatus() {

        try {
            btnRight.setEnabled(true);
            if (this.computerBO.computerListByAcademyPaginated(page + 1, LIMITE, this.computerDTO.getLabId()) == null
                    || this.computerBO.computerListByAcademyPaginated(page + 1, LIMITE, this.computerDTO.getLabId()).isEmpty()) {
                btnRight.setEnabled(false);
            }
        } catch (BusinessException ex) {
            System.out.println(ex);
        }
    }

    private void computerDetails(int x) {
        ComputerDTO computer = loadedPCs.get(x);
        System.out.println(computer);
        this.openComputerDetails(computer);

    }

    private void openComputerDetails(ComputerDTO computer) {
        IConnectionBD connectionBD = new ConnectionDB();
        ISoftwareDAO softwareDAO = new SoftwareDAO(connectionBD);
        ISoftwareBO softwareBO = new SoftwareBO(softwareDAO);
        IStudentDAO studentDAO = new StudentDAO(connectionBD);
        IComputerDAO computerDAO = new ComputerDAO(connectionBD);
        IStudentComputerDAO studentComputerDAO = new StudentComputerDAO(connectionBD);
        IStudentComputerBO studentComputerBO = new StudentComputerBO(studentComputerDAO, studentDAO, computerDAO);
        FrmComputerDetails detalles = new FrmComputerDetails(studentComputerBO, softwareBO, computerBO, computer, studentDTO, laboratoryDTO, studentDegreeDTO);
        detalles.setVisible(true);
        this.dispose();
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
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        computer = new utilities.ComputerButton();
        computer1 = new utilities.ComputerButton();
        computer2 = new utilities.ComputerButton();
        computer3 = new utilities.ComputerButton();
        computer4 = new utilities.ComputerButton();
        computer5 = new utilities.ComputerButton();
        jPanel3 = new javax.swing.JPanel();
        btnLeft = new utilities.MenuButton();
        computer6 = new utilities.ComputerButton();
        computer7 = new utilities.ComputerButton();
        computer8 = new utilities.ComputerButton();
        computer9 = new utilities.ComputerButton();
        computer10 = new utilities.ComputerButton();
        computer11 = new utilities.ComputerButton();
        btnRight = new utilities.MenuButton();
        jPanel5 = new javax.swing.JPanel();
        computer12 = new utilities.ComputerButton();
        computer13 = new utilities.ComputerButton();
        computer14 = new utilities.ComputerButton();
        computer15 = new utilities.ComputerButton();
        computer16 = new utilities.ComputerButton();
        computer17 = new utilities.ComputerButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblPage = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        lblStudent2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(208, 216, 232));
        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(208, 216, 232));

        computer.setNumber(5);
        computer.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computerActionPerformed(evt);
            }
        });
        jPanel2.add(computer);

        computer1.setNumber(5);
        computer1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer1.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer1ActionPerformed(evt);
            }
        });
        jPanel2.add(computer1);

        computer2.setNumber(5);
        computer2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer2.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer2ActionPerformed(evt);
            }
        });
        jPanel2.add(computer2);

        computer3.setNumber(5);
        computer3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer3.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer3ActionPerformed(evt);
            }
        });
        jPanel2.add(computer3);

        computer4.setNumber(5);
        computer4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer4.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer4ActionPerformed(evt);
            }
        });
        jPanel2.add(computer4);

        computer5.setNumber(5);
        computer5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer5.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer5ActionPerformed(evt);
            }
        });
        jPanel2.add(computer5);

        jPanel3.setBackground(new java.awt.Color(208, 216, 232));

        btnLeft.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/leftSelected.png"))); // NOI18N
        btnLeft.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left.png"))); // NOI18N
        jPanel3.add(btnLeft);

        computer6.setNumber(5);
        computer6.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer6.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer6ActionPerformed(evt);
            }
        });
        jPanel3.add(computer6);

        computer7.setNumber(5);
        computer7.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer7.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer7ActionPerformed(evt);
            }
        });
        jPanel3.add(computer7);

        computer8.setNumber(5);
        computer8.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer8.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer8ActionPerformed(evt);
            }
        });
        jPanel3.add(computer8);

        computer9.setNumber(5);
        computer9.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer9.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer9ActionPerformed(evt);
            }
        });
        jPanel3.add(computer9);

        computer10.setNumber(5);
        computer10.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer10.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer10ActionPerformed(evt);
            }
        });
        jPanel3.add(computer10);

        computer11.setNumber(5);
        computer11.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer11.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer11ActionPerformed(evt);
            }
        });
        jPanel3.add(computer11);

        btnRight.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rightSelected.png"))); // NOI18N
        btnRight.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right.png"))); // NOI18N
        jPanel3.add(btnRight);

        jPanel5.setBackground(new java.awt.Color(208, 216, 232));

        computer12.setNumber(5);
        computer12.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer12.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer12ActionPerformed(evt);
            }
        });
        jPanel5.add(computer12);

        computer13.setNumber(5);
        computer13.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer13.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer13ActionPerformed(evt);
            }
        });
        jPanel5.add(computer13);

        computer14.setNumber(5);
        computer14.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer14.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer14ActionPerformed(evt);
            }
        });
        jPanel5.add(computer14);

        computer15.setNumber(5);
        computer15.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer15.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer15ActionPerformed(evt);
            }
        });
        jPanel5.add(computer15);

        computer16.setNumber(5);
        computer16.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer16.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer16ActionPerformed(evt);
            }
        });
        jPanel5.add(computer16);

        computer17.setNumber(5);
        computer17.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pcSelected.png"))); // NOI18N
        computer17.setSimpleIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pc.png"))); // NOI18N
        computer17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                computer17ActionPerformed(evt);
            }
        });
        jPanel5.add(computer17);

        jPanel6.setBackground(new java.awt.Color(182, 191, 210));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setToolTipText("");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 21, -1, -1));

        jLabel2.setText("        ");
        jPanel6.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(504, 13, -1, -1));

        jLabel8.setText("        ");
        jLabel8.setToolTipText("");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(758, 13, -1, -1));

        jPanel9.setBackground(new java.awt.Color(182, 191, 210));
        jPanel9.setPreferredSize(new java.awt.Dimension(1280, 42));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 42, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(182, 191, 210));

        lblPage.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblPage.setText("Pagina  01");
        jPanel7.add(lblPage);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel12.setBackground(new java.awt.Color(182, 191, 210));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(182, 191, 210));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel22.setBackground(new java.awt.Color(182, 191, 210));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        jPanel15.setBackground(new java.awt.Color(182, 191, 210));

        lblStudent2.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        lblStudent2.setText("Selecciona Una Computadora");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStudent2, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStudent2)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Itson Centro, Laboratorio Nainari");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(456, 456, 456)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void computerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computerActionPerformed
        this.computerDetails(0);
    }//GEN-LAST:event_computerActionPerformed

    private void computer1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer1ActionPerformed
        this.computerDetails(1);
    }//GEN-LAST:event_computer1ActionPerformed

    private void computer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer2ActionPerformed
        this.computerDetails(2);
    }//GEN-LAST:event_computer2ActionPerformed

    private void computer3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer3ActionPerformed
        this.computerDetails(3);
    }//GEN-LAST:event_computer3ActionPerformed

    private void computer4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer4ActionPerformed
        this.computerDetails(4);
    }//GEN-LAST:event_computer4ActionPerformed

    private void computer5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer5ActionPerformed
        this.computerDetails(5);
    }//GEN-LAST:event_computer5ActionPerformed

    private void computer6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer6ActionPerformed
        this.computerDetails(6);
    }//GEN-LAST:event_computer6ActionPerformed

    private void computer7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer7ActionPerformed
        this.computerDetails(7);
    }//GEN-LAST:event_computer7ActionPerformed

    private void computer8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer8ActionPerformed
        this.computerDetails(8);
    }//GEN-LAST:event_computer8ActionPerformed

    private void computer9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer9ActionPerformed
        this.computerDetails(9);
    }//GEN-LAST:event_computer9ActionPerformed

    private void computer10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer10ActionPerformed
        this.computerDetails(10);
    }//GEN-LAST:event_computer10ActionPerformed

    private void computer11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer11ActionPerformed
        this.computerDetails(11);
    }//GEN-LAST:event_computer11ActionPerformed

    private void computer12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer12ActionPerformed
        this.computerDetails(12);
    }//GEN-LAST:event_computer12ActionPerformed

    private void computer13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer13ActionPerformed
        this.computerDetails(13);
    }//GEN-LAST:event_computer13ActionPerformed

    private void computer14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer14ActionPerformed
        this.computerDetails(14);
    }//GEN-LAST:event_computer14ActionPerformed

    private void computer15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer15ActionPerformed
        this.computerDetails(15);
    }//GEN-LAST:event_computer15ActionPerformed

    private void computer16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer16ActionPerformed
        this.computerDetails(16);
    }//GEN-LAST:event_computer16ActionPerformed

    private void computer17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computer17ActionPerformed
        this.computerDetails(17);
    }//GEN-LAST:event_computer17ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utilities.MenuButton btnLeft;
    private utilities.MenuButton btnRight;
    private utilities.ComputerButton computer;
    private utilities.ComputerButton computer1;
    private utilities.ComputerButton computer10;
    private utilities.ComputerButton computer11;
    private utilities.ComputerButton computer12;
    private utilities.ComputerButton computer13;
    private utilities.ComputerButton computer14;
    private utilities.ComputerButton computer15;
    private utilities.ComputerButton computer16;
    private utilities.ComputerButton computer17;
    private utilities.ComputerButton computer2;
    private utilities.ComputerButton computer3;
    private utilities.ComputerButton computer4;
    private utilities.ComputerButton computer5;
    private utilities.ComputerButton computer6;
    private utilities.ComputerButton computer7;
    private utilities.ComputerButton computer8;
    private utilities.ComputerButton computer9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblPage;
    private javax.swing.JLabel lblStudent2;
    // End of variables declaration//GEN-END:variables
}
