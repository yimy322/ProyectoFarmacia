/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author yimy
 */
public class PanelVentas extends JPanel {

    JPanel centro, izquierda;

    JLabel usuario, fecha, pago, moneda, cliente, comprobante, ncomprobante;

//    Labels del producto
    JLabel codProducto, nomProducto, labProducto, stock, descripcion, cantidad;

//    ------
    JTextField txtusuario, txtfecha, txtpago, txtmoneda, txtcliente, txtcomprobante, ntxtcomprobante;

//    TextField del producto
    JTextField txtCodProducto, txtNomProducto, txtLabProducto, txtStock, txtDescripcion, txtCantidad;

//    ------    
//    Buttons del producto
    JButton busProducto, agregar, eliminar;

//    ------  
    JButton realizarVenta, imprimir;

    Box cajavertical = Box.createVerticalBox();

    Box caja1 = Box.createHorizontalBox();

    Box caja2 = Box.createHorizontalBox();

    Box cajaProductos = Box.createHorizontalBox();
    
    Box cajaTabla = Box.createHorizontalBox();
    
    Box caja3 = Box.createHorizontalBox();

    Box cajaArea = Box.createHorizontalBox();
    
    public PanelVentas() {

        setLayout(new BorderLayout());

        //---------LAMINA CENTRAL----------------
        centro = new JPanel();

        centro.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true));

        //-----CREACION DE LBL Y TXT----------
        cajavertical.add(Box.createVerticalStrut(2));

        crearcajas("Usuario", usuario, txtusuario);

        crearcajas("Fecha", fecha, txtfecha);

        crearcajas("Forma de pago", pago, txtpago);

        crearcajas("Moneda", moneda, txtmoneda);

        crearcajas("Cliente", cliente, txtcliente);

        cajavertical.add(caja1);

        caja1.add(Box.createVerticalStrut(5));

        crearcajas1("Nº de comprobante", ncomprobante, ntxtcomprobante);

        cajavertical.add(caja2);

        caja2.add(Box.createHorizontalStrut(700));
        

//        Caja productos
        cajaProductos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producto"));

        crearcajasProducto("Nombre", nomProducto, txtNomProducto);

        busProducto = new JButton(new ImageIcon("src/main/java/resources/buscar.png"));

        cajaProductos.add(busProducto);

        cajaProductos.add(Box.createHorizontalStrut(15));

        crearcajasProducto("Codigo", codProducto, txtCodProducto);

        crearcajasProducto("Laboratorio", labProducto, txtLabProducto);

        crearcajasProducto("Stock", stock, txtStock);

        crearcajasProducto("Descripcion", descripcion, txtDescripcion);

        cajavertical.add(cajaProductos);

        cajavertical.add(Box.createVerticalStrut(10));

//        Tabla

        crearTabla();
        
//        cajaTabla.setBorder(BorderFactory.createLineBorder(Color.black));
        
        cajavertical.add(cajaTabla);
        
        cajavertical.add(Box.createVerticalStrut(10));
        
//        Cantidad y botones
        
        cantidad = new JLabel("Cantidad");

        txtCantidad = new JTextField(10);

        caja3.add(cantidad);

        caja3.add(Box.createHorizontalStrut(5));

        caja3.add(txtCantidad);

        caja3.add(Box.createHorizontalStrut(15));
        
        agregar = new JButton("Agregar", new ImageIcon("src/main/java/resources/agregar.png"));
        
        caja3.add(agregar);
        
        caja3.add(Box.createHorizontalStrut(15));
        
        eliminar = new JButton("Eliminar ultimo", new ImageIcon("src/main/java/resources/eliminar.png"));
        
        caja3.add(eliminar);
        
        caja3.add(Box.createHorizontalStrut(15));

        cajavertical.add(caja3);
        
        cajavertical.add(Box.createVerticalStrut(10));
        
//        Creamos el pane llamando a la funcion correspondiente
        
        JTextPane area = new JTextPane();
        
        area.setPreferredSize(new Dimension(989, 200));
        
        JScrollPane scroll = new JScrollPane(area);
        
        cajavertical.add(scroll);
        
        cajavertical.add(Box.createVerticalStrut(10));

//        -------------------
        centro.add(cajavertical);
        
        centro.add(caja1);
        
        centro.add(caja2);

        centro.add(cajaProductos);
        
        centro.add(cajaTabla);
        
        centro.add(caja3);
        
        centro.add(scroll);

        add(centro, BorderLayout.CENTER);

        //-------LAMINA DERECHA------------------
        izquierda = new JPanel();

        izquierda.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true));

        Box caja3 = Box.createVerticalBox();

        realizarVenta = new JButton("Realizar venta");

        caja3.add(Box.createVerticalStrut(220));

        caja3.add(realizarVenta);

        izquierda.add(caja3);

        add(izquierda, BorderLayout.EAST);

        //---------------------------------------
    }

    public void crearcajas(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);

        txt = new JTextField(10);

        caja1.add(lbl);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txt);

        caja1.add(Box.createHorizontalStrut(15));

    }

    public void crearcajas1(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);

        txt = new JTextField(10);

        caja2.add(lbl);

        caja2.add(Box.createHorizontalStrut(5));

        caja2.add(txt);

        caja2.add(Box.createHorizontalStrut(15));

    }

    public void crearcajasProducto(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);

//        En caso sea la caja de descripcion la hacemos mas grande
        if (lbl1.equals("Descripcion")) {

            txt = new JTextField(15);

        } else {

            txt = new JTextField(8);

        }

        cajaProductos.add(lbl);

        cajaProductos.add(Box.createHorizontalStrut(5));

        cajaProductos.add(txt);

        cajaProductos.add(Box.createHorizontalStrut(10));

    }

    public void crearTabla() {
        
        String data[][] = {{"101", "Amit", "670000"},
        {"102", "Jai", "780000"},
        {"101", "Sachin", "700000"}};
        String column[] = {"ID", "NAME", "SALARY"};
        JTable jt = new JTable(data, column);
        jt.setPreferredScrollableViewportSize(new Dimension(989, 100));
        JScrollPane sp=new JScrollPane(jt);   
        cajaTabla.add(sp);
        cajaTabla.add(Box.createVerticalStrut(10));

    }

}
