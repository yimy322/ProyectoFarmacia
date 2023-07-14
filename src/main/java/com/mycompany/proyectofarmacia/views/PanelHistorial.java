/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author yimy
 */
public class PanelHistorial extends JPanel{
    
    Box caja1, caja2;
  
    public PanelHistorial(){
        
        setLayout(new FlowLayout());

        Box cajaVertical = Box.createVerticalBox();
        
        caja1 = Box.createHorizontalBox();
        
        caja2 = Box.createHorizontalBox();
        
//        Caja 1
        
        caja1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Ventas del dia"));
        
        crearTabla();
        
        cajaVertical.add(caja1);
        
//        Caja 2

        caja2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Ventas totales"));
        
        crearTabla1();
        
        cajaVertical.add(caja2);
        
//        Button
        
        JButton addExcel = new JButton("Exportar tabla a excel", new ImageIcon("src/main/java/resources/excel.png"));
        
        add(cajaVertical);
        
        add(addExcel);
        
    }
    
    public void crearTabla() {
        
        String data[][] = {{"101", "Amit", "670000"},
        {"102", "Jai", "780000"},
        {"101", "Sachin", "700000"}};
        String column[] = {"ID", "NAME", "SALARY"};
        JTable jt = new JTable(data, column);
        jt.setPreferredScrollableViewportSize(new Dimension(1175, 100));
        JScrollPane sp=new JScrollPane(jt);   
        caja1.add(sp);
        caja1.add(Box.createVerticalStrut(10));

    }
    
    public void crearTabla1() {
        
        String data[][] = {{"101", "Amit", "670000"},
        {"102", "Jai", "780000"},
        {"101", "Sachin", "700000"}};
        String column[] = {"ID", "NAME", "SALARY"};
        JTable jt = new JTable(data, column);
        jt.setPreferredScrollableViewportSize(new Dimension(1175, 280));
        JScrollPane sp=new JScrollPane(jt);   
        caja2.add(sp);
        caja2.add(Box.createVerticalStrut(10));

    }
    
}
