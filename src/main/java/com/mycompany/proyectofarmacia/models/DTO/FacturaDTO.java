/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.DTO;

/**
 *
 * @author yimy
 */
public class FacturaDTO {

    private int idFactura;
    private String fecha;
    private String nomCliente;
    private String codigo;

    public FacturaDTO() {
    }

    public FacturaDTO(String codigo) {
        this.codigo = codigo;
    }

    public FacturaDTO(int idFactura, String fecha, String nomCliente, String codigo) {
        this.idFactura = idFactura;
        this.fecha = fecha;
        this.nomCliente = nomCliente;
        this.codigo = codigo;
    }

    public FacturaDTO(String fecha, String nomCliente, String codigo) {
        this.fecha = fecha;
        this.nomCliente = nomCliente;
        this.codigo = codigo;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
