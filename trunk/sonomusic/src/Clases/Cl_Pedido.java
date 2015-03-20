package Clases;

public class Cl_Pedido {

    private String id_ped;
    private String fec_ped;
    private String fec_pag_ped;
    //tipo de pago
    private double des_ped;
    private String est_ped = "1";
    //tipo de documento
    private String ser_ped;
    private double total;

    public String getId_ped() {
        return id_ped;
    }

    public void setId_ped(String id_ped) {
        this.id_ped = id_ped.toUpperCase();
    }

    public String getFec_ped() {
        return fec_ped;
    }

    public void setFec_ped(String fec_ped) {
        this.fec_ped = fec_ped;
    }

    public String getFec_pag_ped() {
        return fec_pag_ped;
    }

    public void setFec_pag_ped(String fec_pag_ped) {
        this.fec_pag_ped = fec_pag_ped;
    }

    public double getDes_ped() {
        return des_ped;
    }

    public void setDes_ped(double des_ped) {
        this.des_ped = des_ped;
    }

    public String getEst_ped() {
        return est_ped;
    }

    public void setEst_ped(String est_ped) {
        this.est_ped = est_ped;
    }

    public String getSer_ped() {
        return ser_ped;
    }

    public void setSer_ped(String ser_ped) {
        this.ser_ped = ser_ped;
    }

    public String getAlb_ped() {
        return alb_ped;
    }

    public void setAlb_ped(String alb_ped) {
        this.alb_ped = alb_ped;
    }
    //cliente
    //usuario
    //almacen
    private String alb_ped;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
