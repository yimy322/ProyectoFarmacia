/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.DAO;

import com.mycompany.proyectofarmacia.models.DTO.FacturaDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yimy
 */
public interface FacturaDAO {
    
    int insert(FacturaDTO factura) throws SQLException;
    List<FacturaDTO> select() throws SQLException;
    FacturaDTO selectById(FacturaDTO factura) throws SQLException;
    
}
