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
public class PanelFarmaceuticos extends JPanel{
    
    Box caja1, caja2, caja3;
    
    JLabel lblId, lblNombre, lblApellido, lblFecha, lblEdad, lblDireccion, lblNacionalidad;
    
    JTextField txtNombre, txtApellido, txtFecha, txtEdad, txtDireccion, txtNacionalidad;
    
//    TextField para editar
    
    JTextField Eid, EtxtNombre, EtxtApellido, EtxtFecha, EtxtEdad, EtxtDireccion, EtxtNacionalidad;
    
    JButton btnRegistrar;
    
//    Buttons para editar
    
    JButton btnEditar, btnEliminar;
    
    public PanelFarmaceuticos(){
        
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
        crearCajas("Edad", lblEdad, txtEdad);
        crearCajas("Direccion", lblDireccion, txtDireccion);
        crearCajas("Nacionalidad", lblNacionalidad, txtNacionalidad);
        
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
        crearCajas1("Edad", lblEdad, txtEdad);
        crearCajas1("Direccion", lblDireccion, EtxtDireccion);
        crearCajas1("Nacionalidad", lblNacionalidad, EtxtNacionalidad);
        
        btnEditar = new JButton(new ImageIcon("src/main/java/resources/salvar.png"));
        
        btnEliminar = new JButton(new ImageIcon("src/main/java/resources/borrar.png"));
        
        caja3.add(btnEditar);
        
        caja3.add(btnEliminar);
        
        cajaVertical1.add(caja3);
        
        add(cajaVertical1);
    
        
    }
    
    public void crearCajas(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);
        
//        Para que solo la caja de edad sea de 5 tam, el resto de 8
        
        if (lbl1.equalsIgnoreCase("edad")) {
            txt = new JTextField(4);
        }else{
            txt = new JTextField(8);
        }

        caja1.add(lbl);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txt);

        caja1.add(Box.createHorizontalStrut(10));

    }
    
    public void crearCajas1(String lbl1, JLabel lbl, JTextField txt) {

        lbl = new JLabel(lbl1);
        
//        Para que solo la caja de edad e id sea de 5 tam, el resto de 8
        
        if (lbl1.equalsIgnoreCase("edad") || lbl1.equalsIgnoreCase("id")) {
            txt = new JTextField(3);
        }else if(lbl1.equalsIgnoreCase("Direccion")){
            txt = new JTextField(8);
        }else{
            txt = new JTextField(7);
        }

        caja3.add(lbl);

        caja3.add(Box.createHorizontalStrut(5));

        caja3.add(txt);

        caja3.add(Box.createHorizontalStrut(10));

    }
    
    public void crearTabla() {
        
        String data[][] = {{"101", "Amit", "670000"},
        {"102", "Jai", "780000"},
        {"101", "Sachin", "700000"}};
        String column[] = {"ID", "NAME", "SALARY"};
        JTable jt = new JTable(data, column);
        jt.setPreferredScrollableViewportSize(new Dimension(1175, 335));
        JScrollPane sp=new JScrollPane(jt);   
        caja2.add(sp);
        caja2.add(Box.createVerticalStrut(10));

    }
    
}
