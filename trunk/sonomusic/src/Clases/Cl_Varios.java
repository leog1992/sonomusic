package Clases;

import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Administrador
 */
public class Cl_Varios {

    Cl_Conectar con = new Cl_Conectar();

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

    public static boolean mostrar(JInternalFrame ventana1) {
        boolean mostrar = true;
        for (int a = 0; a < sonomusic.frm_menu.contenedor.getComponentCount(); a++) {     // verificar si es instancia de algun componente que ya este en el jdesktoppane
            if (ventana1.getClass().isInstance(sonomusic.frm_menu.contenedor.getComponent(a))) {
                System.out.println("es instancia, no se debe mostrar");
                mostrar = false;
            } else {
                System.out.println("no lo es, puede mostrarse");
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

    public void imprimir_java(String filename) throws IOException {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filename);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);

            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
            }
        } else {
            System.err.println("No existen impresoras instaladas");
            JOptionPane.showMessageDialog(null, "No Existen impresoras instaladas");
        }

        inputStream.close();
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
                System.out.print(e);
                JOptionPane.showMessageDialog(null, "error pdf " + e);
            }

        } catch (JRException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, "error report " + ex);

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
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint, "reports/" + filename + ".pdf");

            try {
                String imp = "reports/" + filename + ".pdf";
                imprimir_java(imp);
            } catch (IOException e) {
                System.out.print(e);
                JOptionPane.showMessageDialog(null, e);
            }

        } catch (JRException ex) {
            System.out.print(ex);
            JOptionPane.showMessageDialog(null, ex);

        }
    }
}
