/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author lubricante
 */
public class render_cierre extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        String texto = String.valueOf(table.getValueAt(row, 4));
        if (Double.parseDouble(texto) < 0) {
            setBackground(Color.red);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }

        if (column == 0) {
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        if (column == 1) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
        if (column == 2) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
        if (column == 3) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
        if (column == 4) {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }

}
