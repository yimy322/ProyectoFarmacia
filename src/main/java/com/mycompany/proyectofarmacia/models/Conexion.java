/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models;
import java.sql.*;
import javax.sql.DataSource;
//Esta libreria permite hacer el pool de conexiones
import org.apache.commons.dbcp2.BasicDataSource;
/**
 *
 * @author yimy
 */
public class Conexion {
    
//    Las variables estaticas pueden ser accedidas desde otras clases sin necesidad de crear un objeto de dicha clase
//    Al indicar que una variables es final significa que no podra ser modificada despues
    
//    Cadena de conexion con mysql
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/farmacia_db?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
//    Aca especificamos el usuario
    private static final String JDBC_USER = "root";
//    Aca la contraseña de mysql
    private static final String JDBC_PASSWORD = "123456";
    
//    Pool de conexiones
    
    public static DataSource getDataSource(){
        
//        Al instanciar esta clase crea un pool de conexiones
        BasicDataSource ds = new BasicDataSource();
        
//        Le pasamos los valores para que realice la conexion
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASSWORD);

//        Como ya habra conexiones abiertas sera mas rapido la respuesta
//        Definimos el tamaño inicial del pool de conexiones
        ds.setInitialSize(5);
        
        return ds;
        
    } 
    
//    Solicita un objeto de tipo conexion
    public static Connection getConnection() throws SQLException{
        
        return getDataSource().getConnection();
        
    }
    
//    Metodos para cerrar
    
//    El metopdo resultset nos permite utilizar metodos para obtener datos de columnas correspondientes a una fila
    public static void close(ResultSet rs) throws SQLException{
        
        rs.close();
        
    }
    
//    Statement sirve para procesar una sentencia sql y obtener sus resultados
    public static void close(Statement smtm) throws SQLException{
        
        smtm.close();
        
    }
    
//    PreparedStatement nos permite utilizar metodos para establecer parametros 
    public static void close(PreparedStatement smtm) throws SQLException{
        
        smtm.close();
        
    }
    
//    Connection, se encarga de hacer la conexion
    public static void close(Connection conn) throws SQLException{
        
        conn.close();
        
    }
}
