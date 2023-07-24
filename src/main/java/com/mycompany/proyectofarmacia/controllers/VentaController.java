/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.controllers;

import static com.mycompany.proyectofarmacia.controllers.LoginController.NAME;
import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.FacturaDAO;
import com.mycompany.proyectofarmacia.models.DAO.FarmaceuticoDAO;
import com.mycompany.proyectofarmacia.models.DAO.PagoDAO;
import com.mycompany.proyectofarmacia.models.DAO.ProductoDAO;
import com.mycompany.proyectofarmacia.models.DAO.VentasDAO;
import com.mycompany.proyectofarmacia.models.DTO.FacturaDTO;
import com.mycompany.proyectofarmacia.models.DTO.FarmaceuticoDTO;
import com.mycompany.proyectofarmacia.models.DTO.PagoDTO;
import com.mycompany.proyectofarmacia.models.DTO.ProductoDTO;
import com.mycompany.proyectofarmacia.models.DTO.VentasDTO;
import com.mycompany.proyectofarmacia.models.Impl.FacturaDAOImpl;
import com.mycompany.proyectofarmacia.models.Impl.FarmaceuticoDAOImpl;
import com.mycompany.proyectofarmacia.models.Impl.PagoDAOImpl;
import com.mycompany.proyectofarmacia.models.Impl.ProductoDAOImpl;
import com.mycompany.proyectofarmacia.models.Impl.VentasDAOImpl;
import com.mycompany.proyectofarmacia.views.PanelVentas;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author yimy
 */
public class VentaController implements ActionListener, KeyListener, MouseListener {

    private PanelVentas vista;

    private TableRowSorter tFiltro;

    private String usuario, fecha, forPago, moneda, cliente, Ncompro, nomPro, codPro, labPro, stockPro, desPro, prePro, cantPro;

//    Para guardar la cadena de string que entra en el textpane
    private ArrayList<String> arreglo = new ArrayList<String>();

//    Para guardar los precios, este es un array de objetos, todas las clases heredan de object
    private ArrayList<Double> total = new ArrayList<Double>();

//    Aca guardamos el total final
    private double TFinal;

    public VentaController(PanelVentas vista) {
        this.vista = vista;

//        Le damos accion a la caja de busqueda
        this.vista.txtNomProducto.addKeyListener(this);

        cargarDatos();

//        Cargamos los datos de la tabla en el combo
        cargarCombo();

//        Le damos accion a la tabla
        this.vista.jt.addMouseListener(this);

//        Añadimos accion el jbutton
        this.vista.agregar.addActionListener(this);
        this.vista.eliminar.addActionListener(this);
        this.vista.realizarVenta.addActionListener(this);
        this.vista.limpiar.addActionListener(this);

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

    //    Registramos los datos capturados en el panel
    public void registrar() {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            FacturaDAO facturaDao = new FacturaDAOImpl(conexion);

            ProductoDAO productoDao = new ProductoDAOImpl(conexion);

            FarmaceuticoDAO farmaceuticoDao = new FarmaceuticoDAOImpl(conexion);

            PagoDAO pagoDao = new PagoDAOImpl(conexion);

//            -------------------------------------------------------
            VentasDAO ventasDao = new VentasDAOImpl(conexion);

            //---------------------------------------
            capturarDatos();

            FacturaDTO facturaC = new FacturaDTO(this.Ncompro);

            FacturaDTO facturaCod = facturaDao.selectById(facturaC);
            

            FarmaceuticoDTO farmaceuticoC = new FarmaceuticoDTO(this.usuario);

            FarmaceuticoDTO farmaceuticoCod = farmaceuticoDao.selectById(farmaceuticoC);
            
            

            PagoDTO pagoC = new PagoDTO(this.forPago);

            PagoDTO pagoCod = pagoDao.selectById(pagoC);
            

            VentasDTO venta = new VentasDTO(facturaCod.getIdFactura(), farmaceuticoCod.getIdFarmaceutico(), pagoCod.getIdPago(), this.fecha, this.moneda, this.cliente, this.TFinal);

            ventasDao.insert(venta);

            JOptionPane.showMessageDialog(null, "Venta registrada correctamente", "Tabla Ventas", JOptionPane.INFORMATION_MESSAGE);

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

    public void registrarFactura() {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            FacturaDAO facturaDao = new FacturaDAOImpl(conexion);

            //aca se hace las consultas
            capturarDatos();

            FacturaDTO factura = new FacturaDTO(this.fecha, this.cliente, this.Ncompro);

            facturaDao.insert(factura);

            JOptionPane.showMessageDialog(null, "Factura registrada correctamente", "Factura", JOptionPane.INFORMATION_MESSAGE);

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

    public void capturarDatos() {

        this.usuario = this.vista.txtusuario.getText();

        this.fecha = this.vista.txtfecha.getText();

//        La funcion getselecteditem devuelve un object por eso debemos hacer un casting o refundicion
        this.forPago = (String) this.vista.txtpago.getSelectedItem();

        this.moneda = (String) this.vista.txtmoneda.getSelectedItem();

//        --------------------------------------------------------------
        this.cliente = this.vista.txtcliente.getText();

        this.Ncompro = this.vista.ntxtcomprobante.getText();

        this.nomPro = this.vista.txtNomProducto.getText();

        this.codPro = this.vista.txtCodProducto.getText();

        this.labPro = this.vista.txtLabProducto.getText();

        this.stockPro = this.vista.txtStock.getText();

        this.desPro = this.vista.txtDescripcion.getText();

        this.prePro = this.vista.txtPrecio.getText();

        this.cantPro = (String) this.vista.txtCantidad.getSelectedItem();

    }

    public void limpiarDatos() {

//        this.vista.txtcliente.setText("");
//        this.vista.ntxtcomprobante.setText(this.vista.cadenaAleatoria());
        this.vista.txtNomProducto.setText("");

        this.vista.txtCodProducto.setText("");

        this.vista.txtLabProducto.setText("");

        this.vista.txtStock.setText("");

        this.vista.txtDescripcion.setText("");

        this.vista.txtPrecio.setText("");

        this.vista.txtCantidad.removeAllItems();

    }

    public boolean validarText() {

        capturarDatos();

        if (this.usuario.equals("") || this.fecha.equals("") || this.cliente.equals("") || this.Ncompro.equals("") || this.nomPro.equals("") || this.codPro.equals("") || this.labPro.equals("") || this.stockPro.equals("") || this.desPro.equals("") || this.prePro.equals("") || this.cantPro.equals("")) {

            return false;

        } else {

            return true;

        }

    }

    public void agregarArea() {

        this.arreglo.add("PRODUCTO: - " + this.vista.txtNomProducto.getText() + " - PRECIO: - " + this.vista.txtPrecio.getText() + " - CANTIDAD: - " + this.vista.txtCantidad.getSelectedItem() + "\n");

        Double cantidad = Double.parseDouble(this.cantPro);
        Double precio = Double.parseDouble(this.vista.txtPrecio.getText());
        Double tot = cantidad * precio;

//        Guardamos el precio en nuestro arraylist
        this.total.add(tot);

        mostrarArea();

    }

    public void mostrarArea() {

        for (String e : this.arreglo) {
            this.vista.area.setText(this.vista.area.getText() + e);
        }

    }

    public void total() {

        for (Double e : this.total) {
            System.out.println(e);
            this.TFinal += e;

        }

    }

//    Metodos sobrecargados de las interfaces
    @Override
    public void actionPerformed(ActionEvent ae) {

        //        Con eso capturamos el objeto que llama a este evento
        Object press = ae.getSource();

        if (press == this.vista.agregar) {

            if (validarText()) {

                this.vista.area.setText("");

                agregarArea();

//                Para mostrar el total actual en la cajita total
                Double calc = 0.0;
                for (Double e : this.total) {
                    calc += e;
                }
                this.vista.total.setText("" + calc);

//                -----------------
                actualizarFilas();

                limpiarDatos();

            } else {

                JOptionPane.showMessageDialog(null, "Rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } else if (press == this.vista.eliminar) {

            if (this.vista.area.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "No hay nada que eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                this.vista.area.setText("");

                this.arreglo.remove(this.arreglo.size() - 1);

                this.total.remove(this.total.size() - 1);

                mostrarArea();
            }

        } else if (press == this.vista.realizarVenta) {

            total();
            
            registrarFactura();

            registrar();

            this.arreglo.clear();

            this.total.clear();

            limpiarDatos();

            this.vista.txtcliente.setText("");
            this.vista.ntxtcomprobante.setText(this.vista.cadenaAleatoria());

            System.out.println(this.TFinal);

        } else if (press == this.vista.limpiar) {

            limpiarDatos();

            this.arreglo.clear();

            this.vista.area.setText("");

        }

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

//        Antes de agregar removemos todos lo items si no se van a concatenar     
        this.vista.txtCantidad.removeAllItems();

//        Capturamos el stock de la columna tabla, lo casteamos a int, el for evaluara del hasta ese numero y lo agregara al combo
        for (int i = 1; i <= Integer.parseInt(this.vista.model.getValueAt(filaSeleccionada, 4).toString()); i++) {
//            Agregando las comillas vacias antes de un entero se convierte en string
            this.vista.txtCantidad.addItem(" " + i);

        }

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
