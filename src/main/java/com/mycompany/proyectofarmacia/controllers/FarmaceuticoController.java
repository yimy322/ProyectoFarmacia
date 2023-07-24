/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.controllers;

import static com.mycompany.proyectofarmacia.controllers.LoginController.NAME;
import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.FarmaceuticoDAO;
import com.mycompany.proyectofarmacia.models.DTO.FarmaceuticoDTO;
import com.mycompany.proyectofarmacia.models.Impl.FarmaceuticoDAOImpl;
import com.mycompany.proyectofarmacia.views.PanelFarmaceuticos;
import com.toedter.calendar.JDateChooser;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author yimy
 */
public class FarmaceuticoController implements ActionListener, MouseListener{

    private PanelFarmaceuticos vista;

    private String nombre, apellido, contra, direccion, nacionalidad;

    private String idE, nombreE, apellidoE, fechaE, edadE, direccionE, nacionalidadE, contrasenaE;

    private Date fecha;

    private int id;

    private String capContra;

    public FarmaceuticoController(PanelFarmaceuticos vista) {

        this.vista = vista;

//        Añadimos accion el jbutton
        this.vista.btnRegistrar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.addExcel.addActionListener(this);

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
            FarmaceuticoDAO farmaceuticoDao = new FarmaceuticoDAOImpl(conexion);

            //aca se hace las consultas
            capturarDatos();

            FarmaceuticoDTO farmaceutico = new FarmaceuticoDTO(this.nombre, this.apellido, getFecha(this.vista.jd), getEdad(), this.direccion, this.nacionalidad, this.contra);

            farmaceuticoDao.insert(farmaceutico);

            JOptionPane.showMessageDialog(null, "Farmaceutico registrado correctamente", "Tabla Farmaceuticos", JOptionPane.INFORMATION_MESSAGE);

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
            FarmaceuticoDAO farmaceuticoDao = new FarmaceuticoDAOImpl(conexion);

            //aca se hace las consultas
            capturarDatosE();

            FarmaceuticoDTO farmaceutico = new FarmaceuticoDTO(this.nombreE, this.apellidoE, this.fechaE, Integer.parseInt(this.edadE), this.direccionE, this.nacionalidadE, this.contrasenaE, Integer.parseInt(this.idE));

            farmaceuticoDao.update(farmaceutico);

            JOptionPane.showMessageDialog(null, "Farmaceutico actualizado correctamente", "Tabla Farmaceuticos", JOptionPane.INFORMATION_MESSAGE);

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
            FarmaceuticoDAO farmaceuticoDao = new FarmaceuticoDAOImpl(conexion);

            //aca se hace las consultas
            capturarDatosE();
//
            FarmaceuticoDTO farmaceutico = new FarmaceuticoDTO(Integer.parseInt(this.idE));

            farmaceuticoDao.delete(farmaceutico);

            JOptionPane.showMessageDialog(null, "Farmaceutico Eliminado correctamente", "Tabla Farmaceutico", JOptionPane.INFORMATION_MESSAGE);

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
            FarmaceuticoDAO farmaceuticoDao = new FarmaceuticoDAOImpl(conexion);

            //aca se hace las consultas
//            Antes de cargar los datos, vamos a borrar los anteriores para que no se repita
            for (int j = 0; j < this.vista.jt.getRowCount(); j++) {
                this.vista.model.removeRow(j);
                j -= 1;
            }

            List<FarmaceuticoDTO> farmaceuticos = farmaceuticoDao.select();

            Object ob[] = new Object[8];
            for (int i = 0; i < farmaceuticos.size(); i++) {
                ob[0] = farmaceuticos.get(i).getIdFarmaceutico();
                ob[1] = farmaceuticos.get(i).getNombre();
                ob[2] = farmaceuticos.get(i).getApellido();
                ob[3] = farmaceuticos.get(i).getFecNac();
                ob[4] = farmaceuticos.get(i).getEdad();
                ob[5] = farmaceuticos.get(i).getDireccion();
                ob[6] = farmaceuticos.get(i).getNacionalidad();
                ob[7] = farmaceuticos.get(i).getContrasena();
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

//    Captura de datos y validacion
    public String getFecha(JDateChooser jd) {
        SimpleDateFormat Formato = new SimpleDateFormat("yyyy-MM-dd");
        if (jd.getDate() != null) {
            return Formato.format(this.vista.jd.getDate());
        } else {
            return null;
        }
    }
//    Capturamos solo el año del jdatechooser

    public int getEdad() {

        Calendar fecha = new GregorianCalendar();

        int anoN = this.vista.jd.getCalendar().get(Calendar.YEAR);

        int anoA = fecha.get(Calendar.YEAR);

        int edad = anoA - anoN;

        return edad;

    }

    public void capturarDatos() {

        this.nombre = this.vista.txtNombre.getText();

        this.apellido = this.vista.txtApellido.getText();

        this.contra = this.vista.txtpass.getText();

        this.direccion = this.vista.txtDireccion.getText();

        this.nacionalidad = this.vista.txtNacionalidad.getText();

    }

    public void limpiarDatos() {

        this.vista.txtNombre.setText("");

        this.vista.txtApellido.setText("");

        this.vista.txtpass.setText("");

        this.vista.txtDireccion.setText("");

        this.vista.txtNacionalidad.setText("");

    }

    public boolean validarText() {

        capturarDatos();

        if (this.nombre.equals("") || this.apellido.equals("") || this.contra.equals("") || this.direccion.equals("") || this.nacionalidad.equals("") || getFecha(this.vista.jd) == null) {

            return false;

        } else {

            return true;

        }

    }

//    Lo mismo que arriba pero con las cajas de editar
    public void capturarDatosE() {

        this.idE = this.vista.Eid.getText();

        this.nombreE = this.vista.EtxtNombre.getText();

        this.apellidoE = this.vista.EtxtApellido.getText();

        this.fechaE = this.vista.EfechaNac.getText();

        this.edadE = this.vista.EtxtEdad.getText();

        this.direccionE = this.vista.EtxtDireccion.getText();

        this.nacionalidadE = this.vista.EtxtNacionalidad.getText();

        this.contrasenaE = this.vista.Etxtpass.getText();

    }

    public void limpiarDatosE() {

        this.vista.Eid.setText("");

        this.vista.EtxtNombre.setText("");

        this.vista.EtxtApellido.setText("");

        this.vista.EfechaNac.setText("");

        this.vista.EtxtEdad.setText("");

        this.vista.EtxtDireccion.setText("");

        this.vista.EtxtNacionalidad.setText("");

        this.vista.Etxtpass.setText("");

    }

    public boolean validarTextE() {

        capturarDatosE();

        if (this.nombreE.equals("") || this.apellidoE.equals("") || this.direccionE.equals("") || this.nacionalidadE.equals("") || this.contrasenaE.equals("")) {

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

            if (validarText()) {

                registrar();

                actualizarFilas();

                limpiarDatos();

            } else {

                JOptionPane.showMessageDialog(null, "Rellenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);

            }

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

    @Override
    public void mouseClicked(MouseEvent me) {

//        Aca capturamos la fila seleccionada
        int filaSeleccionada = this.vista.jt.getSelectedRow();

//        Aca pasamos los valores a las cajas
        this.vista.Eid.setText(this.vista.model.getValueAt(filaSeleccionada, 0).toString());

        this.vista.EtxtNombre.setText(this.vista.model.getValueAt(filaSeleccionada, 1).toString());

        this.vista.EtxtApellido.setText(this.vista.model.getValueAt(filaSeleccionada, 2).toString());

        this.vista.EfechaNac.setText(this.vista.model.getValueAt(filaSeleccionada, 3).toString());

        this.vista.EtxtEdad.setText(this.vista.model.getValueAt(filaSeleccionada, 4).toString());

        this.vista.EtxtDireccion.setText(this.vista.model.getValueAt(filaSeleccionada, 5).toString());

        this.vista.EtxtNacionalidad.setText(this.vista.model.getValueAt(filaSeleccionada, 6).toString());

        this.vista.Etxtpass.setText(this.vista.model.getValueAt(filaSeleccionada, 7).toString());

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
//            Esta clase permite abrir e imprimir ficheros
            Desktop.getDesktop().open(ruta);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

}
