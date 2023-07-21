/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yimy
 */
public class PanelInventario extends JPanel {

    Box caja1, caja2, cajaTabla, cajaEditar, caja3;

    //    Labels del producto
    public JLabel codProducto, nomProducto, labProducto, stock, descripcion, precio;

    //    TextField del producto
    public JTextField txtCodProducto, txtNomProducto, txtLabProducto, txtStock, txtDescripcion, txtPrecio;

    // Botones
    public JButton btnRegistrar, btnLimpiar, btnEditar, btnEliminar;

    //    Labels de editar
    JLabel id, codEditar, nomEditar, labEditar, Edistock, Edidescripcion, Ediprecio;

    //    TextField de editar
    public JTextField txtId, txtCodEditar, txtNomEditar, txtLabEditar, txtEdiStock, txtEdiDescripcion, txtEdiPrecio;

    //Creacion de la tabla
    public JTable jt;

    public DefaultTableModel model;

//    -----------
    public PanelInventario() {

        setLayout(new FlowLayout());

        Box cajaVertical = Box.createVerticalBox();

        Box cajaVertical1 = Box.createVerticalBox();

        Box cajaVertical2 = Box.createVerticalBox();

        caja1 = Box.createHorizontalBox();

        caja2 = Box.createHorizontalBox();

        caja3 = Box.createHorizontalBox();

        cajaVertical.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Agregar Producto"));

//        Creacion de cajas
        //Labels
        nomProducto = new JLabel("Nombre");

        codProducto = new JLabel("Codigo");

        labProducto = new JLabel("Laboratorio");

        stock = new JLabel("Stock");

        precio = new JLabel("Precio");

        descripcion = new JLabel("Descripcion");

        //txts
        txtNomProducto = new JTextField(12);

        txtCodProducto = new JTextField(8);

        txtLabProducto = new JTextField(12);

        txtStock = new JTextField(8);

        txtDescripcion = new JTextField(14);

        txtPrecio = new JTextField(10);

        //Agregamos el codigo a la caja txtcodproducto con la funcion 
        txtCodProducto.setText(cadenaAleatoria());
        txtCodProducto.setEnabled(false);

        //Validamos la entrada de solo numeros a algunas cajas
        txtStock.addKeyListener(new eventoTeclado());

        txtPrecio.addKeyListener(new eventoTeclado());

        //Agregamos a la caja
        caja1.add(nomProducto);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtNomProducto);

        caja1.add(Box.createHorizontalStrut(10));

        caja1.add(codProducto);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtCodProducto);

        caja1.add(Box.createHorizontalStrut(10));

        caja1.add(labProducto);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtLabProducto);

        caja1.add(Box.createHorizontalStrut(10));

        caja1.add(stock);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtStock);

        caja1.add(Box.createHorizontalStrut(10));

        caja1.add(precio);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtPrecio);

        caja1.add(Box.createHorizontalStrut(10));

        caja1.add(descripcion);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtDescripcion);

        caja1.add(Box.createHorizontalStrut(10));

        cajaVertical.add(caja1);

        cajaVertical.add(Box.createVerticalStrut(10));

//        -----------------------------------------------------------------------
        caja2.add(Box.createHorizontalStrut(30));

        btnRegistrar = new JButton("Registrar", new ImageIcon("src/main/java/resources/agregar.png"));

        btnLimpiar = new JButton("Limpiar", new ImageIcon("src/main/java/resources/eliminar.png"));

//        --------------------------------
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
        
//        Creacion de cajas para el box de editar

        id = new JLabel("Id");
        
        nomEditar = new JLabel("Nombre");
        
        codEditar = new JLabel("Codigo");
        
        labEditar = new JLabel("Laboratorio");
        
        Edistock = new JLabel("Stock");
        
        Ediprecio = new JLabel("Precio");
        

        txtId = new JTextField(11);
        
        txtNomEditar = new JTextField(11);
        
        txtCodEditar = new JTextField(11);
        
        txtLabEditar = new JTextField(11);
        
        txtEdiStock = new JTextField(11);
        
        txtEdiPrecio = new JTextField(11);
        
//        Invalidamos algunas cajas para que no puedan ser modificadas

        txtId.setEnabled(false);
        
        txtCodEditar.setEnabled(false);
        
//        ------------------------------
        
        cajaEditar.add(id);

        cajaEditar.add(Box.createHorizontalStrut(6));

        cajaEditar.add(txtId);

        cajaEditar.add(Box.createHorizontalStrut(12));
        
        
        cajaEditar.add(nomEditar);

        cajaEditar.add(Box.createHorizontalStrut(6));

        cajaEditar.add(txtNomEditar);

        cajaEditar.add(Box.createHorizontalStrut(12));
        
        
        cajaEditar.add(codEditar);

        cajaEditar.add(Box.createHorizontalStrut(6));

        cajaEditar.add(txtCodEditar);

        cajaEditar.add(Box.createHorizontalStrut(12));
        
        
        cajaEditar.add(labEditar);

        cajaEditar.add(Box.createHorizontalStrut(6));

        cajaEditar.add(txtLabEditar);

        cajaEditar.add(Box.createHorizontalStrut(12));
        
        
        cajaEditar.add(Edistock);

        cajaEditar.add(Box.createHorizontalStrut(6));

        cajaEditar.add(txtEdiStock);

        cajaEditar.add(Box.createHorizontalStrut(12));
        
        
        cajaEditar.add(Ediprecio);

        cajaEditar.add(Box.createHorizontalStrut(6));

        cajaEditar.add(txtEdiPrecio);

        cajaEditar.add(Box.createHorizontalStrut(12));
        
//        ------------------------------------------------------

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

//        -------------------
        caja3.add(btnEditar);

        caja3.add(btnEliminar);

        cajaVertical2.add(caja3);

        add(cajaVertical);

        add(cajaVertical1);

        add(addExcel);

        add(cajaVertical2);

    }

    public void crearTabla() {

        jt = new JTable();

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Codigo");
        model.addColumn("Laboratorio");
        model.addColumn("Stock");
        model.addColumn("Precio");
        model.addColumn("Descripcion");

        jt.setModel(model);

        jt.setPreferredScrollableViewportSize(new Dimension(1175, 280));
        JScrollPane sp = new JScrollPane(jt);

        cajaTabla.add(sp);
        cajaTabla.add(Box.createVerticalStrut(10));

    }

    //    Para hallar un numero aleatorio
    public String cadenaAleatoria() {
        // El banco de caracteres
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        // La cadena en donde iremos agregando un carácter aleatorio
        String cadena = "";
        for (int x = 0; x < 6; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }

    public int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

//    Clase para los eventos de las teclas
    class eventoTeclado implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {

//            Capturamos la tecla que presionamos
            int key = ke.getKeyChar();

//            validamos
            if (((key < '0') || (key > '9')) && (key != '.')) {
//                Este metodo evita que se coloque la tecla presionada
                ke.consume();
            }

        }

        @Override
        public void keyPressed(KeyEvent ke) {

        }

        @Override
        public void keyReleased(KeyEvent ke) {

        }

    }

}
