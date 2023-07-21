/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author yimy
 */
public class PanelFarmaceuticos extends JPanel {

    Box caja1, caja2, caja3;

    JLabel lblId, lblNombre, lblApellido, lblFecha, lblEdad, lblContra, lblDireccion, lblNacionalidad;

    public JTextField txtNombre, txtApellido, txtFecha, txtDireccion, txtNacionalidad;

    public JPasswordField txtpass;

//    TextField para editar
    public JTextField Eid, EtxtNombre, EtxtApellido, EfechaNac,EtxtEdad, EtxtDireccion, EtxtNacionalidad;

    public JPasswordField Etxtpass;

    public JButton btnRegistrar;

//    Buttons para editar
    public JButton btnEditar, btnEliminar;

//    El datechoooser
    public JDateChooser Ejd, jd;

    public JTable jt;

    public DefaultTableModel model;

    public PanelFarmaceuticos() {

        setLayout(new FlowLayout());

        Box cajaVertical = Box.createVerticalBox();

        Box cajaVertical1 = Box.createVerticalBox();

        caja1 = Box.createHorizontalBox();

        caja2 = Box.createHorizontalBox();

        caja3 = Box.createHorizontalBox();

//        Caja 1
        //Creacion de labels y txts
        caja1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Registrar Farmaceutico"));

        lblNombre = new JLabel("Nombre");

        txtNombre = new JTextField(5);

        caja1.add(lblNombre);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtNombre);

        caja1.add(Box.createHorizontalStrut(10));
        

        lblApellido = new JLabel("Apellidos");

        txtApellido = new JTextField(5);

        caja1.add(lblApellido);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtApellido);

        caja1.add(Box.createHorizontalStrut(10));
        

        lblFecha = new JLabel("Fecha de nac.");

        jd = new JDateChooser();

        caja1.add(lblFecha);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(jd);

        caja1.add(Box.createHorizontalStrut(10));
        

        lblDireccion = new JLabel("Direccion");

        txtDireccion = new JTextField(5);

        caja1.add(lblDireccion);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtDireccion);

        caja1.add(Box.createHorizontalStrut(10));
        

        lblNacionalidad = new JLabel("Nacionalidad");

        txtNacionalidad = new JTextField(5);

        caja1.add(lblNacionalidad);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtNacionalidad);

        caja1.add(Box.createHorizontalStrut(10));
        

        lblContra = new JLabel("Contraseña");

        txtpass = new JPasswordField(5);

        caja1.add(lblContra);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtpass);

        caja1.add(Box.createHorizontalStrut(10));
        

        btnRegistrar = new JButton("Registrar", new ImageIcon("src/main/java/resources/agregar.png"));

        caja1.add(btnRegistrar);

        cajaVertical.add(caja1);

//        Caja 2
//        Para dar un espacio determinado entre cajas
        cajaVertical.add(Box.createVerticalStrut(10));

        crearTabla();

        JButton addExcel = new JButton("Exportar tabla a excel", new ImageIcon("src/main/java/resources/excel.png"));

        cajaVertical.add(caja2);

        add(cajaVertical);

        add(addExcel);

//        Caja 3
        caja3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Editar o eliminar Farmaceutico"));

        lblId = new JLabel("Id");

        Eid = new JTextField(3);
        
        Eid.setEnabled(false);

        caja3.add(lblId);

        caja3.add(Box.createHorizontalStrut(3));

        caja3.add(Eid);

        caja3.add(Box.createHorizontalStrut(10));
        

        lblNombre = new JLabel("Nombre");

        EtxtNombre = new JTextField(6);

        caja3.add(lblNombre);

        caja3.add(Box.createHorizontalStrut(3));

        caja3.add(EtxtNombre);

        caja3.add(Box.createHorizontalStrut(10));
        

        lblApellido = new JLabel("Apellidos");

        EtxtApellido = new JTextField(6);

        caja3.add(lblApellido);

        caja3.add(Box.createHorizontalStrut(3));

        caja3.add(EtxtApellido);

        caja3.add(Box.createHorizontalStrut(10));
        

        lblFecha = new JLabel("Fec. nac.");

        EfechaNac = new JTextField(7);
        
        EfechaNac.setEnabled(false);

        caja3.add(lblFecha);

        caja3.add(Box.createHorizontalStrut(3));

        caja3.add(EfechaNac);

        caja3.add(Box.createHorizontalStrut(10));
        
        
        lblEdad = new JLabel("Edad");

        EtxtEdad = new JTextField(3);
        
        EtxtEdad.setEnabled(false);

        caja3.add(lblEdad);

        caja3.add(Box.createHorizontalStrut(3));

        caja3.add(EtxtEdad);

        caja3.add(Box.createHorizontalStrut(10));
        

        lblDireccion = new JLabel("Direccion");

        EtxtDireccion = new JTextField(6);

        caja3.add(lblDireccion);

        caja3.add(Box.createHorizontalStrut(3));

        caja3.add(EtxtDireccion);

        caja3.add(Box.createHorizontalStrut(10));
        

        lblNacionalidad = new JLabel("Nacionalidad");

        EtxtNacionalidad = new JTextField(6);

        caja3.add(lblNacionalidad);

        caja3.add(Box.createHorizontalStrut(3));

        caja3.add(EtxtNacionalidad);

        caja3.add(Box.createHorizontalStrut(10));
        

        lblContra = new JLabel("Contraseña");

        Etxtpass = new JPasswordField(6);

        caja3.add(lblContra);

        caja3.add(Box.createHorizontalStrut(5));

        caja3.add(Etxtpass);

        caja3.add(Box.createHorizontalStrut(10));
        

        btnEditar = new JButton(new ImageIcon("src/main/java/resources/salvar.png"));

        btnEliminar = new JButton(new ImageIcon("src/main/java/resources/borrar.png"));

        caja3.add(btnEditar);

        caja3.add(btnEliminar);

        cajaVertical1.add(caja3);

        add(cajaVertical1);

    }

    public void crearTabla() {
//            Creacion de la tabla
        jt = new JTable();

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Fecha nac.");
        model.addColumn("Edad");
        model.addColumn("Direccion");
        model.addColumn("Nacionalidad");
        model.addColumn("Contraseña");

        jt.setModel(model);

        jt.setPreferredScrollableViewportSize(new Dimension(1175, 335));
        JScrollPane sp = new JScrollPane(jt);

        caja2.add(sp);
        caja2.add(Box.createVerticalStrut(10));

    }

}
