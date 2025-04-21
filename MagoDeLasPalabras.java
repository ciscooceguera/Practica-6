import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MagoDeLasPalabras {
    // atributos
    private HashSet<Integer> numPaso;
    private int turno, contadorRonda,numJugadores;
    private String modalidad;
    private Palabra palabra;
    private HashSet<Palabra> palabrasUsadasEnElTurno;
    private HashMap<Integer,Integer> jugadores;
    private HashMap<Palabra,Integer> jugadorPalabrasUsadas;
    private HashSet<Letra> letras;
    private HashMap<Palabra, Integer> palabrasMap;

    public MagoDeLasPalabras(int numJugadores, String modalidad){
        // siempre inicia el jugador 1
        turno = 1;
        numPaso = new HashSet<>();
        // guardo lo recibido de los parámetros del constructor en los atributos, respectivamente
        this.modalidad = modalidad;
        this.numJugadores = numJugadores;
        // inicializo arraylists
        jugadores = new HashMap<>();
        letras = new HashSet<>();
        palabrasMap = new HashMap<>();
        palabrasUsadasEnElTurno = new HashSet<>();
        jugadorPalabrasUsadas = new HashMap<>();
        contadorRonda = 0;
    }
    // inicializo puntajes en 0s
    public void inicializarPuntajes(){
        for (int i = 0; i<numJugadores; i++){
            jugadores.put(i,0);
        }
    }

    // control del flujo del juego
    public void iniciarJuego(){
        cargarPalabras();
        inicializarPuntajes();
        int contadorRondaTemp = contadorRonda-1;
        while (contadorRonda!=3){
            System.out.println("\n**********");
            System.out.println("*Ronda "+(contadorRonda+1)+"*");
            System.out.println("**********");
            if (contadorRonda!= contadorRondaTemp){
                // creo las palabras para el turno y las muestro
                generarLetras();
                contadorRondaTemp++;
            }
            // mientras quiera jugar
            int opcTurno = 1;
            int puntajeTurno = 0;
            int flagJugadorAdivinoPalabra = 0;
            while (opcTurno!=2) {
                mostrarLetras();
                opcTurno = mostrarMenuDeTurno();
                switch (opcTurno) {
                    // jugar
                    case 1:
                        // mientras no se usen las letras permitidas o no se solicite salir
                        solicitarPalabra();
                        boolean validacionDeIntento = evaluarSiSeUsaronSoloLasLetrasPermitidas();
                        if (!validacionDeIntento) {
                            System.out.println("\nSolo puedes usar las letras permitidas !");
                            int puntajeTemp = jugadores.get(turno - 1);
                            // no adivinó
                            puntajeTemp -= 5;
                            jugadores.remove(turno - 1);
                            jugadores.put(turno - 1, puntajeTemp);
                            // sí adivinó
                            mostrarPuntajes();
                            cambiarTurno();
                        } else {
                            if (validarPalabraEnArchivoTXT()) {
                                if (!validarPalabraEnHashSet()) {
                                    opcTurno = 2;
                                    System.out.println("\nPalabra correcta !");
                                    palabrasUsadasEnElTurno.add(palabra);
                                    jugadorPalabrasUsadas.put(palabra, turno - 1);
                                    puntajeTurno = encontrarValuePalabraEnHash();
                                    System.out.println("\nPuntaje de la palabra: " + palabra.obtejerPuntajePalabra());
                                    flagJugadorAdivinoPalabra = 1;
                                    int puntajeTemp = jugadores.get(turno-1);
                                    jugadores.remove(turno - 1);
                                    puntajeTemp+=palabra.obtejerPuntajePalabra();
                                    jugadores.put(turno - 1, puntajeTemp);
                                    mostrarPuntajes();
                                    cambiarTurno();
                                } else {
                                    System.out.println("\nPalabra ya adivinada previamente ");
                                    int puntajeTemp = jugadores.get(turno - 1);
                                    // no adivinó
                                    puntajeTemp -= 5;
                                    jugadores.remove(turno - 1);
                                    jugadores.put(turno - 1, puntajeTemp);
                                    // sí adivinó
                                    puntajeTurno = 0;
                                    mostrarPuntajes();
                                    cambiarTurno();
                                }
                            } else if (!palabra.toString().equals("1")) {
                                System.out.println("\nPalabra incorrecta ");
                                int puntajeTemp = jugadores.get(turno - 1);
                                // no adivinó
                                puntajeTemp -= 5;
                                jugadores.remove(turno - 1);
                                jugadores.put(turno - 1, puntajeTemp);
                                // sí adivinó
                                mostrarPuntajes();
                                cambiarTurno();
                            }
                        }
                        break;
                    // pasar turno
                    case 2:
                        // actualizo el puntaje
                        numPaso.add(turno - 1);
                        int puntajeTemp = jugadores.get(turno - 1);
                        // no adivinó
                        if (flagJugadorAdivinoPalabra == 0) {
                            puntajeTemp -= 5;
                            jugadores.remove(turno - 1);
                            jugadores.put(turno - 1, puntajeTemp);
                            // sí adivinó
                        }
                        mostrarPuntajes();
                        cambiarTurno();
                        break;
                    // case ver palabras
                    case 3:
                        mostrarPalabrasJugadores();
                        break;
                }
            }
        }
        mostrarGanador();
    }
    //Validar si palabra existe en las palabras utilizadas
    public boolean validarPalabraEnHashSet(){
        return palabrasUsadasEnElTurno.contains(palabra);
    }
    // mostrar puntajes
    public void mostrarPuntajes(){
        System.out.println("\nPuntajes:");
        for (int i = 0; i<numJugadores; i++){
            int j = i+1;
            System.out.println("\nPuntaje Jugador " + j + ": " + jugadores.get(i));
        }
    }
    // mostrar ganador
    public void mostrarGanador(){
        ArrayList<Integer> jugadoresGanadores = new ArrayList<>();
        int ganador = 0;
        for (int i = 0; i<numJugadores-1; i++){
            int jugador = jugadores.get(i);
            int jugadorSig = jugadores.get(i+1);
            if (jugador<jugadorSig){
                ganador = i+1;
            }else if (jugador==jugadorSig){
                jugadoresGanadores.add(ganador);
                jugadoresGanadores.add(ganador+1);
                ganador = -2;
            }
        }
        ganador+=1;
        if (ganador==-1){
            System.out.println("\nEmpate! Ganadores:");
            for (Integer jugadoresGanadore : jugadoresGanadores) {
                System.out.println("\nJugador " + (jugadoresGanadore+1));
            }
        }else {
            System.out.println("\nHa ganado el jugador " + ganador);
        }
    }
    // encontrar idx palabra en hasmap
    public int encontrarValuePalabraEnHash(){
        return palabrasMap.get(palabra);
    }
    // validar palabra
    public boolean validarPalabraEnArchivoTXT(){
        if (palabrasMap.containsKey(palabra)){
            return true;
        }
        return false;
    }
    // menu del turno
    public int mostrarMenuDeTurno(){
        // imprimo quien está jugando actualmente y su puntaje hasta el momento
        Scanner sc = new Scanner(System.in);
        System.out.println("\nTurno actual: "+turno);
        System.out.println("\n1. Escribir palabra\n2. Pasar turno\n3. Ver palabras adivinadas\nIngresa la opción: ");
        int opc = 0;
        if (sc.hasNextInt()) {
            opc = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
        } else {
            System.out.println("\nEntrada inválida. Debes ingresar un número.\n");
            sc.nextLine(); // Limpiar el texto inválido
        }
        return opc;
    }
    // evalua si se usaron solo las letras generadas para el turno
    public boolean evaluarSiSeUsaronSoloLasLetrasPermitidas(){
        if (palabra.toString().equals("1")){
            return true;
        }
        return palabra.palabraContieneLasLetras(letras);
    }
    // metodo para pedir que el jugador cree una palabra con las letras dadas
    public void solicitarPalabra(){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n* Para regresar ingresa 1 * ");
        System.out.println("Forma una palabra con las letras dadas: ");
        String palabraTemporal = sc.nextLine();
        palabra = new Palabra(palabraTemporal,modalidad);
    }
    public void cambiarTurno(){
        if (numPaso.size() == numJugadores) {
            contadorRonda++;
            jugadorPalabrasUsadas.clear();
            palabrasUsadasEnElTurno.clear();
            numPaso.clear();
        }
        turno = turno%numJugadores+1;
        if (numPaso.contains(turno-1)){
            cambiarTurno();
        }
    }
    public void cargarPalabras(){
        palabrasMap.clear();
        String nombreArchivo = "";
        if(modalidad.equals("Experto")){
            nombreArchivo ="C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\PRACTICA_6\\palabras.txt";
        } else {
            nombreArchivo ="C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\PRACTICA_6\\palabrasNoAcentos.txt";
        }
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String palabra;
            Integer puntaje;
            while ((palabra = br.readLine()) != null) {
                //  System.out.println(palabra);
                Palabra p = new Palabra(palabra,modalidad);
                puntaje=p.obtejerPuntajePalabra();
                palabrasMap.put(p, puntaje);
            }
        } catch (IOException e) {
            System.err.println("\nError al leer el archivo: " + e.getMessage());
        }
    }
    // mostrar las letras para el turno actual
    public void mostrarLetras(){
        System.out.println("\nLetras de la ronda: ");
        System.out.println(letras.toString());
    }
    // generar las 10 letras
    public void generarLetras() {
        letras.clear();
        // ciclo for 0 - 9
        while (letras.size() < 10) {
            // creo un objeto letra y la agrego al arraylist de letras
            Letra letraTemporal = new Letra('0', modalidad);
            Character toma = letraTemporal.tomarLetra();
            Letra letra = new Letra(toma, modalidad);
            letras.add(letra);

        }
    }

    public void mostrarPalabrasJugadores() {
        Iterator <Palabra> iterator = jugadorPalabrasUsadas.keySet().iterator();
        if (!jugadorPalabrasUsadas.isEmpty()) {
            System.out.println("\nListado de palabras usadas: ");
            while (iterator.hasNext()) {
                Palabra palabra = iterator.next();
                System.out.println("Palabra : " + palabra.toString() + ". Puntaje: " + palabra.obtejerPuntajePalabra());
            }
        }else{
            System.out.println("\nAún no se han adivinado palabras !");
        }
    }
}