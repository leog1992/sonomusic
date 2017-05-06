/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import br.com.adilson.util.Extenso;
import br.com.adilson.util.PrinterMatrix;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;

/**
 *
 * @author gerenciatecnica
 */
public class Print_Separacion_Ticket {

    Cl_Conectar con = new Cl_Conectar();
    Cl_Varios ven = new Cl_Varios();

    public void generar(int venta) {
        PrinterMatrix printer = new PrinterMatrix();

        Extenso e = new Extenso();

        e.setNumber(101.85);

        Date date = new Date();
        String ruc_empresa = "";
        String razon_social = "";
        String direccion = "";
        String ntienda = "";
        String nro_cliente = "";
        String nombre_cliente = "";
        String direccion_cliente = "";
        String tipo_documento = "";
        String fecha_registro = "";
        String telefonos = "";
        String nombre_documento = "";
        int vtienda = 0;
        String serie_ticketera = "";
        int id_tido = 0;
        int serie = 0;
        int numero = 0;
        try {
            Statement st = con.conexion();
            String c_cabezera = "select v.idalmacen, a.nom_alm, a.dir_alm, a.ruc, a.raz_soc, a.telefono1, a.telefono2, td.desc_tipd, v.fec_ped, v.est_ped, v.idtipo_doc, v.serie_doc, v.nro_doc, c.dir_per, v.cli_doc, v.cli_nom, v.total "
                    + "from pedido as v "
                    + "inner join almacen as a on a.idalmacen = v.idalmacen "
                    + "inner join tipo_doc as td on td.idtipo_doc = v.idtipo_doc "
                    + "inner join cliente as c on c.nro_doc = v.cli_doc "
                    + "where v.idpedido = '" + venta + "'";
            System.out.println(c_cabezera);
            ResultSet rs = con.consulta(st, c_cabezera);
            if (rs.next()) {
                vtienda = rs.getInt("idalmacen");
                ruc_empresa = rs.getString("ruc");
                razon_social = rs.getString("raz_soc");
                direccion = rs.getString("dir_alm");
                ntienda = rs.getString("nom_alm");
                nombre_cliente = rs.getString("cli_nom");
                direccion_cliente = rs.getString("dir_per");
                tipo_documento = rs.getString("desc_tipd");
                nro_cliente = rs.getString("cli_doc");
                telefonos = rs.getString("telefono1") + " / " + rs.getString("telefono2");
                //fecha_registro = ven.fecha_larga_tabla(rs.getString("fecha_registro"));
                DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                fecha_registro = hourdateFormat.format(date);
                id_tido = rs.getInt("idtipo_doc");
                serie = rs.getInt("serie_doc");
                numero = rs.getInt("nro_doc");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        double pagado = 0;

        try {
            Statement st = con.conexion();
            String c_pagos = "select sum(monto) as monto "
                    + "from letras_pedido "
                    + "where idpedido = '" + venta + "'";
            ResultSet rs = con.consulta(st, c_pagos);
            while (rs.next()) {
                pagado = rs.getDouble("monto");
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

        if (vtienda == 2) {
            serie_ticketera = "PSSFIKA15030062";
        }

        if (vtienda == 3) {
            serie_ticketera = "PSSFIKA15030061";
        }

        if (vtienda == 4) {
            serie_ticketera = "PSSFIKA15030091";
        }

        if (vtienda == 5) {
            serie_ticketera = "PSSFIKA15030063";
        }

        if (vtienda == 6) {
            serie_ticketera = "PSSFIKA15030092";
        }

        if (vtienda == 7) {
            serie_ticketera = "PSSFIKA15030093";
        }

        int cantidad_filas_resultado = 0;
        try {
            Statement st = con.conexion();
            String c_filas = "select count(*) as cantidad_filas "
                    + "from detalle_pedido as dv "
                    + "where dv.idpedido = '" + venta + "'";
            ResultSet rs = con.consulta(st, c_filas);
            if (rs.next()) {
                cantidad_filas_resultado = rs.getInt("cantidad_filas");

            }
            if (cantidad_filas_resultado > 4) {
                cantidad_filas_resultado = cantidad_filas_resultado + 2;
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println(cantidad_filas_resultado + "cantidad de productos");
        //Definir el tamanho del papel para la impresion  aca 25 lineas y 80 columnas
        if (id_tido == 6) {
            // printer.setOutSize(29,40);
            printer.setOutSize(38 + cantidad_filas_resultado, 40);
        } else {
            //printer.setOutSize(25, 40);
            printer.setOutSize(32 + cantidad_filas_resultado, 40);
        }

        //Imprimir * de la 2da linea a 25 en la columna 1;
        // printer.printCharAtLin(2, 25, 1, "*");
        //Imprimir * 1ra linea de la columa de 1 a 80
        //printer.printCharAtCol(1, 1, 80, "=");
        //Imprimir Encabezado nombre del La EMpresa
        printer.printTextLinCol(1, 1, varios_impresion.centrar_texto(40, "** SONO MUSIC IMPORT **"));
        //printer.printTextWrap(1, 3, 0, 40, varios_impresion.centrar_texto(40, ruc_empresa + " - " + razon_social.toUpperCase()+ " - " + "Tel: " + telefonos));
        printer.printTextWrap(1, 3, 0, 40, ruc_empresa + " - " + razon_social.toUpperCase() + " - " + "Tel: " + telefonos);
        //printer.printTextLinCol(3, 1, varios_impresion.centrar_texto(40, "Tel: " + telefonos));
        if (direccion.length() > 39) {
            printer.printTextLinCol(4, 1, direccion.substring(0, 39)); //aplicar subString(0,39);
        } else {
            printer.printTextLinCol(4, 1, direccion);
        }
        //printer.printTextWrap(linI, linE, colI, colE, null);
        printer.printTextLinCol(6, 1, "TIENDA: " + ntienda.toUpperCase());
        printer.printTextLinCol(7, 1, "NOTA DE SEPARACION  #: " + ven.ceros_izquierda(3, serie + "") + " - " + ven.ceros_izquierda(5, numero + ""));
        printer.printTextLinCol(8, 1, "FECHA EMISION: " + fecha_registro);
        printer.printTextLinCol(9, 1, "SERIE: " + serie_ticketera);

        int aumenta_fila = 0;

        aumenta_fila = 4;
        int otra_fila = 0;
        printer.printTextLinCol(10, 1, "CLIENTE:");
        if (!nro_cliente.equals("00000000")) {
            printer.printTextLinCol(11, 1, nro_cliente);
            otra_fila++;
            printer.printTextLinCol(11 + otra_fila, 1, nombre_cliente);
            otra_fila++;
            printer.printTextLinCol(11 + otra_fila, 1, direccion_cliente);
            otra_fila++;
        } else {
            printer.printTextLinCol(11 + otra_fila, 1, nombre_cliente);
        }

        int filas = 0;
        double total = 0;
        try {
            Statement st = con.conexion();
            String c_detalle = "select dv.cantidad, dv.precio, p.desc_pro, p.marca, p.modelo "
                    + "from detalle_pedido as dv "
                    + "inner join productos as p on p.idproductos = dv.idproductos "
                    + "where dv.idpedido = '" + venta + "'";
            ResultSet rs = con.consulta(st, c_detalle);
            filas = filas + aumenta_fila;

            while (rs.next()) {
                String producto;
                producto = rs.getString("desc_pro").trim() + " " + rs.getString("marca").trim() + " " + rs.getString("modelo").trim();
                int cantidad = rs.getInt("cantidad");
                String parcial = ven.formato_totales(rs.getDouble("cantidad") * rs.getDouble("precio"));
                if (cantidad > 1) {
                    total = total + (cantidad * rs.getDouble("precio"));
                } else {
                    total = total + (rs.getDouble("precio"));
                }

                int longitud_parcial = parcial.length();
                filas++;
                //printer.printTextLinCol(10 + filas, 1, cantidad + " - ");
                if (producto.length() > 62) {
                    producto = producto.substring(0, 61);
                }

                printer.printTextWrap(9 + filas, 9 + filas + 1, 0, 40, cantidad + " - " + producto.trim());
                if ((cantidad + " - " + producto.trim()).length() > (40 - longitud_parcial - 3)) {
                    filas++;
                }
                printer.printTextLinCol(10 + filas, 29, " x " + varios_impresion.texto_derecha(9, parcial));
            }
            con.cerrar(rs);
            con.cerrar(st);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        leer_numeros leer = new leer_numeros();
        String texto_numero = leer.Convertir(ven.formato_numero(total), true) + " SOLES";

        double pendiente = total - pagado;

        if (id_tido == 6) {
            printer.printTextLinCol(10 + filas + 2, 1, varios_impresion.texto_derecha(30, "SUB TOTAL"));
            printer.printTextLinCol(10 + filas + 2, 31, varios_impresion.texto_derecha(10, ven.formato_totales(total / 1.18)));
            printer.printTextLinCol(10 + filas + 3, 1, varios_impresion.texto_derecha(30, "IGV"));
            printer.printTextLinCol(10 + filas + 3, 31, varios_impresion.texto_derecha(10, ven.formato_totales(total / 1.18 * 0.18)));
            printer.printTextLinCol(10 + filas + 4, 1, varios_impresion.texto_derecha(30, "TOTAL"));
            printer.printTextLinCol(10 + filas + 4, 31, varios_impresion.texto_derecha(10, ven.formato_totales(total)));

        } else {
            printer.printTextLinCol(10 + filas + 2, 1, varios_impresion.texto_derecha(30, "TOTAL"));
            printer.printTextLinCol(10 + filas + 2, 31, varios_impresion.texto_derecha(10, ven.formato_totales(total)));
            printer.printTextLinCol(10 + filas + 3, 1, varios_impresion.texto_derecha(30, "PAGADO"));
            printer.printTextLinCol(10 + filas + 3, 31, varios_impresion.texto_derecha(10, ven.formato_totales(pagado)));
            printer.printTextLinCol(10 + filas + 4, 1, varios_impresion.texto_derecha(30, "PENDIENTE"));
            printer.printTextLinCol(10 + filas + 4, 31, varios_impresion.texto_derecha(10, ven.formato_totales(pendiente)));
        }
        //      printer.printTextLinCol(10 + filas + 2, 1, varios_impresion.texto_derecha(30, "TOTAL"));
        //      printer.printTextLinCol(10 + filas + 2, 31, varios_impresion.texto_derecha(10, ven.formato_totales(total)));

        //printer.printTextLinCol(10 + filas + 6, 1, (10 + filas + 6) + "");
        // char[] initEP = new char[]{0x1b, '@'};
        //char[] cutP = new char[]{0x1d, 'V', 1};
        //   String ptxt = new String(initEP) + 'n';
        printer.show();
        //printer.printCharAtCol(10 + filas + 7, 1, 40, cutP.toString());
        // printer.toPrinter("BIXOLON SRP-270", cutP);
        printer.toFile("impresion.txt");

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("impresion.txt");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        byte[] initEP = new byte[]{0x1b, '@'};
        byte[] cutP = new byte[]{0x1d, 'V', 1};

        PrinterService printerService = new PrinterService();
        printerService.printString("BIXOLON SRP-270", new String(initEP));
        // printerService.printBytes("BIXOLON SRP-270", initEP );

        // printerService.printString("BIXOLON SRP-270", inputStream.toString());
        DocFlavor docFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc document = new SimpleDoc(inputStream, docFormat, null);

        PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();

        PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();

        if (defaultPrintService != null) {
            DocPrintJob printJob = defaultPrintService.createPrintJob();
            try {
                printJob.print(document, attributeSet);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "error al imprimir \n" + ex.getLocalizedMessage());
            }
        } else {
            System.err.println("No existen impresoras instaladas");
        }

        //  PrinterService printerService = new PrinterService();
        //System.out.println(printerService.getPrinters());
        printerService.printBytes("BIXOLON SRP-270", cutP);
        //printerService.printString("BIXOLON SRP-270", new String(cutP));

    }
}
