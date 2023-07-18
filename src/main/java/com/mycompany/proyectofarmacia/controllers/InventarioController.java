/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.controllers;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.ProductoDAO;
import com.mycompany.proyectofarmacia.models.DTO.ProductoDTO;
import com.mycompany.proyectofarmacia.models.Impl.ProductoDAOImpl;
import com.mycompany.proyectofarmacia.views.PanelInventario;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yimy
 */
public class InventarioController implements ActionListener {

    private PanelInventario vista;

    private String nombre, codigo, laboratorio, stock, descripcion, precio;

//    La creacion de este objeto recibira una instancia de la clase Login
    public InventarioController(PanelInventario vista) {

        this.vista = vista;

//        Les damos acciones a los JButtons
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);

//        Cargamos los datos en la tabla
        actualizarFilas();

    }

//    Este metodo se encarga de iniciar la vista que recibio el constructor 
    public void iniciarVista() {

        vista.setVisible(true);

    }

//    Este metodo se encarga de cerrar la vista que recibio el constructor   
    public void cerrarVista() {

        vista.setVisible(false);

    }

//    Registramos los datos capturados en el panel
    public void registrar() {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            ProductoDAO productoDao = new ProductoDAOImpl(conexion);

            //aca se hace las consultas
            capturarDatos();

            ProductoDTO producto = new ProductoDTO(this.nombre, this.codigo, this.laboratorio, Integer.parseInt(this.stock), Double.parseDouble(this.precio), this.descripcion);

            productoDao.insert(producto);

            JOptionPane.showMessageDialog(null, "Producto registrado correctamente", "Tabla Productos", JOptionPane.INFORMATION_MESSAGE);

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

    public void actualizarFilas() {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            ProductoDAO productoDao = new ProductoDAOImpl(conexion);

            //aca se hace las consultas
//            Antes de cargar los datos, vamos a borrar los anteriores para que no se repita
            for (int j = 0; j < this.vista.jt.getRowCount(); j++) {
                this.vista.model.removeRow(j);
                j -= 1;
            }

            List<ProductoDTO> productos = productoDao.select();

            Object ob[] = new Object[7];
            for (int i = 0; i < productos.size(); i++) {
                ob[0] = productos.get(i).getIdProducto();
                ob[1] = productos.get(i).getNombre();
                ob[2] = productos.get(i).getCodigo();
                ob[3] = productos.get(i).getLaboratorio();
                ob[4] = productos.get(i).getStock();
                ob[5] = productos.get(i).getPrecio();
                ob[6] = productos.get(i).getDescripcion();
                this.vista.model.addRow(ob);
            }

            this.vista.jt.setModel(this.vista.model);

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

    public void capturarDatos() {

        this.nombre = this.vista.txtNomProducto.getText();

        this.codigo = this.vista.txtCodProducto.getText();

        this.laboratorio = this.vista.txtLabProducto.getText();

        this.stock = this.vista.txtStock.getText();

        this.descripcion = this.vista.txtDescripcion.getText();

        this.precio = this.vista.txtPrecio.getText();

    }
    
    public void limpiarDatos(){
        
        this.vista.txtNomProducto.setText("");

        this.vista.txtCodProducto.setText(cadenaAleatoria());

        this.vista.txtLabProducto.setText("");

        this.vista.txtStock.setText("");

        this.vista.txtDescripcion.setText("");

        this.vista.txtPrecio.setText("");
        
    }

    public boolean validarText() {

        capturarDatos();

        if (this.nombre.equals("") || this.codigo.equals("") || this.laboratorio.equals("") || this.stock.equals("") || this.descripcion.equals("") || this.precio.equals("")) {

            return false;

        } else {

            return true;

        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

//        Con eso capturamos el objeto que llama a este evento
        Object press = ae.getSource();

        if (press == this.vista.btnRegistrar) {

            System.out.println(validarText());

//            Si los campos no estan vacios
            if (validarText()) {

                registrar();

                actualizarFilas();
                
                limpiarDatos();

            } else {

                JOptionPane.showMessageDialog(null, "Rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } else if (press == this.vista.btnLimpiar) {

            limpiarDatos();
            
        }
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


}
