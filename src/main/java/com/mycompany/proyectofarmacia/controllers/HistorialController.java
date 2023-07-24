/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.controllers;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.VentasDAO;
import com.mycompany.proyectofarmacia.models.DTO.VentasDTO;
import com.mycompany.proyectofarmacia.models.Impl.VentasDAOImpl;
import com.mycompany.proyectofarmacia.views.PanelHistorial;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yimy
 */
public class HistorialController implements ActionListener{
    
    private PanelHistorial vista;

    public HistorialController(PanelHistorial vista) {
        this.vista = vista;
    }
    
//    Este metodo se encarga de iniciar la vista que recibio el constructor 
    public void iniciarVista() {

        vista.setVisible(true);

    }

//    Este metodo se encarga de cerrar la vista que recibio el constructor   
    public void cerrarVista() {

        vista.setVisible(false);

    }
    
    public void actualizarFilas() {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            VentasDAO ventasDao = new VentasDAOImpl(conexion);

            //aca se hace las consultas
//            Antes de cargar los datos, vamos a borrar los anteriores para que no se repita
            for (int j = 0; j < this.vista.jt.getRowCount(); j++) {
                this.vista.model.removeRow(j);
                j -= 1;
            }

            List<VentasDTO> ventas = ventasDao.select();

            Object ob[] = new Object[8];
            for (int i = 0; i < ventas.size(); i++) {
                ob[0] = ventas.get(i).getIdVenta();
                ob[1] = ventas.get(i).getIdFactura();
                ob[2] = ventas.get(i).getIdFarmaceutico();
                ob[3] = ventas.get(i).getIdPago();
                ob[4] = ventas.get(i).getFecha();
                ob[5] = ventas.get(i).getMoneda();
                ob[6] = ventas.get(i).getNomCliente();
                ob[7] = ventas.get(i).getTotal();
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


    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
