/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.Impl;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.PagoDAO;
import com.mycompany.proyectofarmacia.models.DTO.PagoDTO;
import java.sql.*;
import java.util.*;

/**
 *
 * @author yimy
 */
public class PagoDAOImpl implements PagoDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT * FROM pagos";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM pagos WHERE id_pago=?";

    public PagoDAOImpl() {
    }

    public PagoDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<PagoDTO> select() throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;

        PagoDTO pago = null;

        List<PagoDTO> pagos = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int idPago = rs.getInt("id_pago");
                String tipo = rs.getString("tipo");

                pago = new PagoDTO(idPago, tipo);

                pagos.add(pago);

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

        return pagos;

    }

    @Override
    public PagoDTO selectById(PagoDTO pago) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, pago.getIdPago());
            rs = stmt.executeQuery();
            rs.next();//Nos posicionamos en el primer registro devuelto

            String tipo = rs.getString("tipo");

            pago.setTipo(tipo);

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

        return pago;

    }

}
