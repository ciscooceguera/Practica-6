import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.println("|El Mago de las Palabras|");
        System.out.println("-------------------------");
        int opc = 0;
        while (opc != 4) {
            // pregunto que acció quiere realizar el usuario
            System.out.println("1. Jugar\n2. Reglas\n3. Créditos\n4. Salir\nIngresa una opción: ");
            if (sc.hasNextInt()) {
                opc = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer
            } else {
                System.out.println("\nEntrada inválida. Debes ingresar un número.");
                sc.nextLine(); // Limpiar el texto inválido
            }
            // switch de la opción seleccionada
            switch (opc) {
                // opción jugar
                case 1:
                    int numJugadores = 0;
                    while (numJugadores < 2 || numJugadores > 4) {
                        // solicito # jugadores
                        System.out.println("\nIngresa el número de jugadores (2 - 4): ");
                        if (sc.hasNextInt()) {
                            numJugadores = sc.nextInt();
                            sc.nextLine(); // Limpiar el buffer
                        } else {
                            System.out.println("\nEntrada inválida. Debes ingresar un número.");
                            sc.nextLine(); // Limpiar el texto inválido
                        }
                    }
                    int modalidadInt = 0;
                    while (modalidadInt != 1 && modalidadInt != 2) {
                        // solicito modalidad
                        System.out.println("\n1. Regular\n2. Experto\nEscoje la modalidad: ");
                        if (sc.hasNextInt()) {
                            modalidadInt = sc.nextInt();
                            sc.nextLine(); // Limpiar el buffer
                        } else {
                            System.out.println("\nEntrada inválida. Debes ingresar un número.");
                            sc.nextLine(); // Limpiar el texto inválido
                        }
                    }
                    String modalidad = "";
                    switch (modalidadInt) {
                        case 1 -> modalidad = "Regular";
                        case 2 -> modalidad = "Experto";
                    }
                    // objeto del juego
                    MagoDeLasPalabras juego = new MagoDeLasPalabras(numJugadores, modalidad);
                    juego.iniciarJuego();
                    break;
                // opción reglas
                case 2:
                    int reglasOpc = 0;
                    while (reglasOpc != 3) {
                        System.out.println("\n1. Reglas modalidad Regular\n2. Reglas modalidad Experto\n3. Salir\nIngresa una opción: ");
                        if (sc.hasNextInt()) {
                            reglasOpc = sc.nextInt();
                            sc.nextLine(); // Limpiar el buffer
                        } else {
                            System.out.println("\nEntrada inválida. Debes ingresar un número.");
                            sc.nextLine(); // Limpiar el texto inválido
                        }
                        switch (reglasOpc) {
                            // ver reglas de modalidad regular
                            case 1:
                                System.out.println("\nReglas Modalidad Regular:\nRecibe un conjunto de 10 letras y forma todas las palabras válidas" +
                                        " posibles con estas para poder puntuar.\n" +
                                        "Al formar una palabra válida, recibe 5 puntos por c/d vocal y 3 por c/d consonante.\n" +
                                        "Si el jugador no forma una palabra válida, entonces pierde 5 puntos.\n" +
                                        "No podrás repetir palabras en una ronda.\nUna ronda termina si todos los jugadores" +
                                        " acuerdan no poder escribir más palabras.\nAl pasar 3 rondas el ganador será aquel" +
                                        " que haya acumulado una mayor cantidad de puntos");
                                break;
                            // ver reglas de modalidad experto
                            case 2:
                                System.out.println("\nReglas Modalidad Experto:\nRecibe un conjunto de 10 letras y forma todas las palabras válidas" +
                                        " posibles con estas para poder puntuar.\n" +
                                        "Al formar una palabra válida, recibe 5 puntos por c/d vocal y 3 por c/d consonante.\n" +
                                        "Si el jugador no forma una palabra válida, entonces pierde 5 puntos.\n" +
                                        "No podrás repetir palabras en una ronda.\nUna ronda termina si todos los jugadores" +
                                        " acuerdan no poder escribir más palabras.\nAl pasar 3 rondas el ganador será aquel" +
                                        " que haya acumulado una mayor cantidad de puntos\n" +
                                        "Regla experto: También se te darán letras con acento" +
                                        "la cual no podrá ser utilizada para formar palabras.");
                                break;
                            // salir de las reglas
                            case 3:
                                break;
                        }
                    }
                    break;
                // opción créditos
                case 3:
                    System.out.println("\nCréditos:\nRealizado por:\nSuffo Peimbert\nFrancisco Oceguera");
                    break;
                // opción salir
                case 4:
                    break;
            }
        }
    }
}