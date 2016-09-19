/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author lubricante
 */
public class render_productos extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        if (String.valueOf(table.getValueAt(row, 9)).equals("POR TERMINAR")) {
            setBackground(Color.green);
            setForeground(Color.black);
        } else if (String.valueOf(table.getValueAt(row, 9)).equals("NO DISPONIBLE")) {
            setBackground(Color.red);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }

        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
