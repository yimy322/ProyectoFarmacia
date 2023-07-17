/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.DAO;

import com.mycompany.proyectofarmacia.models.DTO.ProductoDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yimy
 */
public interface ProductoDAO {
    
    int insert(ProductoDTO producto) throws SQLException;
    int update(ProductoDTO producto) throws SQLException;
    int delete(ProductoDTO producto) throws SQLException;
    List<ProductoDTO> select() throws SQLException;
    ProductoDTO selectById(ProductoDTO producto) throws SQLException;
    
}
