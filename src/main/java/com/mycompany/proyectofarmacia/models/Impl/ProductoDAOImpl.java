/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.Impl;

import com.mycompany.proyectofarmacia.models.Conexion;
import com.mycompany.proyectofarmacia.models.DAO.ProductoDAO;
import com.mycompany.proyectofarmacia.models.DTO.ProductoDTO;
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
public class ProductoDAOImpl implements ProductoDAO {

    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT * FROM productos";
    private static final String SQL_INSERT = "INSERT INTO productos(nombre, codigo, laboratorio, stock, precio, descripcion) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET nombre = ?,codigo = ?,laboratorio = ?,stock = ?,precio = ?,descripcion = ? WHERE id_producto = ?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE id_producto =?";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM productos WHERE nombre=?";

    public ProductoDAOImpl() {
    }

    public ProductoDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public int insert(ProductoDTO producto) throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCodigo());
            stmt.setString(3, producto.getLaboratorio());
            stmt.setInt(4, producto.getStock());
            stmt.setDouble(5, producto.getPrecio());
            stmt.setString(6, producto.getDescripcion());

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
    public int update(ProductoDTO producto) throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getCodigo());
            stmt.setString(3, producto.getLaboratorio());
            stmt.setInt(4, producto.getStock());
            stmt.setDouble(5, producto.getPrecio());
            stmt.setString(6, producto.getDescripcion());
            stmt.setInt(7, producto.getIdProducto());

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
    public int delete(ProductoDTO producto) throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        int registros = 0;

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, producto.getIdProducto());

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
    public List<ProductoDTO> select() throws SQLException {

        Connection conn = null;

        PreparedStatement stmt = null;

        ResultSet rs = null;

        ProductoDTO producto = null;

        List<ProductoDTO> productos = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {

                int idProducto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                String codigo = rs.getString("codigo");
                String laboratorio = rs.getString("laboratorio");
                int stock = rs.getInt("stock");
                Double precio = rs.getDouble("precio");
                String descripcion = rs.getString("descripcion");

                producto = new ProductoDTO(idProducto, nombre, codigo, laboratorio, stock, precio, descripcion);

                productos.add(producto);

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

        return productos;

    }

    @Override
    public ProductoDTO selectById(ProductoDTO producto) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setString(1, producto.getNombre());
            rs = stmt.executeQuery();
            rs.next();//Nos posicionamos en el primer registro devuelto

            int id = rs.getInt("id_producto");
            String nombre = rs.getString("nombre");
            String codigo = rs.getString("codigo");
            String laboratorio = rs.getString("laboratorio");
            int stock = rs.getInt("stock");
            Double precio = rs.getDouble("precio");
            String descripcion = rs.getString("descripcion");

            producto.setIdProducto(id);
            producto.setNombre(nombre);
            producto.setCodigo(codigo);
            producto.setLaboratorio(laboratorio);
            producto.setStock(stock);
            producto.setPrecio(precio);
            producto.setDescripcion(descripcion);

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

        return producto;

    }

}
