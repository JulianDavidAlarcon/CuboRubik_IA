package CuboRubik;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CuboRubikPanel extends JPanel {
    private JTable tablaCubo;
    private DefaultTableModel modeloCubo;

    public CuboRubikPanel() {
        setLayout(new BorderLayout());
        modeloCubo = new DefaultTableModel(15, 15);
        tablaCubo = new JTable(modeloCubo);
        configuraTablaCubo();
        add(new JScrollPane(tablaCubo), BorderLayout.CENTER);
    }

    private void configuraTablaCubo() {
        tablaCubo.setRowHeight(25);
        tablaCubo.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(Color.WHITE);
                return c;
            }
        });
    }

    // Método para actualizar el cubo con datos desde el archivo o desde otro input
    public void actualizarCubo(String[][] datos) {
        modeloCubo.setRowCount(0); // Limpiar la tabla
        for (String[] fila : datos) {
            modeloCubo.addRow(fila);
        }
    }
}
