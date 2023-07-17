/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import com.mycompany.proyectofarmacia.controllers.InventarioController;
import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.ProductoDAO;
import com.mycompany.proyectofarmacia.models.DTO.ProductoDTO;
import com.mycompany.proyectofarmacia.models.Impl.ProductoDAOImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author yimy
 */
public class PanelInventario extends JPanel {

    InventarioController controller = new InventarioController();

    Box caja1, caja2, cajaTabla, cajaEditar, caja3;

    //    Labels del producto
    JLabel codProducto, nomProducto, labProducto, stock, descripcion, precio;

    //    TextField del producto
    JTextField txtCodProducto, txtNomProducto, txtLabProducto, txtStock, txtDescripcion, txtCantidad, txtPrecio;

    // Botones
    JButton btnRegistrar, btnLimpiar, btnEditar, btnEliminar;

    //    Labels de editar
    JLabel id, codEditar, nomEditar, labEditar, Edistock, Edidescripcion, Ediprecio;

    //    TextField de editar
    JTextField txtId, txtCodEditar, txtNomEditar, txtLabEditar, txtEdiStock, txtEdiDescripcion, txtEdiPrecio;

    //            Creacion de la tabla
    JTable jt = new JTable();

    DefaultTableModel model = new DefaultTableModel();

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

        cajaVertical.add(caja1);

        cajaVertical.add(Box.createVerticalStrut(10));

        crearcajasProducto1("Descripcion", descripcion, txtDescripcion);

        caja2.add(Box.createHorizontalStrut(30));

        btnRegistrar = new JButton("Registrar", new ImageIcon("src/main/java/resources/agregar.png"));

        btnLimpiar = new JButton("Limpiar", new ImageIcon("src/main/java/resources/eliminar.png"));

//        Agregando acciones a los buttons
        btnRegistrar.addActionListener(new accion());

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

        crearcajasEditar("Id", id, txtId);

        crearcajasEditar("Nombre", nomEditar, txtNomEditar);

        crearcajasEditar("Codigo", codEditar, txtCodEditar);

        crearcajasEditar("Laboratorio", labEditar, txtLabEditar);

        crearcajasEditar("Stock", Edistock, txtEdiStock);

        crearcajasEditar("Precio", Ediprecio, txtEdiPrecio);

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

            txt = new JTextField(14);

        }

        if (lbl1.equals("Codigo")) {
            txt.setText(controller.cadenaAleatoria());
            txt.setEnabled(false);
        }

        caja1.add(lbl);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txt);

        caja1.add(Box.createHorizontalStrut(10));

    }

    public void crearcajasProducto1(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);

        txt = new JTextField(10);

        caja2.add(lbl);

        caja2.add(Box.createHorizontalStrut(5));

        caja2.add(txt);

        caja2.add(Box.createHorizontalStrut(10));

    }

    public void crearTabla() {

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Codigo");
        model.addColumn("Laboratorio");
        model.addColumn("Stock");
        model.addColumn("Precio");
        model.addColumn("Descripcion");

        jt.setModel(model);

        controller.actualizarFilas(jt, model);

        jt.setPreferredScrollableViewportSize(new Dimension(1175, 280));
        JScrollPane sp = new JScrollPane(jt);

        cajaTabla.add(sp);
        cajaTabla.add(Box.createVerticalStrut(10));

    }

    public void crearcajasEditar(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);

        txt = new JTextField(11);

        cajaEditar.add(lbl);

        cajaEditar.add(Box.createHorizontalStrut(6));

        cajaEditar.add(txt);

        cajaEditar.add(Box.createHorizontalStrut(12));

    }

    class accion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            try {

                Object press = ae.getSource();

                String nombre = txtNomProducto.getText();

                String codigo = txtCodProducto.getText();

                String laboratorio = txtLabProducto.getText();

                int stock = Integer.parseInt(txtStock.getText());

                Double precio = Double.parseDouble(txtPrecio.getText());

                String descripcion = txtDescripcion.getText();

                if (press == btnRegistrar) {

//                    Llamamos al controlador
                    controller.registrar(nombre, codigo, laboratorio, stock, precio, descripcion);
                    
                    controller.actualizarFilas(jt, model);

                }

            } catch (RuntimeException e) {

                JOptionPane.showMessageDialog(null, "Completa todas las casillas", "Agregar producto", JOptionPane.ERROR_MESSAGE);

            }

        }

    }

}
