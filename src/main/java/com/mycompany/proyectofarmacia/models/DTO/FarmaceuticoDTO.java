/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofarmacia.models.DTO;

/**
 *
 * @author yimy
 */
public class FarmaceuticoDTO {

    private int idFarmaceutico;
    private String nombre;
    private String apellido;
    private String fecNac;
    private int edad;
    private String direccion;
    private String nacionalidad;
    private String contrasena;

    public FarmaceuticoDTO() {
    }

    public FarmaceuticoDTO(int idFarmaceutico) {
        this.idFarmaceutico = idFarmaceutico;
    }

    public FarmaceuticoDTO(int idFarmaceutico, String nombre, String apellido, String fecNac, int edad, String direccion, String nacionalidad) {
        this.idFarmaceutico = idFarmaceutico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecNac = fecNac;
        this.edad = edad;
        this.direccion = direccion;
        this.nacionalidad = nacionalidad;
    }

    public FarmaceuticoDTO(int idFarmaceutico, String nombre, String apellido, String fecNac, int edad, String direccion, String nacionalidad, String contrasena) {
        this.idFarmaceutico = idFarmaceutico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecNac = fecNac;
        this.edad = edad;
        this.direccion = direccion;
        this.nacionalidad = nacionalidad;
        this.contrasena = contrasena;
    }

    public FarmaceuticoDTO(String nombre, String apellido, String fecNac, int edad, String direccion, String nacionalidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecNac = fecNac;
        this.edad = edad;
        this.direccion = direccion;
        this.nacionalidad = nacionalidad;
    }

    public FarmaceuticoDTO(String nombre, String apellido, String fecNac, int edad, String direccion, String nacionalidad, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecNac = fecNac;
        this.edad = edad;
        this.direccion = direccion;
        this.nacionalidad = nacionalidad;
        this.contrasena = contrasena;
    }
    
    public FarmaceuticoDTO(String nombre, String apellido, String fecNac, int edad, String direccion, String nacionalidad, String contrasena, int idFarmaceutico) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecNac = fecNac;
        this.edad = edad;
        this.direccion = direccion;
        this.nacionalidad = nacionalidad;
        this.contrasena = contrasena;
        this.idFarmaceutico = idFarmaceutico;
    }

    public int getIdFarmaceutico() {
        return idFarmaceutico;
    }

    public void setIdFarmaceutico(int idFarmaceutico) {
        this.idFarmaceutico = idFarmaceutico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFecNac() {
        return fecNac;
    }

    public void setFecNac(String fecNac) {
        this.fecNac = fecNac;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
