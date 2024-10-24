/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package panels;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author carli
 */
public class PanelMenu extends JPanel{
     private Image image;

    public PanelMenu() {
        // Load the image from the specified path
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/menu.png"));
        image = icon.getImage();
        this.setSize(250, 1080);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image, resizing it to fit the panel
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
    
    
}
