/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia;
import com.mycompany.proyectofarmacia.controllers.LoginController;
import com.mycompany.proyectofarmacia.views.Login;
/**
 *
 * @author yimy
 */
public class App {
    
    public static void main(String[] args) {
        
        Login vista = new Login();
        
        LoginController login = new LoginController(vista);
        
        login.iniciarVista();
        
    }
    
}
