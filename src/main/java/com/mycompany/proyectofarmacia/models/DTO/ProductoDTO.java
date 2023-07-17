/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.DTO;

/**
 *
 * @author yimy
 */
public class ProductoDTO {

    private int idProducto;
    private String nombre;
    private String codigo;
    private String laboratorio;
    private int stock;
    private Double precio;
    private String descripcion;

    public ProductoDTO() {
    }

    public ProductoDTO(int idProducto) {
        this.idProducto = idProducto;
    }

    public ProductoDTO(int idProducto, String nombre, String codigo, String laboratorio, int stock, Double precio, String descripcion) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.codigo = codigo;
        this.laboratorio = laboratorio;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public ProductoDTO(String nombre, String codigo, String laboratorio, int stock, Double precio, String descripcion) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.laboratorio = laboratorio;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
