/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import java.awt.BorderLayout;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author yimy
 */
public class PanelInventario extends JPanel {

    Box caja1, caja2, cajaTabla, cajaEditar, caja3;

    //    Labels del producto
    JLabel codProducto, nomProducto, labProducto, stock, descripcion, cantidad, precio;
    
    //    TextField del producto
    JTextField txtCodProducto, txtNomProducto, txtLabProducto, txtStock, txtDescripcion, txtCantidad, txtPrecio;
    
    // Botones
    
    JButton btnRegistrar, btnLimpiar, btnEditar, btnEliminar;
    
    //    Labels de editar
    JLabel id, codEditar, nomEditar, labEditar, Edistock, Edidescripcion, Edicantidad, Ediprecio;
    
    //    TextField de editar
    JTextField txtId, txtCodEditar, txtNomEditar, txtLabEditar, txtEdiStock, txtEdiDescripcion, txtEdiCantidad, txtEdiPrecio;

    public PanelInventario() {

        setLayout(new FlowLayout());

        Box cajaVertical = Box.createVerticalBox();
        
        Box cajaVertical1 = Box.createVerticalBox();
        
        Box cajaVertical2 = Box.createVerticalBox();

        caja1 = Box.createHorizontalBox();
        
        caja2 = Box.createHorizontalBox();

        caja3 = Box.createHorizontalBox();

        cajaVertical.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Agregar Producto"));

        crearcajasProducto("Nombre", nomProducto, txtNomProducto);

        crearcajasProducto("Codigo", codProducto, txtCodProducto);

        crearcajasProducto("Laboratorio", labProducto, txtLabProducto);

        crearcajasProducto("Stock", stock, txtStock);

        crearcajasProducto("Precio", precio, txtPrecio);
        
        crearcajasProducto("Cantidad", cantidad, txtCantidad);
        
        cajaVertical.add(caja1);
        
        cajaVertical.add(Box.createVerticalStrut(10));

        crearcajasProducto1("Descripcion", descripcion, txtDescripcion);
        
        caja2.add(Box.createHorizontalStrut(30));

        btnRegistrar = new JButton("Registrar", new ImageIcon("src/main/java/resources/agregar.png"));
        
        btnLimpiar = new JButton("Limpiar", new ImageIcon("src/main/java/resources/eliminar.png"));
        
        cajaTabla = Box.createHorizontalBox();
        
        caja2.add(btnRegistrar);
        
        caja2.add(btnLimpiar);
        
        cajaVertical.add(caja2);

        crearTabla();
        
        cajaVertical1.add(cajaTabla);
        
        JButton addExcel = new JButton("Exportar tabla a excel", new ImageIcon("src/main/java/resources/excel.png"));
        
//        Caja editar

        cajaEditar = Box.createHorizontalBox();
        
        cajaVertical2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Editar Producto"));

        
        crearcajasEditar("Id", id, txtId);
        
        crearcajasEditar("Nombre", nomEditar, txtNomEditar);

        crearcajasEditar("Codigo", codEditar, txtCodEditar);

        crearcajasEditar("Laboratorio", labEditar, txtLabEditar);

        crearcajasEditar("Stock", Edistock, txtEdiStock);

        crearcajasEditar("Precio", Ediprecio, txtEdiPrecio);
        
        crearcajasEditar("Cantidad", Edicantidad, txtEdiCantidad);
        
        cajaVertical2.add(cajaEditar);
        
        cajaVertical2.add(Box.createVerticalStrut(10));
        
        Edidescripcion = new JLabel("Descripcion");

        txtEdiDescripcion = new JTextField(9);

        caja3.add(Edidescripcion);

        caja3.add(Box.createHorizontalStrut(5));

        caja3.add(txtEdiDescripcion);

        caja3.add(Box.createHorizontalStrut(10));

        btnEditar = new JButton("Editar", new ImageIcon("src/main/java/resources/salvar.png"));
        
        btnEliminar = new JButton("Borrar", new ImageIcon("src/main/java/resources/borrar.png"));
        
        caja3.add(btnEditar);
        
        caja3.add(btnEliminar);
        
        cajaVertical2.add(caja3);

        add(cajaVertical);
        
        add(cajaVertical1);
        
        add(addExcel);
        
        add(cajaVertical2);

    }

    public void crearcajasProducto(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);

//        En caso sea la caja de descripcion la hacemos mas grande
        if (lbl1.equals("Descripcion")) {

            txt = new JTextField(15);

        } else {

            txt = new JTextField(11);

        }

        caja1.add(lbl);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txt);

        caja1.add(Box.createHorizontalStrut(10));

    }
    
    public void crearcajasProducto1(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);

        txt = new JTextField(8);

        caja2.add(lbl);

        caja2.add(Box.createHorizontalStrut(5));

        caja2.add(txt);

        caja2.add(Box.createHorizontalStrut(10));

    }
    
     public void crearTabla() {
        
        String data[][] = {{"101", "Amit", "670000"},
        {"102", "Jai", "780000"},
        {"101", "Sachin", "700000"}};
        String column[] = {"ID", "NAME", "SALARY"};
        JTable jt = new JTable(data, column);
        jt.setPreferredScrollableViewportSize(new Dimension(1175, 282));
        JScrollPane sp=new JScrollPane(jt);   
        cajaTabla.add(sp);
        cajaTabla.add(Box.createVerticalStrut(10));

    }
     
     public void crearcajasEditar(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);

        txt = new JTextField(9);

        cajaEditar.add(lbl);

        cajaEditar.add(Box.createHorizontalStrut(5));

        cajaEditar.add(txt);

        cajaEditar.add(Box.createHorizontalStrut(10));

    }

}
