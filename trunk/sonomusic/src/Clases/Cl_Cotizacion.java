
package Clases;

public class Cl_Cotizacion {
    private int id;
    private String fec_cot;
    private String est_cot="0";

    public Cl_Cotizacion() {
    }     

    public Cl_Cotizacion(int id, String fec_cot) {
        this.id = id;
        this.fec_cot = fec_cot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFec_cot() {
        return fec_cot;
    }

    public void setFec_cot(String fec_cot) {
        this.fec_cot = fec_cot;
    }

    public String getEst_cot() {
        return est_cot;
    }

    public void setEst_cot(String est_cot) {
        this.est_cot = est_cot;
    }
    
    
    
}
