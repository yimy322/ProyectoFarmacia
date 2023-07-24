/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.Impl;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.FacturaDAO;
import com.mycompany.proyectofarmacia.models.DTO.FacturaDTO;
import com.mycompany.proyectofarmacia.models.DTO.PagoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yimy
 */
public class FacturaDAOImpl implements FacturaDAO {

//    Al implementar la interfaz tendremos que llamar a sus metodos
//    Para las transacciones
    private Connection conexionTransaccional;

//    Consultas
    private static final String SQL_SELECT = "SELECT * FROM facturas";
    private static final String SQL_INSERT = "INSERT INTO facturas(fecha, nom_cliente, codigo) VALUES (?,?,?)";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM facturas WHERE codigo=?";

    public FacturaDAOImpl() {
    }

    public FacturaDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public int insert(FacturaDTO factura) throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, factura.getFecha());
            stmt.setString(2, factura.getNomCliente());
            stmt.setString(3, factura.getCodigo());

            registros = stmt.executeUpdate();

        } finally {

            try {
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {

                    Conexion.close(conn);

                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }

        return registros;

    }

    @Override
    public List<FacturaDTO> select() throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;

        FacturaDTO factura = null;

        List<FacturaDTO> facturas = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int idFactura = rs.getInt("id_factura");
                String fecha = rs.getString("fecha");
                String nomCliente = rs.getString("nom_cliente");
                String codigo = rs.getString("codigo");

                factura = new FacturaDTO(idFactura, fecha, nomCliente, codigo);

                facturas.add(factura);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                if (this.conexionTransaccional == null) {

                    Conexion.close(conn);

                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }

        return facturas;

    }

    @Override
    public FacturaDTO selectById(FacturaDTO factura) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setString(1, factura.getCodigo());
            rs = stmt.executeQuery();
            rs.next();//Nos posicionamos en el primer registro devuelto
            
            int id = rs.getInt("id_factura");
            String fecha = rs.getString("fecha");
            String nomCliente = rs.getString("nom_cliente");
            String codigo = rs.getString("codigo");

            factura.setIdFactura(id);
            factura.setFecha(fecha);
            factura.setNomCliente(nomCliente);
            factura.setCodigo(codigo);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }

        return factura;

    }

}
