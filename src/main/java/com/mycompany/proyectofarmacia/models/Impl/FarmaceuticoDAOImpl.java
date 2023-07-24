/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.Impl;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.FarmaceuticoDAO;
import com.mycompany.proyectofarmacia.models.DTO.FarmaceuticoDTO;
import java.sql.*;
import java.util.*;

/**
 *
 * @author yimy
 */
public class FarmaceuticoDAOImpl implements FarmaceuticoDAO {

//    Al implementar la interfaz tendremos que llamar a sus metodos
//    Para las transacciones
    private Connection conexionTransaccional;

//    Consultas
    private static final String SQL_SELECT = "SELECT * FROM farmaceuticos";
    private static final String SQL_INSERT = "INSERT INTO farmaceuticos(nombre, apellido, fec_nac, edad, direccion, nacionalidad, contraseña) VALUES (?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE farmaceuticos SET nombre = ?,apellido = ?,fec_nac = ?,edad = ?,direccion = ?,nacionalidad = ?,contraseña = ? WHERE id_farmaceutico = ?";
    private static final String SQL_DELETE = "DELETE FROM farmaceuticos WHERE id_farmaceutico =?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM farmaceuticos WHERE nombre=?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM farmaceuticos";

    public FarmaceuticoDAOImpl() {
    }

    public FarmaceuticoDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

//    Damos funcionalidad a los metodos
    @Override
    public int insert(FarmaceuticoDTO farmaceutico) throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, farmaceutico.getNombre());
            stmt.setString(2, farmaceutico.getApellido());
            stmt.setString(3, farmaceutico.getFecNac());
            stmt.setInt(4, farmaceutico.getEdad());
            stmt.setString(5, farmaceutico.getDireccion());
            stmt.setString(6, farmaceutico.getNacionalidad());
            stmt.setString(7, farmaceutico.getContrasena());

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
    public int update(FarmaceuticoDTO farmaceutico) throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, farmaceutico.getNombre());
            stmt.setString(2, farmaceutico.getApellido());
            stmt.setString(3, farmaceutico.getFecNac());
            stmt.setInt(4, farmaceutico.getEdad());
            stmt.setString(5, farmaceutico.getDireccion());
            stmt.setString(6, farmaceutico.getNacionalidad());
            stmt.setString(7, farmaceutico.getContrasena());
            stmt.setInt(8, farmaceutico.getIdFarmaceutico());

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
    public int delete(FarmaceuticoDTO farmaceutico) throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, farmaceutico.getIdFarmaceutico());

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
    public List<FarmaceuticoDTO> select() throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;

        FarmaceuticoDTO farmaceutico = null;

        List<FarmaceuticoDTO> farmaceuticos = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int idFarmaceutico = rs.getInt("id_farmaceutico");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String fecNac = rs.getString("fec_nac");
                int edad = rs.getInt("edad");
                String direccion = rs.getString("direccion");
                String nacionalidad = rs.getString("nacionalidad");
                String contrasena = rs.getString("contraseña");

                farmaceutico = new FarmaceuticoDTO(idFarmaceutico, nombre, apellido, fecNac, edad, direccion, nacionalidad, contrasena);

                farmaceuticos.add(farmaceutico);

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

        return farmaceuticos;

    }

    @Override
    public FarmaceuticoDTO selectById(FarmaceuticoDTO farmaceutico) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setString(1, farmaceutico.getNombre());
            rs = stmt.executeQuery();
            rs.next();//Nos posicionamos en el primer registro devuelto

            int id = rs.getInt("id_farmaceutico");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String fecNac = rs.getString("fec_nac");
            int edad = rs.getInt("edad");
            String direccion = rs.getString("direccion");
            String nacionalidad = rs.getString("nacionalidad");

            farmaceutico.setIdFarmaceutico(id);
            farmaceutico.setNombre(nombre);
            farmaceutico.setApellido(apellido);
            farmaceutico.setFecNac(fecNac);
            farmaceutico.setEdad(edad);
            farmaceutico.setDireccion(direccion);
            farmaceutico.setNacionalidad(nacionalidad);

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

        return farmaceutico;

    }

    @Override
    public List<FarmaceuticoDTO> selectAll() throws SQLException {
        
        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;

        FarmaceuticoDTO farmaceutico = null;

        List<FarmaceuticoDTO> farmaceuticos = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int idFarmaceutico = rs.getInt("id_farmaceutico");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String fecNac = rs.getString("fec_nac");
                int edad = rs.getInt("edad");
                String direccion = rs.getString("direccion");
                String nacionalidad = rs.getString("nacionalidad");
                String contrasena = rs.getString("contraseña");

                farmaceutico = new FarmaceuticoDTO(idFarmaceutico, nombre, apellido, fecNac, edad, direccion, nacionalidad, contrasena);

                farmaceuticos.add(farmaceutico);

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

        return farmaceuticos;
        
    }

}
