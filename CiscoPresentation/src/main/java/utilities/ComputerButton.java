/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author carli
 */
public class ComputerButton extends JButton {
  private Icon simpleIcon;
    private Icon selectedIcon;
    private int number = -1;  // Default to -1 if no number is set
    private boolean isHovered = false;  // Variable para saber si el mouse está sobre el botón
    private final Color  selectedColor= new Color(0x87DECD);

    public ComputerButton() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add mouse listener for hover effects
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(selectedIcon); // Cambia al icono seleccionado al poner el mouse encima
                isHovered = true;      // Marca el botón como "hovered"
                repaint();             // Redibuja para mostrar el cambio de color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(simpleIcon);   // Vuelve al icono simple cuando el mouse sale
                isHovered = false;     // Marca el botón como "no hovered"
                repaint();             // Redibuja para mostrar el cambio de color
            }
        });
    }

    public Icon getSimpleIcon() {
        return simpleIcon;
    }

    public void setSimpleIcon(Icon iconoSimple) {
        this.simpleIcon = iconoSimple;
        setIcon(iconoSimple); // Set the initial icon
    }

    public Icon getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(Icon iconoSeleccionado) {
        this.selectedIcon = iconoSeleccionado;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        repaint();  // Repaint to show the updated number
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the icon at the center if it's set
        Icon icon = getIcon();
        if (icon != null) {
            int iconX = (getWidth() - icon.getIconWidth()) / 2;
            int iconY = (getHeight() - icon.getIconHeight()) / 2;
            icon.paintIcon(this, g, iconX, iconY);
        }

        // Draw the number over the icon if it's set
        if (number >= 0) {  // Only draw if the number is set to a non-negative value
            g.setColor(isHovered ? selectedColor : Color.BLACK);  // Verde si está en hover, negro si no
            g.setFont(new Font("Arial", Font.BOLD, 20));

            // Position the number in the middle of the screen area within the icon
            int numberX = (getWidth() - g.getFontMetrics().stringWidth(String.valueOf(number))) / 2;
            int numberY = (getHeight() + g.getFontMetrics().getAscent()) / 2 - 10;  // Adjust to place within screen area
            g.drawString(String.valueOf(number), numberX, numberY);
        }
    }
}