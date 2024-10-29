/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package frames;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 *
 * @author carli
 */
public class FrmSessionStarted extends javax.swing.JFrame {

    private int seconds = 0; // Contador de segundos
    private Timer timer; // Timer para el cronómetro
    private TrayIcon trayIcon; // Icono de la bandeja del sistema

    public FrmSessionStarted() {
        initComponents();

        // Listener para minimizar la ventana
        startTime();

        if (SystemTray.isSupported()) {
            sistemTrayConfiguration();
        } else {
            System.out.println("SystemTray no soportado.");
        }
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowIconified(java.awt.event.WindowEvent e) {
                setVisible(false); // Minimizar la ventana en lugar de cerrarla
            }

            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                SystemTray.getSystemTray().remove(trayIcon);
                System.exit(0);
            }
        });

        // Minimizar automáticamente después de mostrar
        SwingUtilities.invokeLater(() -> setState(JFrame.ICONIFIED)); // Minimizar la ventana
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
        lblTime = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(208, 216, 232));

        lblTime.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        lblTime.setText("00:00");

        jButton1.setText("Terminar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTime))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTime)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  private void startTime() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                updateTimeLabel();
            }
        });
        timer.start(); // Comienza a contar
    }

    private void updateTimeLabel() {
        int min = seconds / 60; // Calcula los minutos
        int sec = seconds % 60; // Calcula los segundos
        lblTime.setText(String.format("%02d:%02d", min, sec)); // Actualiza la etiqueta
    }

    private void sistemTrayConfiguration() {
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/itsonLogoBigger.png"));
        trayIcon = new TrayIcon(icon, "Cisco");
        trayIcon.setImageAutoSize(true);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem terminarUsoItem = new JMenuItem("Terminar Uso");

        terminarUsoItem.addActionListener(e -> {
            terminarUsoItem.setEnabled(false);

            // Stop the timer
            if (timer != null) {
                timer.stop();
            }
            deleteIcon();
            popupMenu.setVisible(false); // Hide the pop-up menu

            FinishSesion();
        });

        // Agregar elemento al menú
        popupMenu.add(terminarUsoItem);
        terminarUsoItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                terminarUsoItem.setBackground(Color.BLUE); // Cambiar a azul
                terminarUsoItem.setForeground(Color.BLUE); // Cambiar texto a blanco
            }

            @Override
            public void mouseExited(MouseEvent e) {
                terminarUsoItem.setBackground(Color.WHITE); // Restaurar color original
                terminarUsoItem.setForeground(Color.BLACK); // Restaurar color de texto
            }
        });

        // Acción al hacer clic en el icono del tray
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Hacer visible el JFrame al hacer clic izquierdo
                    setVisible(true);
                    setExtendedState(JFrame.NORMAL); // Restaurar el estado de la ventana
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    // Mostrar el menú contextual al hacer clic derecho
                    Point mouseLocation = MouseInfo.getPointerInfo().getLocation();

                    // Mostrar el menú contextual justo arriba del icono
                    popupMenu.show(e.getComponent(), mouseLocation.x, mouseLocation.y - popupMenu.getHeight());
                }
            }
        });

        // Añadir el icono a la bandeja del sistema
        try {
            SystemTray.getSystemTray().add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void FinishSesion() {
//        System.out.println("Tiempo" + lblTime.getText());
//        FrmBlockedComputer blockedComputer = new FrmBlockedComputer();
//        blockedComputer.setVisible(true);
//        this.dispose();
    }

    private void deleteIcon() {
        // Eliminar el icono de la bandeja del sistema
        if (trayIcon != null) {
            SystemTray systemTray = SystemTray.getSystemTray();
            try {
                systemTray.remove(trayIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmSessionStarted.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmSessionStarted.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmSessionStarted.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSessionStarted.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmSessionStarted().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblTime;
    // End of variables declaration//GEN-END:variables
}
