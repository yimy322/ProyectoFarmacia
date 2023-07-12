/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author yimy
 */
public class PanelPrincipal extends JPanel{

    private Image imagen;

    public void paintComponent(Graphics g) {

        File image = new File("src/main/java/resources/fondo.jpg");

        try {
            imagen = ImageIO.read(image);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Imagen no encontrada");
        }

        Graphics2D g2 = (Graphics2D) g;

        Image conversion = imagen.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);

        g2.drawImage(conversion, 0, 0, null);

    }

}
