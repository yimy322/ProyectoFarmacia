/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.controllers;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.FarmaceuticoDAO;
import com.mycompany.proyectofarmacia.models.DTO.FarmaceuticoDTO;
import com.mycompany.proyectofarmacia.models.Impl.FarmaceuticoDAOImpl;
import com.mycompany.proyectofarmacia.views.Login;
import com.mycompany.proyectofarmacia.views.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author yimy
 */
public class LoginController implements ActionListener{

    private Login vista;
    
//    Creacion de la variable global
    
    public static String NAME = "";

//    La creacion de este objeto recibira una instancia de la clase Login
    public LoginController(Login vista) {
        
        this.vista = vista;
        
//        Les damos acciones a los JButtons
        this.vista.btningreso.addActionListener(this);
        this.vista.btnsalir.addActionListener(this);

    }
    
//    Este metodo se encarga de iniciar la vista que recibio el constructor 
    public void iniciarVista(){
        
        vista.setVisible(true);
        
    }
    
//    Este metodo se encarga de cerrar la vista que recibio el constructor   
    public void cerrarVista(){
        
        vista.dispose();
        
    }
    
//    Valida que no haya campos vacios
    public void validar() {
        if (this.vista.txtuser.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Completa todas las casillas","Login", JOptionPane.ERROR_MESSAGE);
        } else if (this.vista.txtpass.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Completa todas las casillas","Login", JOptionPane.ERROR_MESSAGE);
        } else if (this.vista.txtuser.getText().equals("") && this.vista.txtpass.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Completa todas las casillas","Login", JOptionPane.ERROR_MESSAGE);
        } else {
            ingresar();
        }
    }

//    Se encarga de hacer la consulta
    public void ingresar() {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            FarmaceuticoDAO farmaceuticoDao = new FarmaceuticoDAOImpl(conexion);

            //aca se hace las consultas
            List<FarmaceuticoDTO> farmaceuticos = farmaceuticoDao.selectAll();

            for (int i = 0; i < farmaceuticos.size(); i++) {

                if (this.vista.txtuser.getText().equals(farmaceuticos.get(i).getNombre()) && this.vista.txtpass.getText().equals(farmaceuticos.get(i).getContrasena())) {

                    cerrarVista();
                    
                    JOptionPane.showMessageDialog(null, "Bienvenido "+NAME,"Sistema de farmacia", JOptionPane.INFORMATION_MESSAGE);
                    
                    NAME = farmaceuticos.get(i).getNombre();
                    
                    Menu marcopro = new Menu();
                    
                    marcopro.setVisible(true);

                }else{
                    
                    JOptionPane.showMessageDialog(null, "Usuario inexistente","Error", JOptionPane.ERROR_MESSAGE);
                    
                }

            }

            //------------------
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
    
//    Se encarga de cerrar la app
    public void salir(){
        
        System.exit(0);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
//        Con eso capturamos el objeto que llama a este evento
        Object press = ae.getSource();

        if (press == this.vista.btnsalir) {
            
            salir();
            
        }else if(press == this.vista.btningreso){
            
            validar();
        }
        
        
    }

}
