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
public class LoginController {

    private String usuario;
    private String password;
    private Login loginPanel = new Login();
    
//    Creacion de la variable global
    
    public static String NAME = "Admin";

    public LoginController(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
        validar();

    }

    public LoginController() {
        this.loginPanel.setVisible(true);
    }
    
    public void validar() {
        if (usuario.equals("")) {
            JOptionPane.showMessageDialog(null, "Completa todas las casillas","Login", JOptionPane.ERROR_MESSAGE);
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, "Completa todas las casillas","Login", JOptionPane.ERROR_MESSAGE);
        } else if (password.equals("") && password.equals("")) {
            JOptionPane.showMessageDialog(null, "Completa todas las casillas","Login", JOptionPane.ERROR_MESSAGE);
        } else {
            ingresar();
        }
    }

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

                if (this.usuario.equals(farmaceuticos.get(i).getNombre()) && this.password.equals(farmaceuticos.get(i).getContrasena())) {

                    JOptionPane.showMessageDialog(null, "Bienvenido "+NAME,"Sistema de farmacia", JOptionPane.INFORMATION_MESSAGE);
                    
                    NAME = farmaceuticos.get(i).getNombre();
                    
                    this.loginPanel.dispose();

                    loginPanel.setVisible(false);
                    
                    Menu marcopro = new Menu();

                    marcopro.setVisible(true);

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

}
