/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Eduardo Varela
 */
public class CuentasER {
    private String ventas, costoVentas, gastosVentas, gastosAdmin;
    private String nombreEmpresa, periodo;

    public CuentasER() {
    }
    
    public CuentasER(String ventas, String costoVentas, String gastosVentas, String gastosAdmin, String nombreEmpresa, String periodo) {
        this.ventas = ventas;
        this.costoVentas = costoVentas;
        this.gastosVentas = gastosVentas;
        this.gastosAdmin = gastosAdmin;
        this.nombreEmpresa = nombreEmpresa;
        this.periodo = periodo;
    }

    public String getVentas() {
        return ventas;
    }

    public void setVentas(String ventas) {
        this.ventas = ventas;
    }

    public String getCostoVentas() {
        return costoVentas;
    }

    public void setCostoVentas(String costoVentas) {
        this.costoVentas = costoVentas;
    }

    public String getGastosVentas() {
        return gastosVentas;
    }

    public void setGastosVentas(String gastosVentas) {
        this.gastosVentas = gastosVentas;
    }

    public String getGastosAdmin() {
        return gastosAdmin;
    }

    public void setGastosAdmin(String gastosAdmin) {
        this.gastosAdmin = gastosAdmin;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
