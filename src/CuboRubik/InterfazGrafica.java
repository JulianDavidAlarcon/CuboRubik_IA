package CuboRubik;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InterfazGrafica extends JFrame {
    private JTable tablaCubo, tablaEstados;
    private DefaultTableModel modeloCubo, modeloEstados;
    private JList<String> listaVector, listaSolucion;
    private JSplitPane splitPaneHorizontal, splitPaneVertical;
    private JPanel panelCubo, panelVectorSolucion, panelControles, panelEstados;
    private JLabel labelL, labelP, labelN;
    private JTextField textM;

    public InterfazGrafica() {
        setTitle("Agente para Solucionar Cubos de Rubik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLayout(new BorderLayout());

        configuraCubo();
        configuraTablaEstados();
        configuraSplitPanes();
        configuraControles();

        add(splitPaneVertical, BorderLayout.CENTER);
    }

    private void configuraCubo() {
        modeloCubo = new DefaultTableModel(15, 15);
        tablaCubo = new JTable(modeloCubo);
        configuraTablaCubo();
    }

    private void configuraTablaEstados() {
        modeloEstados = new DefaultTableModel(5, 5); // Ajustar dimensiones si es necesario
        tablaEstados = new JTable(modeloEstados);
        panelEstados = new JPanel(new BorderLayout());
        panelEstados.add(new JLabel("Estado del Cubo", SwingConstants.CENTER), BorderLayout.NORTH);
        panelEstados.add(new JScrollPane(tablaEstados), BorderLayout.CENTER);
    }

    private void configuraSplitPanes() {
        splitPaneVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPaneHorizontal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPaneVertical.setResizeWeight(0.8); // Aumentar espacio para el Cubo de Rubik
        splitPaneHorizontal.setResizeWeight(0.7);

        panelCubo = new JPanel(new BorderLayout());
        panelCubo.add(new JLabel("Cubo de Rubik", SwingConstants.CENTER), BorderLayout.NORTH);
        panelCubo.add(new JScrollPane(tablaCubo), BorderLayout.CENTER);

        panelVectorSolucion = new JPanel(new GridLayout(1, 2));
        listaVector = new JList<>();
        listaSolucion = new JList<>();
        panelVectorSolucion.add(createLabeledPanel(listaVector, "Vector"));
        panelVectorSolucion.add(createLabeledPanel(listaSolucion, "Solución"));

        splitPaneHorizontal.setLeftComponent(panelCubo);
        splitPaneHorizontal.setRightComponent(panelVectorSolucion);
        splitPaneVertical.setTopComponent(splitPaneHorizontal);
        splitPaneVertical.setBottomComponent(panelEstados); // Mover tabla de estados aquí
    }

    private void configuraControles() {
        panelControles = new JPanel(new GridLayout(1, 8, 10, 10));
        agregarBotones();
        agregarCamposSalidaEntrada();
    }

    private void agregarBotones() {
        String[] nombresBotones = {"Buscar", "Iniciar", "Expandir", "Solucionar"};
        for (String nombre : nombresBotones) {
            JButton boton = new JButton(nombre);
            boton.addActionListener(this::accionBuscar);
            panelControles.add(boton);
        }
    }

    private void agregarCamposSalidaEntrada() {
        labelL = new JLabel("L: 0", SwingConstants.CENTER);
        labelP = new JLabel("P: 0", SwingConstants.CENTER);
        labelN = new JLabel("N: 0", SwingConstants.CENTER);
        textM = new JTextField("5", 5);

        styleLabel(labelL);
        styleLabel(labelP);
        styleLabel(labelN);
        styleTextField(textM);

        panelControles.add(labelL);
        panelControles.add(labelP);
        panelControles.add(labelN);
        panelControles.add(new JLabel("M:"));
        panelControles.add(textM);

        // Añadir controles al panel de estados para mantener la disposición
        panelEstados.add(panelControles, BorderLayout.SOUTH);
    }

    private void styleLabel(JLabel label) {
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setFont(new Font("Arial", Font.BOLD, 12));
    }

    private void styleTextField(JTextField textField) {
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    private JPanel createLabeledPanel(JComponent component, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(label, BorderLayout.NORTH);
        panel.add(new JScrollPane(component), BorderLayout.CENTER);
        return panel;
    }

    private void accionBuscar(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            cargarArchivo(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void cargarArchivo(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            // Procesar el contenido para mostrar en L, P, N
            labelL.setText("L: " + content.length());
            labelP.setText("P: " + content.hashCode());
            labelN.setText("N: " + content.toString().split("\n").length);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el archivo: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazGrafica().setVisible(true));
    }
}
