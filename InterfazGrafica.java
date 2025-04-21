import javax.swing.*;
import java.awt.*;

public class InterfazGrafica extends JFrame {
    // juego
    private MagoDeLasPalabras juego;
    // Componentes
    private JTextArea letrasTurno;
    private JTextArea palabrasUsadas;
    private JTextField campoPalabra;
    private JTextArea areaPuntajes;
    private JButton adivinarPalabra, pasar, registrarPalabra;
    private JLabel imagen;
    private JFrame ventana;

//    public InterfazGrafica(int jugadores, String modalidad) {
//        super("El Mago de las Palabras");
//        this.juego = new MagoDeLasPalabras(jugadores, modalidad);
//        //
//        inicializarComponentes();
//        configurarVentana();
//        ejecucionDeEventos();
//        juego.iniciarJuego();
//    }

    public InterfazGrafica(){
        ventana = new JFrame();

    }


    private void ejecucionDeEventos() {
        juego.iniciarJuego();
        // adivinar
        adivinarPalabra.addActionListener(e -> {
            String palabraIngresada = campoPalabra.getText();

        });

        // pasar
        pasar.addActionListener(e -> {

        });

        // registrar palabra
        registrarPalabra.addActionListener(e -> {

        });
    }

}
