/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.FarmaceuticoDAO;
import com.mycompany.proyectofarmacia.models.DTO.FarmaceuticoDTO;
import com.mycompany.proyectofarmacia.models.Impl.FarmaceuticoDAOImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
public class PanelFarmaceuticos extends JPanel {

    Box caja1, caja2, caja3;

    JLabel lblId, lblNombre, lblApellido, lblFecha, lblContra, lblDireccion, lblNacionalidad;

    JTextField txtNombre, txtApellido, txtFecha, txtDireccion, txtNacionalidad;

    JPasswordField txtpass;

//    TextField para editar
    JTextField Eid, EtxtNombre, EtxtApellido, EtxtFecha, EtxtDireccion, EtxtNacionalidad;

    JPasswordField Etxtpass;

    JButton btnRegistrar;

//    Buttons para editar
    JButton btnEditar, btnEliminar;

    public PanelFarmaceuticos() {

        setLayout(new FlowLayout());

        Box cajaVertical = Box.createVerticalBox();

        Box cajaVertical1 = Box.createVerticalBox();

        caja1 = Box.createHorizontalBox();

        caja2 = Box.createHorizontalBox();

        caja3 = Box.createHorizontalBox();

//        Caja 1
        caja1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Registrar Farmaceutico"));

        crearCajas("Nombre", lblNombre, txtNombre);
        crearCajas("Apellidos", lblApellido, txtApellido);
        crearCajas("Fecha de nacimiento", lblFecha, txtFecha);
        crearCajas("Direccion", lblDireccion, txtDireccion);
        crearCajas("Nacionalidad", lblNacionalidad, txtNacionalidad);
        crearCajasContra("Contraseña", lblContra, txtpass);

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

        crearCajas1("Id", lblId, Eid);
        crearCajas1("Nombre", lblNombre, EtxtNombre);
        crearCajas1("Apellidos", lblApellido, EtxtApellido);
        crearCajas1("Fecha de nacimiento", lblFecha, EtxtFecha);
        crearCajas1("Direccion", lblDireccion, EtxtDireccion);
        crearCajas1("Nacionalidad", lblNacionalidad, EtxtNacionalidad);
        crearCajasContra1("Contraseña", lblContra, Etxtpass);

        btnEditar = new JButton(new ImageIcon("src/main/java/resources/salvar.png"));

        btnEliminar = new JButton(new ImageIcon("src/main/java/resources/borrar.png"));

        caja3.add(btnEditar);

        caja3.add(btnEliminar);

        cajaVertical1.add(caja3);

        add(cajaVertical1);

    }

    public void crearCajas(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);

        txt = new JTextField(5);

        caja1.add(lbl);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txt);

        caja1.add(Box.createHorizontalStrut(10));

    }

    public void crearCajasContra(String lbl1, JLabel lbl, JPasswordField txt) {

        lbl = new JLabel(lbl1);

        txt = new JPasswordField(5);

        caja1.add(lbl);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txt);

        caja1.add(Box.createHorizontalStrut(10));

    }

    public void crearCajas1(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);

        txt = new JTextField(6);

        caja3.add(lbl);

        caja3.add(Box.createHorizontalStrut(3));

        caja3.add(txt);

        caja3.add(Box.createHorizontalStrut(10));

    }

    public void crearCajasContra1(String lbl1, JLabel lbl, JPasswordField txt) {

        lbl = new JLabel(lbl1);

        txt = new JPasswordField(4);

        caja3.add(lbl);

        caja3.add(Box.createHorizontalStrut(5));

        caja3.add(txt);

        caja3.add(Box.createHorizontalStrut(10));

    }

    public void crearTabla() {

//        Cargamos los datos de la tabla con un select
        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            FarmaceuticoDAO farmaceuticoDao = new FarmaceuticoDAOImpl(conexion);

            //aca se hace las consultas
            List<FarmaceuticoDTO> farmaceuticos = farmaceuticoDao.select();

            Object ob[] = new Object[7];

//            Creacion de la tabla
            JTable jt = new JTable();

            DefaultTableModel model = new DefaultTableModel();

            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Apellido");
            model.addColumn("Fecha nac.");
            model.addColumn("Edad");
            model.addColumn("Direccion");
            model.addColumn("Nacionalidad");

            jt.setModel(model);

            for (int i = 0; i < farmaceuticos.size(); i++) {
                ob[0] = farmaceuticos.get(i).getIdFarmaceutico();
                ob[1] = farmaceuticos.get(i).getNombre();
                ob[2] = farmaceuticos.get(i).getApellido();
                ob[3] = farmaceuticos.get(i).getFecNac();
                ob[4] = farmaceuticos.get(i).getEdad();
                ob[5] = farmaceuticos.get(i).getDireccion();
                ob[6] = farmaceuticos.get(i).getNacionalidad();
                model.addRow(ob);
            }

            jt.setModel(model);

            jt.setPreferredScrollableViewportSize(new Dimension(1175, 280));
            JScrollPane sp = new JScrollPane(jt);

            caja2.add(sp);
            caja2.add(Box.createVerticalStrut(10));

            //-----------------------------
            conexion.commit();
            System.out.println("se hizo commit");
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }

    }

}
