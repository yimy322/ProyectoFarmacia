/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.views;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.ProductoDAO;
import com.mycompany.proyectofarmacia.models.DTO.ProductoDTO;
import com.mycompany.proyectofarmacia.models.Impl.ProductoDAOImpl;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yimy
 */
public class PanelVentas extends JPanel {

    JPanel centro, izquierda;

    JLabel usuario, fecha, pago, moneda, cliente, comprobante, ncomprobante;

//    Labels del producto
    JLabel codProducto, nomProducto, labProducto, stock, descripcion, cantidad, busProducto, precio;

//    ------
    public JTextField txtusuario, txtfecha, txtcliente, ntxtcomprobante;

    public JComboBox txtmoneda, txtpago, txtCantidad;

//    TextField del producto
    public JTextField txtCodProducto, txtNomProducto, txtLabProducto, txtStock, txtDescripcion, txtPrecio;

//    ------    
//    Buttons del producto
    public JButton agregar, eliminar;

//    ------  
    public JButton realizarVenta, limpiar;

    public JTable jt;

    public DefaultTableModel model;

    public JTextPane area;

    Box cajavertical = Box.createVerticalBox();

    Box caja1 = Box.createHorizontalBox();

    Box caja2 = Box.createHorizontalBox();

    Box cajaProductos = Box.createHorizontalBox();

    Box cajaTabla = Box.createHorizontalBox();

    Box caja3 = Box.createHorizontalBox();

    Box cajaArea = Box.createHorizontalBox();

//    Jtext del total
    public JTextField total;
    
    public JLabel labelSim;

    public PanelVentas() {

        setLayout(new BorderLayout());

        //---------LAMINA CENTRAL----------------
        centro = new JPanel();

        centro.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true));

        //-----CREACION DE LBL Y TXT----------
        cajavertical.add(Box.createVerticalStrut(2));

        usuario = new JLabel("Usuario");

        txtusuario = new JTextField(11);

        txtusuario.setEditable(false);

        caja1.add(usuario);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtusuario);

        caja1.add(Box.createHorizontalStrut(15));

        fecha = new JLabel("Fecha");

        txtfecha = new JTextField(11);

        txtfecha.setEditable(false);

        caja1.add(fecha);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtfecha);

        caja1.add(Box.createHorizontalStrut(15));

        pago = new JLabel("Forma de pago");

        txtpago = new JComboBox();

        caja1.add(pago);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtpago);

        caja1.add(Box.createHorizontalStrut(15));

        moneda = new JLabel("Moneda");

        txtmoneda = new JComboBox();

        txtmoneda.addItem("Soles");
        txtmoneda.addItem("Dolares");

        //Para que cambie el simbolo en la caja
        txtmoneda.addItemListener(new accionCombo());

        caja1.add(moneda);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtmoneda);

        caja1.add(Box.createHorizontalStrut(15));

        cliente = new JLabel("Cliente");

        txtcliente = new JTextField(11);

        caja1.add(cliente);

        caja1.add(Box.createHorizontalStrut(5));

        caja1.add(txtcliente);

        caja1.add(Box.createHorizontalStrut(15));

        cajavertical.add(caja1);

        caja1.add(Box.createVerticalStrut(5));

        ncomprobante = new JLabel("Nº de comprobante");

        ntxtcomprobante = new JTextField(11);

        ntxtcomprobante.setText(cadenaAleatoria());

        ntxtcomprobante.setEditable(false);

        caja2.add(ncomprobante);

        caja2.add(Box.createHorizontalStrut(5));

        caja2.add(ntxtcomprobante);

        caja2.add(Box.createHorizontalStrut(15));

        cajavertical.add(caja2);

        caja2.add(Box.createHorizontalStrut(750));

//        Caja productos
        cajaProductos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Producto"));

        nomProducto = new JLabel("Nombre");

        txtNomProducto = new JTextField(9);

        cajaProductos.add(nomProducto);

        cajaProductos.add(Box.createHorizontalStrut(5));

        cajaProductos.add(txtNomProducto);

        cajaProductos.add(Box.createHorizontalStrut(10));

        busProducto = new JLabel(new ImageIcon("src/main/java/resources/buscar.png"));

        cajaProductos.add(busProducto);

        cajaProductos.add(Box.createHorizontalStrut(15));

        codProducto = new JLabel("Codigo");

        txtCodProducto = new JTextField(9);

        txtCodProducto.setEditable(false);

        cajaProductos.add(codProducto);

        cajaProductos.add(Box.createHorizontalStrut(5));

        cajaProductos.add(txtCodProducto);

        cajaProductos.add(Box.createHorizontalStrut(10));

        labProducto = new JLabel("Laboratorio");

        txtLabProducto = new JTextField(9);

        txtLabProducto.setEditable(false);

        cajaProductos.add(labProducto);

        cajaProductos.add(Box.createHorizontalStrut(5));

        cajaProductos.add(txtLabProducto);

        cajaProductos.add(Box.createHorizontalStrut(10));

        stock = new JLabel("Stock");

        txtStock = new JTextField(9);

        txtStock.setEditable(false);

        cajaProductos.add(stock);

        cajaProductos.add(Box.createHorizontalStrut(5));

        cajaProductos.add(txtStock);

        cajaProductos.add(Box.createHorizontalStrut(10));

        descripcion = new JLabel("Descripcion");

        txtDescripcion = new JTextField(16);

        txtDescripcion.setEditable(false);

        cajaProductos.add(descripcion);

        cajaProductos.add(Box.createHorizontalStrut(5));

        cajaProductos.add(txtDescripcion);

        cajaProductos.add(Box.createHorizontalStrut(10));

        cajavertical.add(cajaProductos);

        cajavertical.add(Box.createVerticalStrut(10));

//        Tabla
        crearTabla();

//        cajaTabla.setBorder(BorderFactory.createLineBorder(Color.black));
        cajavertical.add(cajaTabla);

        cajavertical.add(Box.createVerticalStrut(10));

//        Cantidad, precio y botones
        precio = new JLabel("Precio");

        txtPrecio = new JTextField(10);

        txtPrecio.setEditable(false);

        caja3.add(precio);

        caja3.add(Box.createHorizontalStrut(5));

        caja3.add(txtPrecio);

        caja3.add(Box.createHorizontalStrut(15));

        cantidad = new JLabel("Cantidad");

        txtCantidad = new JComboBox();

        caja3.add(cantidad);

        caja3.add(Box.createHorizontalStrut(5));

        caja3.add(txtCantidad);

        caja3.add(Box.createHorizontalStrut(15));

        //Validamos la entrada de solo numeros a algunas cajas
        txtCantidad.addKeyListener(new eventoTec());

        agregar = new JButton("Agregar", new ImageIcon("src/main/java/resources/agregar.png"));

        caja3.add(agregar);

        caja3.add(Box.createHorizontalStrut(15));

        eliminar = new JButton("Eliminar ultimo", new ImageIcon("src/main/java/resources/eliminar.png"));

        caja3.add(eliminar);

        caja3.add(Box.createHorizontalStrut(15));
        
        labelSim = new JLabel("S/.");
        
        caja3.add(labelSim);

        total = new JTextField(8);

        total.setText("0.00");

        total.setEditable(false);

        caja3.add(total);

        caja3.add(Box.createHorizontalStrut(15));

        cajavertical.add(caja3);

        cajavertical.add(Box.createVerticalStrut(10));

//        Creamos el pane llamando a la funcion correspondiente
        area = new JTextPane();

        area.setEditable(false);

        area.setPreferredSize(new Dimension(1049, 200));

        JScrollPane scroll = new JScrollPane(area);

        cajavertical.add(scroll);

        cajavertical.add(Box.createVerticalStrut(10));

//        -------------------
        centro.add(cajavertical);

        centro.add(caja1);

        centro.add(caja2);

        centro.add(cajaProductos);

        centro.add(cajaTabla);

        centro.add(caja3);

        centro.add(scroll);

        add(centro, BorderLayout.CENTER);

        //-------LAMINA DERECHA------------------
        izquierda = new JPanel();

        izquierda.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3, true));

        Box caja3 = Box.createVerticalBox();

        realizarVenta = new JButton(tamimage("src/main/java/resources/venta.png"));

        limpiar = new JButton(tamimage("src/main/java/resources/basura.png"));

//        Para que aparazca texto cuando nos ubicamos encima del button
        realizarVenta.setToolTipText("Realizar venta");

        limpiar.setToolTipText("Limpiar");

        caja3.add(Box.createVerticalStrut(180));

        caja3.add(realizarVenta);

        caja3.add(Box.createVerticalStrut(10));

        caja3.add(limpiar);

        izquierda.add(caja3);

        add(izquierda, BorderLayout.EAST);

        //---------------------------------------
    }

    public void crearTabla() {

        jt = new JTable();

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Codigo");
        model.addColumn("Laboratorio");
        model.addColumn("Stock");
        model.addColumn("Precio(S/.)");
        model.addColumn("Descripcion");

        jt.setModel(model);

        jt.setPreferredScrollableViewportSize(new Dimension(1050, 110));
        JScrollPane sp = new JScrollPane(jt);

        cajaTabla.add(sp);
        cajaTabla.add(Box.createVerticalStrut(10));

    }

//    Para ajustar el tamaño de una imagen
    public ImageIcon tamimage(String url) {

        ImageIcon icono = new ImageIcon(url);

        Image imagen = icono.getImage();

        Image conversion = imagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH);

        ImageIcon resultado = new ImageIcon(conversion);

        return resultado;

    }

    //    Para hallar un numero aleatorio
    public String cadenaAleatoria() {
        // El banco de caracteres
        String banco = "1234567890";
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

    //    Clase para los eventos de las teclas
    class eventoTec implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {

//            Capturamos la tecla que presionamos
            int key = ke.getKeyChar();

//            validamos
            if (((key < '0') || (key > '9')) && (key != '.')) {
//                Este metodo evita que se coloque la tecla presionada
                ke.consume();
            }

        }

        @Override
        public void keyPressed(KeyEvent ke) {

        }

        @Override
        public void keyReleased(KeyEvent ke) {

        }

    }

    class accionCombo implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent ie) {
            
            if (ie.getItem()=="Dolares") {
                labelSim.setText("$");
            }else{
                labelSim.setText("S/.");
            }
            
        }
        
    }

}
