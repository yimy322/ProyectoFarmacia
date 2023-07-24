/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.DTO;

/**
 *
 * @author yimy
 */
public class PagoDTO {

    private int idPago;
    private String tipo;

    public PagoDTO() {
    }

    public PagoDTO(String tipo) {
        this.tipo = tipo;
    }

    public PagoDTO(int idPago) {
        this.idPago = idPago;
    }

    public PagoDTO(int idPago, String tipo) {
        this.idPago = idPago;
        this.tipo = tipo;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
