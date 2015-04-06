/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sonomusic;

import java.net.ServerSocket;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jvnet.substance.SubstanceLookAndFeel;
/**
 *
 * @author Dereck
 */
public class Sonomusic {

    private static ServerSocket SERVER_SOCKET;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true); //que nos permite dejar a Substance la decoracion ( por asi decirlo)
        
        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceLimeGreenTheme"); 
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.ModerateSkin"); 


        frm_login loader = new frm_login();
        try {
            SERVER_SOCKET = new ServerSocket(1334);
            loader.setVisible(true);
        } catch (IOException x) {
            System.out.print(x);
            System.out.println("Otra instancia de la aplicación se está ejecutando...");
            JOptionPane.showMessageDialog(null, "La Aplicacion ya se esta ejecutando.");
            System.exit(0);
        }
    }
}
