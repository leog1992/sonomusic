

package Clases;

public class Cl_Medida {
    private int id_med;
    private String des_med;

    public Cl_Medida() {
    }

        
    public Cl_Medida(int id_med, String des_med) {
        this.id_med = id_med;
        this.des_med = des_med;
    }

    public int getId_med() {
        return id_med;
    }

    public void setId_med(int id_med) {
        this.id_med = id_med;
    }

    public String getDes_med() {
        return des_med;
    }

    public void setDes_med(String des_med) {
        this.des_med = des_med.toUpperCase();
    }
    
    
    
}
