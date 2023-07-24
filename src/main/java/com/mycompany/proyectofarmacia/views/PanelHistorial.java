/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yimy
 */
public class PanelHistorial extends JPanel{
    
    Box caja1, caja2;
    
    //Creacion de la tabla
    public JTable jt, jt1;

    public DefaultTableModel model, model1;

  
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
              
        jt = new JTable();

        model = new DefaultTableModel();

        model.addColumn("IdVenta");
        model.addColumn("IdFactura");
        model.addColumn("IdFarmaceutico");
        model.addColumn("IdPago");
        model.addColumn("Fecha");
        model.addColumn("moneda");
        model.addColumn("Cliente");
        model.addColumn("Total");

        jt.setModel(model);

        jt.setPreferredScrollableViewportSize(new Dimension(1175, 100));
        JScrollPane sp = new JScrollPane(jt);

        caja1.add(sp);
        caja1.add(Box.createVerticalStrut(10));

    }
    
    public void crearTabla1() {
        
        jt1 = new JTable();

        model1 = new DefaultTableModel();

        model1.addColumn("IdVenta");
        model1.addColumn("IdFactura");
        model1.addColumn("IdFarmaceutico");
        model1.addColumn("IdPago");
        model1.addColumn("Fecha");
        model1.addColumn("moneda");
        model1.addColumn("Cliente");
        model1.addColumn("Total");

        jt1.setModel(model1);

        jt1.setPreferredScrollableViewportSize(new Dimension(1175, 280));
        JScrollPane sp = new JScrollPane(jt1);

        caja2.add(sp);
        caja2.add(Box.createVerticalStrut(10));

    }
    
}
