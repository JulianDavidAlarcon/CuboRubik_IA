package CuboRubik;

/**
 * 20242008_Inteligencia Artificial_Cun
 * Cubo Rubik_ V1
 * Autores: Julián Alarcón, Albert Olarte, Sebastian Palacio
 */
public class Principal {

    private void ejecutar() {
        // Crear la interfaz gráfica del cubo Rubik
        InterfazGrafica interfaz = new InterfazGrafica();
        interfaz.setVisible(true);
    }

    public static void main(String[] args) {
        new Principal().ejecutar();
        // Instanciar y ejecutar la aplicación para visualizar el cubo Rubik
    }
}
