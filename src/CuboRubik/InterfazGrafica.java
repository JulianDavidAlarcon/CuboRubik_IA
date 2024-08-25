package CuboRubik;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InterfazGrafica extends JFrame {

    private JTextArea areaTexto;
    private JButton botonCargar, botonIniciar, botonExpandir, botonSolucionar;
    private JPanel panelBotones, panelCubo;

    public InterfazGrafica() {
        setTitle("Agente para Solucionar Cubos de Rubik");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        panelCubo = new JPanel();
        panelCubo.setBorder(BorderFactory.createTitledBorder("Estado del Cubo Rubik"));
        areaTexto = new JTextArea(20, 50);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 12));
        panelCubo.add(new JScrollPane(areaTexto));
        add(panelCubo, BorderLayout.CENTER);

        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 4, 10, 10));

        botonCargar = new JButton("Cargar Estado");
        botonIniciar = new JButton("Iniciar");
        botonExpandir = new JButton("Expandir");
        botonSolucionar = new JButton("Solucionar");

        panelBotones.add(botonCargar);
        panelBotones.add(botonIniciar);
        panelBotones.add(botonExpandir);
        panelBotones.add(botonSolucionar);

        add(panelBotones, BorderLayout.SOUTH);

        botonCargar.addActionListener(e -> cargarEstadoDesdeArchivo());
    }

    private void cargarEstadoDesdeArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione el archivo de estado inicial");

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fileChooser.getSelectedFile().getAbsolutePath();
            cargarEstado(path);
        }
    }

    private void cargarEstado(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            areaTexto.setText(builder.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InterfazGrafica interfaz = new InterfazGrafica();
            interfaz.setVisible(true);
        });
    }
}
