/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.controllers;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.ProductoDAO;
import com.mycompany.proyectofarmacia.models.DTO.ProductoDTO;
import com.mycompany.proyectofarmacia.models.Impl.ProductoDAOImpl;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yimy
 */
public class InventarioController {

//    Registramos los datos capturados en el panel
    public void registrar(String nombre, String codigo, String laboratorio, int stock, Double precio, String descripcion) {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            ProductoDAO productoDao = new ProductoDAOImpl(conexion);

            //aca se hace las consultas
            ProductoDTO producto = new ProductoDTO(nombre, codigo, laboratorio, stock, precio, descripcion);

            productoDao.insert(producto);

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

    public void actualizarFilas(JTable jt, DefaultTableModel model) {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            ProductoDAO productoDao = new ProductoDAOImpl(conexion);

            //aca se hace las consultas
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
                model.addRow(ob);
            }

            jt.setModel(model);
            
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
