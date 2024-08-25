package CuboRubik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InterfazGrafica extends JFrame {
    private JTextArea areaTexto;
    private JTable tabla;
    private JList<String> listaVector, listaSolucion, listaVariable;

    public InterfazGrafica() {
        setTitle("Agente para Solucionar Cubos de Rubik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 650);
        Container contenedor = new Container();
        setContentPane(contenedor);
        //setLayout(new BorderLayout());

        // Panel para la visualización del cubo Rubik
        JPanel panelCubo = new JPanel();
        panelCubo.setLayout(null);
        panelCubo.setLayout(new BorderLayout());
        areaTexto = new JTextArea(15, 20);
        panelCubo.add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        panelCubo.setBounds(10, 10, 550, 400);
        contenedor.add (panelCubo);

        // Panel para las listas Vector y Solución
        JPanel panelLista1 = new JPanel();
        panelLista1.add(new JScrollPane(listaVector));
        panelLista1.setBounds(570, 10, 150, 400);
        
        JPanel panelLista2 = new JPanel();
        panelLista2.add(new JScrollPane(listaSolucion));
        panelLista2.setBounds(570, 10, 150, 400);
        
        JPanel panelLista3 = new JPanel();
        panelLista3.add(new JScrollPane(listaVariable));
        panelLista3.setBounds(570, 10, 150, 400);
        
        panelLista1.setLayout(new GridLayout());
        listaVariable = new JList<>();
        listaVector = new JList<>();
        listaSolucion = new JList<>();
        
        
        
        contenedor.add (panelLista1);

        // botones y entradas
        JButton botonBuscar = new JButton("Buscar");
        botonBuscar.setBounds(590, 550, 100, 30);
        contenedor.add(botonBuscar);
        
        JButton botonIniciar = new JButton("Iniciar");
        botonIniciar.setBounds(590, 510, 100, 30);
        contenedor.add(botonIniciar);
        
        JButton botonExpandir = new JButton("Expandir");
        botonExpandir.setBounds(700, 510, 100, 30);
        contenedor.add(botonExpandir);
        
        JButton botonSolucionar = new JButton("Solucionar");
        botonSolucionar.setBounds(700, 550, 100, 30);   
        contenedor.add(botonSolucionar);


        // Agregar componentes al JFrame
        add(panelCubo, BorderLayout.WEST);
      //  add(panelListas, BorderLayout.EAST);
       // add(panelControles, BorderLayout.SOUTH);

        botonBuscar.addActionListener(this::accionBuscar);
    }

    private void accionBuscar(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
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
            areaTexto.setText(content.toString());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar el archivo: " + ex.getMessage(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
