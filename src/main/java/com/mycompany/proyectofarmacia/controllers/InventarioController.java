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
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author yimy
 */
public class InventarioController implements ActionListener, MouseListener {

    private PanelInventario vista;

    private String nombre, codigo, laboratorio, stock, descripcion, precio;

    private String id, nombreE, codigoE, laboratorioE, stockE, descripcionE, precioE;

//    La creacion de este objeto recibira una instancia de la clase Login
    public InventarioController(PanelInventario vista) {

        this.vista = vista;

//        Les damos acciones a los JButtons
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.addExcel.addActionListener(this);

//        Cargamos los datos en la tabla
        actualizarFilas();

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

//    Para actualizar los datos
    public void actualizar() {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            ProductoDAO productoDao = new ProductoDAOImpl(conexion);

            //aca se hace las consultas
            capturarDatosE();

            ProductoDTO producto = new ProductoDTO(this.nombreE, this.codigoE, this.laboratorioE, Integer.parseInt(this.stockE), Double.parseDouble(this.precioE), this.descripcionE, Integer.parseInt(this.id));

            productoDao.update(producto);

            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente", "Tabla Productos", JOptionPane.INFORMATION_MESSAGE);

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

//    Para eliminar
    public void eliminar() {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            ProductoDAO productoDao = new ProductoDAOImpl(conexion);

            //aca se hace las consultas
            capturarDatosE();

            ProductoDTO producto = new ProductoDTO(Integer.parseInt(this.id));

            productoDao.delete(producto);

            JOptionPane.showMessageDialog(null, "Producto Eliminado correctamente", "Tabla Productos", JOptionPane.INFORMATION_MESSAGE);

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

    public void limpiarDatos() {

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

//    Lo mismo que arriba pero con las cajas de editar
    public void capturarDatosE() {

        this.id = this.vista.txtId.getText();

        this.nombreE = this.vista.txtNomEditar.getText();

        this.codigoE = this.vista.txtCodEditar.getText();

        this.laboratorioE = this.vista.txtLabEditar.getText();

        this.stockE = this.vista.txtEdiStock.getText();

        this.descripcionE = this.vista.txtEdiDescripcion.getText();

        this.precioE = this.vista.txtEdiPrecio.getText();

    }

    public void limpiarDatosE() {

        this.vista.txtId.setText("");

        this.vista.txtNomEditar.setText("");

        this.vista.txtCodEditar.setText(cadenaAleatoria());

        this.vista.txtLabEditar.setText("");

        this.vista.txtEdiStock.setText("");

        this.vista.txtEdiDescripcion.setText("");

        this.vista.txtEdiPrecio.setText("");

    }

    public boolean validarTextE() {

        capturarDatosE();

        if (this.nombreE.equals("") || this.laboratorioE.equals("") || this.stockE.equals("") || this.descripcionE.equals("") || this.precioE.equals("")) {

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

        } else if (press == this.vista.btnEditar) {

            if (validarTextE()) {

                actualizar();

                actualizarFilas();

                limpiarDatosE();

            } else {

                JOptionPane.showMessageDialog(null, "Rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);

            }

        } else if (press == this.vista.btnEliminar) {

            eliminar();

            actualizarFilas();

            limpiarDatosE();

        } else if (press == this.vista.addExcel) {

            try {

//                Instanciamos la clase
                JFileChooser chooser = new JFileChooser();
//                El metodo showsavediaolog es el que muestra el cuadro de dialogo
                chooser.showSaveDialog(this.vista);

//                capturamos el archivo
                File guardar = chooser.getSelectedFile();

//                Validamos que se haya capturado una ruta
                if (guardar != null) {

//                    le pasamos la extension al archivo, file guardara una cadena
                    guardar = new File(guardar.toString() + ".xlsx");
//                    Aca creamos un libro de excel
                    Workbook wb = new XSSFWorkbook();
//                    Creamos una hoja dentro el libro de excel
                    Sheet sheet = wb.createSheet("customer");

//                    Se crea una fila dentro de la hoja   
                    Row rowCol = sheet.createRow(0);
//                    Recoremos las columnas de nuestra tabla
                    for (int i = 0; i < this.vista.jt.getColumnCount(); i++) {
//                        Creamos las celdas dentro del excel
                        Cell cell = rowCol.createCell(i);
//                        Asignamos un valor a las celdas
                        cell.setCellValue(this.vista.jt.getColumnName(i));
                    }

                    for (int j = 0; j < this.vista.jt.getRowCount(); j++) {
                        Row row = sheet.createRow(j);
                        for (int k = 0; k < this.vista.jt.getColumnCount(); k++) {
                            Cell cell = row.createCell(k);

                            if (this.vista.jt.getValueAt(j, k) != null) {

                                cell.setCellValue(this.vista.jt.getValueAt(j, k).toString());

                            }

                        }
                    }
//                    Escribimos los resultados en un fichero excel
                    FileOutputStream out = new FileOutputStream(new File(guardar.toString()));
                    wb.write(out);
//                    Aca cerramos
                    wb.close();
                    out.close();

                    abrirFile(guardar.toString());

                } else {
//                    En caso de que no se haya guardado me mostrara un mensaje
                    JOptionPane.showMessageDialog(null, "Error al generar archivo", "Error", JOptionPane.ERROR_MESSAGE);

                }

            } catch (FileNotFoundException e) {
                System.out.println(e);
            } catch (IOException ie) {
                System.out.println(ie);
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

//    Cuando implementamos la interfaz tenemos que cargar sus metodos
    @Override
    public void mouseClicked(MouseEvent me) {

//        Aca capturamos la fila seleccionada
        int filaSeleccionada = this.vista.jt.getSelectedRow();

//        Aca pasamos los valores a las cajas
        this.vista.txtId.setText(this.vista.model.getValueAt(filaSeleccionada, 0).toString());

        this.vista.txtNomEditar.setText(this.vista.model.getValueAt(filaSeleccionada, 1).toString());

        this.vista.txtCodEditar.setText(this.vista.model.getValueAt(filaSeleccionada, 2).toString());

        this.vista.txtLabEditar.setText(this.vista.model.getValueAt(filaSeleccionada, 3).toString());

        this.vista.txtEdiStock.setText(this.vista.model.getValueAt(filaSeleccionada, 4).toString());

        this.vista.txtEdiPrecio.setText(this.vista.model.getValueAt(filaSeleccionada, 5).toString());

        this.vista.txtEdiDescripcion.setText(this.vista.model.getValueAt(filaSeleccionada, 6).toString());

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

//    Funcion para abrir el excel una vez lo hayamos guardado
    public void abrirFile(String file) {

        try {
            File ruta = new File(file);
//            Esto permite abrir e imprimir ficheros
            Desktop.getDesktop().open(ruta);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
