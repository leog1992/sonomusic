/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dereck
 */
public class Cl_Tipo_Documentos {
    private int id;
    private String desc;
    private int serie;
    private int nro;
    Cl_Conectar con = new Cl_Conectar();

    public Cl_Tipo_Documentos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }
    
    public int ver_ser (int iddoc, int idalm) {
        int ser = 0;
        try {
            Statement st = con.conexion();
            String ver_ser_doc = "select ser_doc from documento_almacen where idtipo_doc = '"+iddoc+"' and idAlmacen = '"+idalm+"'";
            ResultSet rs = con.consulta(st, ver_ser_doc);
            if (rs.next()) {
                ser = rs.getInt("ser_doc");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ser;
    }
    
    public int ver_num (int iddoc, int idalm) {
        int num = 0;
        try {
            Statement st = con.conexion();
            String ver_ser_doc = "select nro_doc from documento_almacen where idtipo_doc = '"+iddoc+"' and idAlmacen = '"+idalm+"'";
            ResultSet rs = con.consulta(st, ver_ser_doc);
            if (rs.next()) {
                num = rs.getInt("nro_doc");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return num;
    }
        
    public void act_doc (int serie, int nro, int idalm, int tido) {
        try {
            Statement st = con.conexion();
            String upd_doc = "update documento_almacen set ser_doc = '"+serie+"', nro_doc = '"+nro+"'"
                    + " where idAlmacen = '"+idalm+"' and idtipo_doc = '"+tido+"'";
            con.actualiza(st, upd_doc);
            con.cerrar(st);
        } catch (Exception ex) {
            System.out.print(ex);
        }
    }
    
    public Boolean validar_RUC(String ruc) {
        Boolean validado = false;
        int dig[] = new int[10];
        int factores[] = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
        //  System.out.println("digitos del ruc");
        for (int i = 0; i < 10; i++) {
            dig[i] = Integer.parseInt(ruc.charAt(i) + "");
            //      System.out.println(dig[i] + "\t");
        }
        int producto[] = new int[10];
        //   System.out.println("producto de cada digito");
        for (int i = 0; i < 10; i++) {
            producto[i] = dig[i] * factores[i];
         //   System.out.println(producto[i]);
        }
        int suma_producto = 0;
        //     System.out.println("suma total del producto");
        for (int i = 0; i < 10; i++) {
            suma_producto += producto[i];
        }
   //     System.out.println(suma_producto);
        //     System.out.println("Resultado de formula");
        int formula = 11 - (suma_producto % 11);
        //       System.out.println(formula);
        String resultado = formula + "";
//        System.out.println("longitud de resultado " + resultado.length());
        int longitud = resultado.length();
        String ultimo = resultado.charAt(longitud - 1) + "";
        //       System.out.println("ultimo digito " + ultimo);
        int dig11 = Integer.parseInt(ruc.charAt(10) + "");
        //       System.out.println("comparando " + ultimo + " = " + dig11);
        if (dig11 == Integer.parseInt(ultimo)) {
            validado = true;
        }
//        System.out.println(validado);
        return validado;
    }
}
