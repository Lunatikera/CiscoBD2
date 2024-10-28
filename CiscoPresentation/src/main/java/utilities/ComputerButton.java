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
    private int number = -1;
    private boolean isHovered = false;
    private final Color selectedColor = new Color(0x87DECD);

    public ComputerButton(Icon simpleIcon, Icon selectedIcon) {
        this.simpleIcon = simpleIcon;
        this.selectedIcon = selectedIcon;

        setIcon(simpleIcon);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(selectedIcon);
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(simpleIcon);
                isHovered = false;
                repaint();
            }
        });
    }

    public ComputerButton() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(selectedIcon);
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(simpleIcon);
                isHovered = false;
                repaint();
            }
        });
    }

    public Icon getSimpleIcon() {
        return simpleIcon;
    }

    public void setSimpleIcon(Icon iconoSimple) {
        this.simpleIcon = iconoSimple;
        setIcon(iconoSimple);
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
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Icon icon = getIcon();
        if (icon != null) {
            int iconX = (getWidth() - icon.getIconWidth()) / 2;
            int iconY = (getHeight() - icon.getIconHeight()) / 2;
            icon.paintIcon(this, g, iconX, iconY);
        }

        if (number >= 0) {
            g.setColor(isHovered ? selectedColor : Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 20));

            int numberX = (getWidth() - g.getFontMetrics().stringWidth(String.valueOf(number))) / 2;
            int numberY = (getHeight() + g.getFontMetrics().getAscent()) / 2 - 10;
            g.drawString(String.valueOf(number), numberX, numberY);
        }
    }
}
