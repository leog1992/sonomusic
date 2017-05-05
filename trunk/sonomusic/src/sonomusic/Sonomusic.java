/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonomusic;

import Clases.Cl_Entidad;
import java.net.ServerSocket;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Dereck
 */
public class Sonomusic {

//    private static ServerSocket SERVER_SOCKET;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        frm_login loader = new frm_login();
//        try {
//            SERVER_SOCKET = new ServerSocket(1338);
        loader.setVisible(true);
//        } catch (IOException x) {
//            System.out.print(x);
//            System.out.println("Otra instancia de la aplicación se está ejecutando...");
//            JOptionPane.showMessageDialog(null, "La Aplicacion ya se esta ejecutando.");
//            System.exit(0);
//        }
    }
}
