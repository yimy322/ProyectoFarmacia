/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.controllers;

import static com.mycompany.proyectofarmacia.controllers.LoginController.NAME;
import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.PagoDAO;
import com.mycompany.proyectofarmacia.models.DAO.ProductoDAO;
import com.mycompany.proyectofarmacia.models.DTO.PagoDTO;
import com.mycompany.proyectofarmacia.models.DTO.ProductoDTO;
import com.mycompany.proyectofarmacia.models.Impl.PagoDAOImpl;
import com.mycompany.proyectofarmacia.models.Impl.ProductoDAOImpl;
import com.mycompany.proyectofarmacia.views.PanelVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author yimy
 */
public class VentaController implements ActionListener, KeyListener, MouseListener {

    private PanelVentas vista;

    private TableRowSorter tFiltro;

    public VentaController(PanelVentas vista) {
        this.vista = vista;

//        Le damos accion a la caja de busqueda
        this.vista.txtNomProducto.addKeyListener(this);

        cargarDatos();

//        Cargamos los datos de la tabla en el combo
        cargarCombo();
        
//        Le damos accion a la tabla
        this.vista.jt.addMouseListener(this);

    }

//    Este metodo se encarga de iniciar la vista que recibio el constructor 
    public void iniciarVista() {

        vista.setVisible(true);

    }

//    Este metodo se encarga de cerrar la vista que recibio el constructor   
    public void cerrarVista() {

        vista.setVisible(false);

    }

    public void cargarDatos() {

//        Para capturar la fecha actual
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();

        this.vista.txtusuario.setText(NAME);

        this.vista.txtfecha.setText(simpleDateFormat.format(c.getTime()));
        
        this.vista.jt.setRowSorter(tFiltro);

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

    public void cargarCombo() {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            PagoDAO pagoDao = new PagoDAOImpl(conexion);

            //aca se hace las consultas
//            Antes de cargar los datos, vamos a borrar los anteriores para que no se repita
            List<PagoDTO> pagos = pagoDao.select();
//            El metodo size retorna un entero con el tamaño del list
            for (int i = 0; i < pagos.size(); i++) {
                this.vista.txtpago.addItem(pagos.get(i).getTipo());
            }

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

//    Metodos sobrecargados de las interfaces
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

        filtro();

    }
//    Metodo para filtrar
    public void filtro() {
//        Este tipo de tabla nos da el metodo que necesitamos para filtrar, ahi le pasamos el modelo
        tFiltro = new TableRowSorter(this.vista.model);
        
        this.vista.jt.setRowSorter(tFiltro);
//        Aca hace todo, en el regexfilter se captura lo que se ingresa en la casa y se pone el numero de columna a evaluar
        tFiltro.setRowFilter(RowFilter.regexFilter(this.vista.txtNomProducto.getText(), 1));
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
        //        Aca capturamos la fila seleccionada
        int filaSeleccionada = this.vista.jt.getSelectedRow();
        
//        Aca pasamos los valores a las cajas
        
        this.vista.txtNomProducto.setText(this.vista.model.getValueAt(filaSeleccionada, 1).toString());
        
        this.vista.txtCodProducto.setText(this.vista.model.getValueAt(filaSeleccionada, 2).toString());
        
        this.vista.txtLabProducto.setText(this.vista.model.getValueAt(filaSeleccionada, 3).toString());
        
        this.vista.txtStock.setText(this.vista.model.getValueAt(filaSeleccionada, 4).toString());
        
        this.vista.txtPrecio.setText(this.vista.model.getValueAt(filaSeleccionada, 5).toString());
        
        this.vista.txtDescripcion.setText(this.vista.model.getValueAt(filaSeleccionada, 6).toString());

        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

}
