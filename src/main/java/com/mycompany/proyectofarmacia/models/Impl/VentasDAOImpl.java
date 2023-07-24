/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.Impl;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.VentasDAO;
import com.mycompany.proyectofarmacia.models.DTO.VentasDTO;
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
public class VentasDAOImpl implements VentasDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT * FROM ventas";
    private static final String SQL_INSERT = "INSERT INTO ventas(id_factura, id_farmaceutico, id_pago, fecha, moneda, nom_cliente, total) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE ventas SET id_factura = ?,id_farmaceutico = ?,id_pago = ?,fecha = ?,moneda = ?,nom_cliente = ?,total = ? WHERE id_venta = ?";
    private static final String SQL_DELETE = "DELETE FROM ventas WHERE id_venta =?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM ventas WHERE id_venta=?";

    public VentasDAOImpl() {
    }

    public VentasDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    
    
    @Override
    public int insert(VentasDTO venta) throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, venta.getIdFactura());
            stmt.setInt(2, venta.getIdFarmaceutico());
            stmt.setInt(3, venta.getIdPago());
            stmt.setString(4, venta.getFecha());
            stmt.setString(5, venta.getMoneda());
            stmt.setString(6, venta.getNomCliente());
            stmt.setDouble(7, venta.getTotal());

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
    public int update(VentasDTO venta) throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setInt(1, venta.getIdFactura());
            stmt.setInt(2, venta.getIdFarmaceutico());
            stmt.setInt(3, venta.getIdPago());
            stmt.setString(4, venta.getFecha());
            stmt.setString(5, venta.getMoneda());
            stmt.setString(6, venta.getNomCliente());
            stmt.setDouble(7, venta.getTotal());
            stmt.setInt(8, venta.getIdVenta());

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
    public int delete(VentasDTO venta) throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, venta.getIdVenta());

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
    public List<VentasDTO> select() throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;

        VentasDTO venta = null;

        List<VentasDTO> ventas = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int idVenta = rs.getInt("id_venta");
                int idFactura = rs.getInt("id_factura");
                int idFarmaceutico = rs.getInt("id_farmaceutico");
                int idPago = rs.getInt("id_pago");
                String fecha = rs.getString("fecha");
                String moneda = rs.getString("moneda");
                String nomCliente = rs.getString("nom_cliente");
                Double total = rs.getDouble("total");

                venta = new VentasDTO(idVenta, idFactura, idFarmaceutico, idPago, fecha, moneda, nomCliente, total);

                ventas.add(venta);

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

        return ventas;

    }

    @Override
    public VentasDTO selectById(VentasDTO venta) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, venta.getIdVenta());
            rs = stmt.executeQuery();
            rs.next();//Nos posicionamos en el primer registro devuelto

            int idFactura = rs.getInt("id_factura");
            int idFarmaceutico = rs.getInt("id_farmaceutico");
            int idPago = rs.getInt("id_pago");
            String fecha = rs.getString("fecha");
            String moneda = rs.getString("moneda");
            String nomCliente = rs.getString("nom_cliente");
            Double total = rs.getDouble("total");

            venta.setIdFactura(idFactura);
            venta.setIdFarmaceutico(idFarmaceutico);
            venta.setIdPago(idPago);
            venta.setFecha(fecha);
            venta.setMoneda(moneda);
            venta.setNomCliente(nomCliente);
            venta.setTotal(total);

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

        return venta;

    }

}
