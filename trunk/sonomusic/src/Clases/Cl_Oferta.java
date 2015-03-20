
package Clases;

public class Cl_Oferta {
    private int id_ofe;
    private String nom_ofe;
    private String fec_ini;
    private String fec_ven;
    private int cant_ofe;
    private String est_ofe="1";

    public Cl_Oferta() {
    }
       
    public Cl_Oferta(int id_ofe, String nom_ofe, String fec_ini, String fec_ven, int cant_ofe) {
        this.id_ofe = id_ofe;
        this.nom_ofe = nom_ofe;
        this.fec_ini = fec_ini;
        this.fec_ven = fec_ven;
        this.cant_ofe = cant_ofe;
    }    
    
    public int getId_ofe() {
        return id_ofe;
    }

    public void setId_ofe(int id_ofe) {
        this.id_ofe = id_ofe;
    }

    public String getNom_ofe() {
        return nom_ofe;
    }

    public void setNom_ofe(String nom_ofe) {
        this.nom_ofe = nom_ofe;
    }

    public String getFec_ini() {
        return fec_ini;
    }

    public void setFec_ini(String fec_ini) {
        this.fec_ini = fec_ini;
    }

    public String getFec_ven() {
        return fec_ven;
    }

    public void setFec_ven(String fec_ven) {
        this.fec_ven = fec_ven;
    }

    public int getCant_ofe() {
        return cant_ofe;
    }

    public void setCant_ofe(int cant_ofe) {
        this.cant_ofe = cant_ofe;
    }

    public String getEst_ofe() {
        return est_ofe;
    }

    public void setEst_ofe(String est_ofe) {
        this.est_ofe = est_ofe;
    }
    
    
}
