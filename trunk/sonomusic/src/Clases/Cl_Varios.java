/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Administrador
 */
public class Cl_Varios {

    Cl_Conectar con = new Cl_Conectar();
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    DecimalFormat formato = null;

    public String formato_numero(Double number) {
        simbolo.setDecimalSeparator('.');
        formato = new DecimalFormat("######0.00", simbolo);
        String numero = "";
        numero = formato.format(number);
        return numero;
    }

    public String formato_tc(Double number) {
        simbolo.setDecimalSeparator('.');
        formato = new DecimalFormat("######0.000", simbolo);
        String numero = "";
        numero = formato.format(number);
        return numero;
    }

    public String formato_totales(Double number) {
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        formato = new DecimalFormat("#,###,##0.00", simbolo);
        String numero = "";
        numero = formato.format(number);
        return numero;
    }

    public void llamar_ventana(JInternalFrame ventana1) {
        if (mostrar(ventana1)) {
            sonomusic.frm_menu.contenedor.add(ventana1);
            ventana1.show();
            Dimension desktopSize = sonomusic.frm_menu.contenedor.getSize();
            Dimension jInternalFrameSize = ventana1.getSize();
            ventana1.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                    (desktopSize.height - jInternalFrameSize.height) / 2);
        }
    }

    public void solo_numeros(KeyEvent evt) {
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }

    public void solo_precio(KeyEvent evt) {
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9') && car != '.') {
            evt.consume();
        }
    }

    public void limitar_caracteres(KeyEvent evt, JTextField txt, int longitud) {
        if (txt.getText().length() == longitud) {
            evt.consume();
        }
    }

    public static boolean mostrar(JInternalFrame ventana1) {
        boolean mostrar = true;
        for (int a = 0; a < sonomusic.frm_menu.contenedor.getComponentCount(); a++) {     // verificar si es instancia de algun componente que ya este en el jdesktoppane
            if (ventana1.getClass().isInstance(sonomusic.frm_menu.contenedor.getComponent(a))) {
                System.out.println("ya esta cargado, no se puede mostrar");
                JOptionPane.showMessageDialog(null, "ESTA VENTANA YA ESTA ABIERTA, BUSQUELA, CIERRE, Y ABRA NUEVAMENTE");
                mostrar = false;
            } else {
                //System.out.println("no lo es, puede mostrarse");
            }
        }
        return mostrar;
    }

    public String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        return formateador.format(ahora);
    }

    public String fechaformateada(String fecha) {
        String m_fecha = null;
        try {
            DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date fec = df.parse(fecha);
            m_fecha = dt.format(fec);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return m_fecha;
    }

    public String fechabase(String fecha) {
        String m_fecha = null;
        try {
            DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date fec = dt.parse(fecha);
            m_fecha = df.format(fec);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return m_fecha;
    }

    // Suma los días recibidos a la fecha  
    public Date suma_dia(String fecha, int dias) {
        Calendar calendar = Calendar.getInstance();
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(fecha);
            calendar.setTime(date); // Configuramos la fecha que se recibe
            calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
        } catch (ParseException ex) {
            Logger.getLogger(Cl_Varios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
    }

    public void centrar_celda(JTable table, int col) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(col).setCellRenderer(tcr);
    }

    public void derecha_celda(JTable table, int col) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        table.getColumnModel().getColumn(col).setCellRenderer(tcr);
    }

    public void ver_reporte(String filename, Map<String, Object> parametros) {
        Connection st = con.conx();

        try {
            JasperReport jasperReport;
            JasperPrint jasperPrint;
            jasperReport = JasperCompileManager.compileReport("reports//" + filename + ".jrxml");
            jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, st);
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint, "reports/" + filename + ".pdf");

            try {
                File file = new File("reports/" + filename + ".pdf");
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                System.out.print(e + " -- error io");
                JOptionPane.showMessageDialog(null, "Error al Generar el PDF -- " + e);
            }

        } catch (JRException ex) {
            System.out.print(ex + " -- error jre");
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error con el Reporte -- " + ex);
        }
    }

   

    public void imp_reporte(String filename, Map<String, Object> parametros) {
        Connection st = con.conx();

        try {
            JasperReport jasperReport;
            JasperPrint jasperPrint;
            jasperReport = JasperCompileManager.compileReport("reports//" + filename + ".jrxml");
            jasperPrint = JasperFillManager.fillReport(
                    jasperReport, parametros, st);
            JasperPrintManager.printReport(jasperPrint, false);
        } catch (JRException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    public String leer_archivo(String nom_arc) {
        String linea = null;
        try {
            File Ffichero = new File(nom_arc);
            /*Si existe el fichero*/
            if (Ffichero.exists()) {
                /*Abre un flujo de lectura a el fichero*/
                BufferedReader Flee = new BufferedReader(new FileReader(Ffichero));
                String Slinea;
                /*Lee el fichero linea a linea hasta llegar a la ultima*/
                while ((Slinea = Flee.readLine()) != null) {
                    /*Imprime la linea leida*/
                    linea = Slinea;
                }
                /*Cierra el flujo*/
                Flee.close();
            } else {
                System.out.println("Fichero No Existe");
                linea = "NO ALMACEN";
            }
        } catch (IOException ex) {
            /*Captura un posible error y le imprime en pantalla*/
            System.out.println(ex.getMessage());
        }
        return linea;
    }

    public String ceros_izquierda(int largo, String string) {
        String ceros = "";
        int cantidad = largo - string.length();
        if (cantidad >= 1) {
            for (int i = 0; i < cantidad; i++) {
                ceros += "0";
            }
            return (ceros + string);
        } else {
            return string;
        }
    }

    public boolean esEntero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public boolean esDecimal(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
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
