/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.DAO;

import com.mycompany.proyectofarmacia.models.DTO.PagoDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yimy
 */
public interface PagoDAO {
    
    List<PagoDTO> select() throws SQLException;
    PagoDTO selectById(PagoDTO pago) throws SQLException;
    
}
