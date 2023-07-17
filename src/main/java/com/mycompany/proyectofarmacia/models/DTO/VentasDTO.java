/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.DTO;

/**
 *
 * @author yimy
 */
public class VentasDTO {

    private int idVenta;
    private int idFactura;
    private int idProducto;
    private int idFarmaceutico;
    private int idPago;
    private String fecha;
    private String moneda;
    private String nomCliente;
    private Double total;

    public VentasDTO() {
    }

    public VentasDTO(int idVenta) {
        this.idVenta = idVenta;
    }

    public VentasDTO(int idVenta, int idFactura, int idProducto, int idFarmaceutico, int idPago, String fecha, String moneda, String nomCliente, Double total) {
        this.idVenta = idVenta;
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.idFarmaceutico = idFarmaceutico;
        this.idPago = idPago;
        this.fecha = fecha;
        this.moneda = moneda;
        this.nomCliente = nomCliente;
        this.total = total;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdFarmaceutico() {
        return idFarmaceutico;
    }

    public void setIdFarmaceutico(int idFarmaceutico) {
        this.idFarmaceutico = idFarmaceutico;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
