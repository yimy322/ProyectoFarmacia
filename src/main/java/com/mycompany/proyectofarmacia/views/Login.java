/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import com.mycompany.proyectofarmacia.controllers.LoginController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author yimy
 */
public class Login extends JFrame{
    
    public Login() {
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setResizable(false);

        setSize(350, 250);

        setLocationRelativeTo(null);

        setTitle("Login");

        Toolkit pantalla = Toolkit.getDefaultToolkit();

        Image icono = pantalla.getImage("src/main/java/resources/logo.png");

        setIconImage(icono);

        add(new LaminaLogin());

    }
    
}
class LaminaLogin extends JPanel {
    
    JTextField txtuser;
    
    JPasswordField txtpass;

    public LaminaLogin() {

        //--PONEMOS LA DISPOSICION QUE DESEAMOS EN ESTE CASO
        //--BORDERLAYOUT
        setLayout(new BorderLayout());

        JPanel laminaima = new JPanel();

        //--CONVERTIMOS UN IMAGEICON A IMAGE PARA AJUSTAR SU TAMAÑO
        //--PORQUE LA CLASE IMAGE SI PUEDE Y LA IMAGEICON NO.
        ImageIcon icon = new ImageIcon("src/main/java/resources/login.png");

        Image nueva = icon.getImage();

        Image conversion = nueva.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        ImageIcon ulti = new ImageIcon(conversion);

        //--AGREGAMOS LA IMAGEN EN UN JLABEL
        JLabel ima = new JLabel(ulti);

        laminaima.add(ima);

        add(laminaima, BorderLayout.NORTH);

        JPanel laminap = new JPanel();

        //ACA PONEMOS EL LAYOUT ENCOLUMNAS, ESTE
        //LAYOUT TIENE LA INTERFAZ LayoutManager
        //QUE PERMITE CREAR NUESTRO PROPIO LAYOUT
        laminap.setLayout(new EnColumnas());

        //--ACA PONEMOS LOS JLABEL Y LOS JTEXTEXFIELD 
        //--AMBOS SON DEL PAQUETE SWING
        JLabel user = new JLabel(tamima("src/main/java/resources/user.png"));

        txtuser = new JTextField();

        LineBorder lineBorder = new LineBorder(Color.white, 5, true);

        txtuser.setBorder(lineBorder);

        txtuser.setFont(new Font("Arial", Font.PLAIN, 16));

        TextPrompt placeholder = new TextPrompt("Usuario", txtuser);

        placeholder.changeAlpha(0.75f);

        JLabel pass = new JLabel(tamima("src/main/java/resources/pass.png"));

        txtpass = new JPasswordField();

        txtpass.setBorder(lineBorder);

        txtpass.setFont(new Font("Arial", Font.PLAIN, 16));

        TextPrompt placeholder1 = new TextPrompt("Contraseña", txtpass);

        placeholder1.changeAlpha(0.75f);
        
        //--ACA AGREGAMOS LOS JTEXTFIELD Y LOS JLABEL QUE
        //--MENCIONAMOS ANTERIORMENTE
        laminap.add(user);

        laminap.add(txtuser);

        laminap.add(pass);

        laminap.add(txtpass);

        add(laminap, BorderLayout.CENTER);

        //--ACA DECLARAMOS LOS JBUTTON
        JPanel laminabtn = new JPanel();

        laminabtn.setLayout(new FlowLayout());

        JButton btningreso = new JButton("Ingresar");
        
        JButton btnregistro = new JButton("Salir");

        btningreso.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        btnregistro.setFont(new Font("Tahoma", Font.BOLD, 14));

        btningreso.setBackground(Color.CYAN);
        
        btnregistro.setBackground(Color.RED);
        
        btnregistro.setForeground(Color.WHITE);
        
//        Agregamos la funcionalidad al jbutton

        btningreso.addActionListener(new accion());
        
        btnregistro.addActionListener(new salir());
        
//        -------------------

        laminabtn.add(btningreso);
        
        laminabtn.add(btnregistro);

        add(laminabtn, BorderLayout.SOUTH);

        //PINTAMOS LAS LAMINAS
        laminap.setBackground(new Color(48, 61, 66));

        laminaima.setBackground(new Color(48, 61, 66));

        laminabtn.setBackground(new Color(45, 45, 45));

    }
    
    public class accion implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            String usuario = txtuser.getText();
            String password = txtpass.getText();
            
            LoginController controlador = new LoginController(usuario, password);

        }
        
    }
    
    public class salir implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            System.exit(0);

        }
        
    }
    
    //ESTE METODO ME PERMITE REDIMENSIONAR EL TAMAÑO DE UNA IMAGEN
    //ME RETORNA UN IMAGEICON
    private ImageIcon tamima(String url) {

        ImageIcon icon = new ImageIcon(url);

        Image nueva = icon.getImage();

        Image conversion = nueva.getScaledInstance(25, 25, Image.SCALE_SMOOTH);

        ImageIcon ulti = new ImageIcon(conversion);

        return ulti;

    }

}
//ESTE ES LA CLASE DEL LAYOUT CREADO

class EnColumnas implements LayoutManager {

    private int x;

    private int y = 20;

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {

        return null;
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {

        return null;
    }

    @Override

    //ACA POSICIONAMOS LOS COMPONENTES
    public void layoutContainer(Container parent) {

        int d = parent.getWidth();

        x = (d / 2) - 80;

        int cont = 0;

        //ACA CAPTURAMOS LA CANTIDAD DE COMPONENTE COLOCADOS
        int n = parent.getComponentCount();

        //ESTE FOR RECORRE LA CANTIDAD DE COMPONENTES
        //Y LOS COLOCA, SEGUN LAS INDICACIONES.
        for (int i = 0; i < n; i++) {

            cont++;

            Component c = parent.getComponent(i);

            c.setBounds(x - 100, y, 170, 25);

            x += 115;

            if (cont % 2 == 0) {

                x = (d / 2) - 80;

                y += 40;

            }

        }

    }

}

