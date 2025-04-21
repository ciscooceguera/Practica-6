import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Mago de las Palabras");
        ventana.setSize(750, 850);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);

        JButton jugar = new JButton("Jugar");
        JButton reglas = new JButton("Reglas");
        JButton creditos = new JButton("Créditos");
        JButton salir = new JButton("Salir");

        jugar.setBounds(100, 700, 100, 50);
        reglas.setBounds(250, 700, 100, 50);
        creditos.setBounds(400, 700, 100, 50);
        salir.setBounds(550, 700, 100, 50);

        ventana.add(jugar);
        ventana.add(reglas);
        ventana.add(creditos);
        ventana.add(salir);
        ventana.setLayout(null);
        ventana.setVisible(true);

        ImageIcon mago = new ImageIcon("C:\\Users\\joser\\IdeaProjects\\Practica-6\\magoInicio.png");
        JLabel imagen = new JLabel(mago);
        imagen.setBounds(0, 0, mago.getIconWidth(), mago.getIconHeight());
        ventana.add(imagen);

        jugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int numJugadores = 0;
                while (numJugadores < 2 || numJugadores > 4) {
                    String numJugadoresStr = JOptionPane.showInputDialog(ventana,
                            "Numero de jugadores",
                            "Jugadores",
                            JOptionPane.QUESTION_MESSAGE);
                    numJugadores = Integer.parseInt(numJugadoresStr);
                    Object[] botones = {"Regular", "Experto"};
                    int modalidadOpcion = JOptionPane.showOptionDialog(ventana,
                            "Elige modalidad",
                            "Modalidad",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            botones,
                            botones[0]);
                    String modalidad = "";
                    switch (modalidadOpcion) {
                        case 0:
                            modalidad = "Regular";
                            break;
                        case 1:
                            modalidad = "Experto";
                            break;
                    }
                    MagoDeLasPalabras juego = new MagoDeLasPalabras(numJugadores, modalidad);
                    juego.iniciarJuego();
                    juego.crearInterfaz();
//                    juego.iniciarJuego();
//                    InterfazGrafica interfazGrafica = new InterfazGrafica(numJugadores,modalidad);
//                    interfazGrafica.setLocationRelativeTo(ventana);
                }
            }
        });

        reglas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] botones = {"Regular", "Experto"};
                int modalidadOpcion = JOptionPane.showOptionDialog(ventana,
                        "Elige modalidad",
                        "Modalidad",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        botones,
                        botones[0]);
                String modalidad = "";
                switch (modalidadOpcion) {
                    case 0:
                        JOptionPane.showMessageDialog(ventana,
                                "Recibe un conjunto de 10 letras y forma todas las palabras válidas posibles con estas para poder puntuar. \n" +
                                        "Al formar una palabra válida, recibe 5 puntos por c/d vocal y 3 por c/d consonante.\n" +
                                        "Si el jugador no forma una palabra válida, entonces pierde 5 puntos.\n" +
                                        "No podrás repetir palabras en una ronda. Una ronda termina si todos los jugadores\n" +
                                        "acuerdan no poder escribir más palabras. Al pasar 3 rondas el ganador será aquel\n" +
                                        "que haya acumulado una mayor cantidad de puntos.",
                                "Reglas Modo Regular", JOptionPane.DEFAULT_OPTION);
                        break;
                    case 1:
                        JOptionPane.showMessageDialog(ventana,
                                "Recibe un conjunto de 10 letras y forma todas las palabras válidas posibles con estas para poder puntuar. \n" +
                                        "Al formar una palabra válida, recibe 5 puntos por c/d vocal y 3 por c/d consonante.\n" +
                                        "Si el jugador no forma una palabra válida, entonces pierde 5 puntos.\n" +
                                        "No podrás repetir palabras en una ronda. Una ronda termina si todos los jugadores\n" +
                                        "acuerdan no poder escribir más palabras. Al pasar 3 rondas el ganador será aquel\n" +
                                        "que haya acumulado una mayor cantidad de puntos.\n" +
                                        "Regla experto: También se te darán letras con acento\n" +
                                        "la cual no podrá ser utilizada para formar palabras.",
                                "Reglas Modo Experto", JOptionPane.DEFAULT_OPTION);
                        break;
                }
            }
        });

        creditos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ventana, "Realizado por: \n" +
                                "Francisco Javier Oceguera Gutierrez" +
                                "\n José Ramón Suffo Peimbert",
                        "Creditos", JOptionPane.DEFAULT_OPTION);
            }
        });

        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}