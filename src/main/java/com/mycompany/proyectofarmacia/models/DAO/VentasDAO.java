/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.DAO;

import com.mycompany.proyectofarmacia.models.DTO.VentasDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yimy
 */
public interface VentasDAO {
    
    int insert(VentasDTO venta) throws SQLException;
    int update(VentasDTO venta) throws SQLException;
    int delete(VentasDTO venta) throws SQLException;
    List<VentasDTO> select() throws SQLException;
    VentasDTO selectById(VentasDTO venta) throws SQLException;
    
}
