package CuboRubik;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CuboRubikPanel extends JPanel {
    private JTable tablaCubo;
    private DefaultTableModel modeloCubo;
    private final Color[] colores = {
        Color.LIGHT_GRAY,  // 0 - Color para las celdas vacías o no usadas
        Color.YELLOW, // 1 - Amarillo
        Color.WHITE,  // 2 - Blanco
        Color.BLUE,   // 3 - Azul
        Color.ORANGE, // 4 - Naranja
        Color.GREEN,  // 5 - Verde
        Color.RED     // 6 - Rojo
    };

    public CuboRubikPanel() {
        setLayout(new BorderLayout());
        modeloCubo = new DefaultTableModel(22, 22); // Ajustar el tamaño de la tabla para visualizar el cubo
        tablaCubo = new JTable(modeloCubo);
        configuraTablaCubo();
        add(new JScrollPane(tablaCubo), BorderLayout.CENTER);
        inicializarCubo(); // Método para inicializar el cubo con colores
    }

    private void configuraTablaCubo() {
        tablaCubo.setRowHeight(25);
        tablaCubo.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                int valor = (value != null) ? Integer.parseInt(value.toString()) : 0;
                c.setBackground(colores[valor]);
                return c;
            }
        });
        tablaCubo.setShowGrid(true);
        tablaCubo.setGridColor(Color.BLACK);
    }

    private void inicializarCubo() {
        int[][] estadoInicial = new int[22][22];

        // Llenar el estado inicial con valores de ejemplo
        for (int i = 0; i < estadoInicial.length; i++) {
            for (int j = 0; j < estadoInicial[i].length; j++) {
                estadoInicial[i][j] = 0; // Inicializar en vacío (0)
            }
        }

        // Configuración de las caras del cubo (usar los valores correctos según la disposición)
        // Cara superior blanca (2)
        for (int i = 0; i < 4; i++) {
            for (int j = 9; j < 13; j++) {
                estadoInicial[i][j] = 2;
            }
        }

        // Cara frontal verde (5)
        for (int i = 9; i < 13; i++) {
            for (int j = 9; j < 13; j++) {
                estadoInicial[i][j] = 5;
            }
        }

        // Cara derecha azul (3)
        for (int i = 9; i < 13; i++) {
            for (int j = 13; j < 17; j++) {
                estadoInicial[i][j] = 3;
            }
        }

        // Cara izquierda naranja (4)
        for (int i = 9; i < 13; i++) {
            for (int j = 5; j < 9; j++) {
                estadoInicial[i][j] = 4;
            }
        }

        // Cara inferior amarilla (1)
        for (int i = 13; i < 17; i++) {
            for (int j = 9; j < 13; j++) {
                estadoInicial[i][j] = 1;
            }
        }

        // Cara trasera roja (6)
        for (int i = 5; i < 9; i++) {
            for (int j = 9; j < 13; j++) {
                estadoInicial[i][j] = 6;
            }
        }

        actualizarCubo(estadoInicial);
    }

    // Método para actualizar el cubo con datos desde un archivo o desde otro input
    public void actualizarCubo(int[][] datos) {
        for (int i = 0; i < datos.length; i++) {
            for (int j = 0; j < datos[i].length; j++) {
                modeloCubo.setValueAt(datos[i][j], i, j);
            }
        }
    }
}
