/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import com.mycompany.proyectofarmacia.controllers.*;
import static com.mycompany.proyectofarmacia.controllers.LoginController.NAME;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

/**
 *
 * @author yimy
 */
public class Menu extends JFrame {
    
    public Menu() {
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setResizable(false);
        
        setSize(1200, 600);
        
        setLocationRelativeTo(null);
        
        setTitle("Menu principal");
        
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        
        Image icono = pantalla.getImage("src/main/java/resources/logo.png");
        
        setIconImage(icono);
        
        LaminaMenu lampro = new LaminaMenu();
        
        Thread c = new Thread(lampro);
        
        c.start();
        
        add(lampro);
        
    }
    
}

class LaminaMenu extends JPanel implements Runnable {
    
    public JButton ventas, inventario, princi, histventas, farmaceuticos;
    
    PanelPrincipal principal = new PanelPrincipal();
    
    PanelVentas panelVentas = new PanelVentas();
    
    PanelInventario panelInventario = new PanelInventario();
    
    PanelHistorial panelHistorial = new PanelHistorial();
    
    PanelFarmaceuticos panelFarma = new PanelFarmaceuticos();
    
    JLabel lblhora;
    
    public LaminaMenu() {
        
        setLayout(new BorderLayout());
        
        JMenuBar menu = new JMenuBar();
        
        princi = new JButton("Principal", tamimage("src/main/java/resources/menu.png"));
        
        ventas = new JButton("Venta", tamimage("src/main/java/resources/ventas.png"));
        
        inventario = new JButton("Inventario", tamimage("src/main/java/resources/productos.png"));
        
        histventas = new JButton("Historial de ventas", tamimage("src/main/java/resources/histventas.png"));
        
        farmaceuticos = new JButton("Farmaceuticos", tamimage("src/main/java/resources/farma.png"));
        
        menu.add(princi);
        
        menu.add(ventas);
        
        menu.add(inventario);
        
        menu.add(histventas);
        
        menu.add(farmaceuticos);
        
        add(menu, BorderLayout.NORTH);

        //-------------VENTANA-PRINCIPAL----------
        add(principal, BorderLayout.CENTER);

        //-----------ACCIONES---------------------
        princi.addActionListener(new accion());
        
        ventas.addActionListener(new accion());
        
        inventario.addActionListener(new accion());
        
        histventas.addActionListener(new accion());
        
        farmaceuticos.addActionListener(new accion());
        
        desbtn();

        //-------------FOOTER--------------------
        JMenuBar menufooter = new JMenuBar();
        
        menufooter.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        JLabel lbl = new JLabel(tamimage("src/main/java/resources/usuario.png"));
        
        JLabel usuario = new JLabel(NAME);
        
        JLabel espacio = new JLabel("          ");
        
        JLabel iconfecha = new JLabel(tamimage("src/main/java/resources/fecha.png"));
        
        lblhora = new JLabel();
        
        menufooter.add(lblhora);
        
        menufooter.add(iconfecha);
        
        menufooter.add(espacio);
        
        menufooter.add(usuario);
        
        menufooter.add(lbl);
        
        add(menufooter, BorderLayout.SOUTH);
        
    }
    
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            
            try {
                
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy, HH:mm:ss");
                
                Calendar c = Calendar.getInstance();
                
                lblhora.setText(simpleDateFormat.format(c.getTime()));
                
                Thread.sleep(1000);
                
            } catch (InterruptedException ex) {
                
            }
            
        }
    }
    
    private void desbtn() {
        
        if (principal.isVisible()) {
            
            princi.setEnabled(false);
            
            ventas.setEnabled(true);
            
            inventario.setEnabled(true);
            
            histventas.setEnabled(true);
            
            farmaceuticos.setEnabled(true);
            
        } else if (panelVentas.isVisible()) {
            
            princi.setEnabled(true);
            
            ventas.setEnabled(false);
            
            inventario.setEnabled(true);
            
            histventas.setEnabled(true);
            
            farmaceuticos.setEnabled(true);
            
        } else if (panelInventario.isVisible()) {
            
            princi.setEnabled(true);
            
            ventas.setEnabled(true);
            
            inventario.setEnabled(false);
            
            histventas.setEnabled(true);
            
            farmaceuticos.setEnabled(true);
            
        } else if (panelHistorial.isVisible()) {
            
            princi.setEnabled(true);
            
            ventas.setEnabled(true);
            
            inventario.setEnabled(true);
            
            histventas.setEnabled(false);
            
            farmaceuticos.setEnabled(true);
            
        } else if (panelFarma.isVisible()) {
            
            princi.setEnabled(true);
            
            ventas.setEnabled(true);
            
            inventario.setEnabled(true);
            
            histventas.setEnabled(true);
            
            farmaceuticos.setEnabled(false);
            
        }
        
    }
    
    InventarioController inventarioC = new InventarioController(panelInventario);
    
    FarmaceuticoController farmaceuticoC = new FarmaceuticoController(panelFarma);
    
    VentaController ventaC = new VentaController(panelVentas);
    
    HistorialController historialC = new HistorialController(panelHistorial);
    
    class accion implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            Object press = e.getSource();
            
            if (press == princi) {
                
                principal.setVisible(true);
                
                ventaC.cerrarVista();
                
                inventarioC.cerrarVista();
                
                historialC.cerrarVista();
                
                farmaceuticoC.cerrarVista();
                
                add(principal, BorderLayout.CENTER);
                
                validate();
                
                desbtn();
                
            } else if (press == ventas) {
                
                principal.setVisible(false);

//                ---
                ventaC.iniciarVista();
                
                ventaC.actualizarFilas();

//                ---
                inventarioC.cerrarVista();
                
                historialC.cerrarVista();
                
                farmaceuticoC.cerrarVista();
                
                add(panelVentas, BorderLayout.CENTER);
                
                validate();
                
                desbtn();
                
            } else if (press == inventario) {
                
                principal.setVisible(false);
                
                ventaC.cerrarVista();

//                ---
                inventarioC.iniciarVista();
                
                inventarioC.actualizarFilas();

//                ---
                historialC.cerrarVista();
                
                farmaceuticoC.cerrarVista();
                
                add(panelInventario, BorderLayout.CENTER);
                
                validate();
                
                desbtn();
                
            } else if (press == histventas) {
                
                principal.setVisible(false);
                
                ventaC.cerrarVista();
                
                inventarioC.cerrarVista();
                
//                -------------
                
                historialC.iniciarVista();
                
                historialC.actualizarFilas();
                
//                ------------------
                
                farmaceuticoC.cerrarVista();
                
                add(panelHistorial, BorderLayout.CENTER);
                
                validate();
                
                desbtn();
                
            } else if (press == farmaceuticos) { 

//                ---
                if (NAME.equals("admin")) {
                    
                    principal.setVisible(false);

                    ventaC.cerrarVista();

                    inventarioC.cerrarVista();

                    historialC.cerrarVista();
                    
                    farmaceuticoC.iniciarVista();
                    
                    farmaceuticoC.actualizarFilas();
                    
                    add(panelFarma, BorderLayout.CENTER);
                    
                }
                
//                ---
                
                validate();
                
                desbtn();
                
            }
            
        }
        
    }
    
    public ImageIcon tamimage(String url) {
        
        ImageIcon icono = new ImageIcon(url);
        
        Image imagen = icono.getImage();
        
        Image conversion = imagen.getScaledInstance(16, 16, Image.SCALE_SMOOTH);
        
        ImageIcon resultado = new ImageIcon(conversion);
        
        return resultado;
        
    }
    
}
