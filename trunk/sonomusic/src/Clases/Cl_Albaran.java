/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Dereck
 */
public class Cl_Albaran {

    private Integer id;
    private String fecha;
    private String motivo;
    private String origen;
    private String destino;
    private String ruc_des;
    private String raz_soc_des;
    private Integer nro;
    private Integer ser;
    private String ruc_tran;
    private String raz_tran;
    private String marca;
    private String placa;
    private String brevete;
    private String chofer;
    private String constancia;

    public Cl_Albaran(Integer id, String fecha, String motivo, String origen, String destino, String ruc_des, String raz_soc_des, Integer nro, Integer ser) {
        this.id = id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.origen = origen;
        this.destino = destino;
        this.ruc_des = ruc_des;
        this.raz_soc_des = raz_soc_des;
        this.nro = nro;
        this.ser = ser;
    }

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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getRuc_des() {
        return ruc_des;
    }

    public void setRuc_des(String ruc_des) {
        this.ruc_des = ruc_des;
    }

    public String getRaz_soc_des() {
        return raz_soc_des;
    }

    public void setRaz_soc_des(String raz_soc_des) {
        this.raz_soc_des = raz_soc_des;
    }

    public Integer getNro() {
        return nro;
    }

    public void setNro(Integer nro) {
        this.nro = nro;
    }

    public Integer getSer() {
        return ser;
    }

    public void setSer(Integer ser) {
        this.ser = ser;
    }

    public String getRuc_tran() {
        return ruc_tran;
    }

    public void setRuc_tran(String ruc_tran) {
        this.ruc_tran = ruc_tran;
    }

    public String getRaz_tran() {
        return raz_tran;
    }

    public void setRaz_tran(String raz_tran) {
        this.raz_tran = raz_tran;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getBrevete() {
        return brevete;
    }

    public void setBrevete(String brevete) {
        this.brevete = brevete;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    public String getConstancia() {
        return constancia;
    }

    public void setConstancia(String constancia) {
        this.constancia = constancia;
    }
}
