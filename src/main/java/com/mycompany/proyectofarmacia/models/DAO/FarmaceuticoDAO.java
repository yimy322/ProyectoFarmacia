/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.DAO;

import com.mycompany.proyectofarmacia.models.DTO.FarmaceuticoDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author yimy
 */
public interface FarmaceuticoDAO {
    
    int insert(FarmaceuticoDTO farmaceutico) throws SQLException;
    int update(FarmaceuticoDTO farmaceutico) throws SQLException;
    int delete(FarmaceuticoDTO farmaceutico) throws SQLException;
    List<FarmaceuticoDTO> select() throws SQLException;
    List<FarmaceuticoDTO> selectAll() throws SQLException;
    FarmaceuticoDTO selectById(FarmaceuticoDTO farmaceutico) throws SQLException;
    
}
