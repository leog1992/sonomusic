/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Statement;

/**
 *
 * @author Dereck
 */
public class Cl_Albaran {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();

    private Integer id;
    private String fecha;
    private int origen;
    private int destino;
    private String usuario;
    private Integer numero;
    private Integer serie;
    private int tipo_documento;

    public Cl_Albaran() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int destino) {
        this.destino = destino;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public int getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(int tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public int i_traslado() {
        int resultado = -1;
        try {
            Statement st = con.conexion();
            String i_traslado = "insert into traslado values (null, '" + fecha + "', '" + origen + "', '" + destino + "', '" + usuario + "', '" + tipo_documento + "','" + numero + "', '" + serie + "','0')";
            resultado = con.actualiza(st, i_traslado);
            con.cerrar(st);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return resultado;
    }

}
