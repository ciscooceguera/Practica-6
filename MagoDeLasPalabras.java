import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class  MagoDeLasPalabras extends JFrame {
    // interfaz grafica
    // juego
    private MagoDeLasPalabras juego;
    // Componentes
    private JTextArea letrasTurno;
    private JTextArea palabrasUsadas;
    private JTextArea turnoYRonda;
    private JTextField campoPalabra;
    private JTextArea areaPuntajes;
    private JButton adivinarPalabra, pasar;
    private JLabel imagen;

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
        // interfaz grafica
        super("El Mago de las Palabras");


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

        //
        inicializarComponentes();
        configurarVentana();
    }
    // metodos interfaz grafica
    // inicializa botones, campos de texto, etc.
    private void inicializarComponentes() {
        // letras disponibles en el turno
        letrasTurno = new JTextArea(5,20);
        letrasTurno.setEditable(false);
        letrasTurno.setFont(new Font("Monospaced", Font.BOLD, 24));
        letrasTurno.setBackground(new Color(230, 230, 250));
        letrasTurno.setBorder(BorderFactory.createTitledBorder("Letras del turno"));
        letrasTurno.setLineWrap(true);
        letrasTurno.setWrapStyleWord(true);
        // info turno y ronda actuales
        turnoYRonda = new JTextArea(5,31);
        turnoYRonda.setEditable(false);
        turnoYRonda.setFont(new Font("Monospaced", Font.BOLD, 14));
        turnoYRonda.setBackground(new Color(230, 230, 250));
        turnoYRonda.setText(getTurnoYRonda());
        turnoYRonda.setLineWrap(true);
        turnoYRonda.setWrapStyleWord(true);
        // campo para ingresar/adivinar palabra
        campoPalabra = new JTextField();
        campoPalabra.setFont(new Font("Monospaced", Font.BOLD, 24));
        campoPalabra.setPreferredSize(new Dimension(750, 50));
        campoPalabra.setBorder(BorderFactory.createTitledBorder("Ingresa una palabra"));
        // campo para todas las palabras usadas
        palabrasUsadas = new JTextArea(5,31);
        palabrasUsadas.setEditable(false);
        palabrasUsadas.setFont(new Font("Monospaced", Font.PLAIN, 16));
        palabrasUsadas.setBackground(new Color(234, 255, 211));
        palabrasUsadas.setBorder(BorderFactory.createTitledBorder("Palabras Usadas"));
        // campo para mostrar puntajes
        areaPuntajes = new JTextArea(5,31);
        areaPuntajes.setEditable(false);
        areaPuntajes.setFont(new Font("Monospaced", Font.PLAIN, 16));
        areaPuntajes.setBackground(new Color(227, 195, 244));
        areaPuntajes.setBorder(BorderFactory.createTitledBorder("Puntajes"));
        // botones
        adivinarPalabra = new JButton("Adivinar Palabra");
        adivinarPalabra.setPreferredSize(new Dimension(200, 50));
        pasar = new JButton("Pasar Turno");
        pasar.setPreferredSize(new Dimension(200, 50));

        // imagen
        ImageIcon mago = new ImageIcon("C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\PRACTICA_6\\magoJuego.png");
        imagen = new JLabel(mago);
        imagen.setBounds(0, 0, mago.getIconWidth(), mago.getIconHeight());

    }
    // establece las divisiones de las ventanas
    public void configurarVentana() {
        // Establecer el Layout principal de la ventana como BorderLayout
        this.setLayout(new BorderLayout());
        // panel norte (imagen)
        JPanel norte = new JPanel();
        norte.setPreferredSize(new Dimension(750, 200));
        norte.add(imagen);

        // panel centro
        JPanel centro = new JPanel();
        centro.setLayout(new BorderLayout());
        centro.setPreferredSize(new Dimension(750, 500));
        // paneles internos para dividir el panel centro
        // panel izquierdo central (muestreo de las palabras usadas)
        JPanel izquierda = new JPanel();
        izquierda.setLayout(new BorderLayout());
        izquierda.setPreferredSize(new Dimension(325, 0));
        izquierda.add(palabrasUsadas, BorderLayout.WEST);
        // panel superior del central (letras usadas)
        JPanel centroNorte = new JPanel();
        centroNorte.setLayout(new BorderLayout());
        centroNorte.add(letrasTurno, BorderLayout.NORTH);
        centroNorte.setPreferredSize(new Dimension(750, 80));
        // panel derecho del centro (puntajes)
        JPanel derecha = new JPanel();
        derecha.setLayout(new BorderLayout());
        derecha.setPreferredSize(new Dimension(325, 0));
        derecha.add(areaPuntajes, BorderLayout.EAST); // Mostrar puntajes

        // Agregar los paneles al centro
        centro.add(izquierda, BorderLayout.WEST);
        centro.add(centroNorte, BorderLayout.NORTH);
        centro.add(derecha, BorderLayout.EAST);
        centro.add(turnoYRonda, BorderLayout.CENTER);

        //Panel sur
        JPanel sur = new JPanel();
        sur.setLayout(new BorderLayout());
        sur.setPreferredSize(new Dimension(750, 0));
        // se divide en 2, el superior donde se ingresa la palabra
        // panel inferior (botones)
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER)); // Centrado de los botones
        panelBotones.setPreferredSize(new Dimension(750, 70)); // Ajusta la altura de los botones
        panelBotones.add(adivinarPalabra);
        panelBotones.add(pasar);
        // añado los paneles intenros
        sur.add(campoPalabra, BorderLayout.NORTH);
        sur.add(panelBotones, BorderLayout.SOUTH);
        sur.setLayout(new FlowLayout());
        sur.setPreferredSize(new Dimension(750, 150)); // altura de 150
        // Agregar los paneles a la ventana
        this.add(norte, BorderLayout.NORTH);
        this.add(centro, BorderLayout.CENTER);
        this.add(sur, BorderLayout.SOUTH);
        // Configuración de la ventana
        this.setSize(750, 850);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public String getTurnoYRonda(){
        String mensaje = "";
        mensaje += "Ronda: \n " + contadorRonda + "\n\n" + "Turno: \n" + turno;
        return mensaje;
    }
    // inicializo puntajes en 0s
    public void inicializarPuntajes(){
        for (int i = 0; i<numJugadores; i++){
            jugadores.put(i,0);
        }
    }
    public void flujoInterfaz(){
        cargarPalabras();
        inicializarPuntajes();
        areaPuntajes.setText(getStringPuntajes());
        // creo letras
        generarLetras();
        letrasTurno.setText(letras.toString());

        AtomicInteger flagJugadorAdivinoPalabra = new AtomicInteger();
        // adivinar
        adivinarPalabra.addActionListener(e -> {
            String palabraIngresada = campoPalabra.getText();
            palabra = new Palabra(palabraIngresada,modalidad);
            boolean validacionDeIntento = evaluarSiSeUsaronSoloLasLetrasPermitidas();
            if (!validacionDeIntento){
                JOptionPane.showMessageDialog(null,"Solo puedes usar las letras permitidas !");
                campoPalabra.setText("");
                int puntajeTemp = jugadores.get(turno-1);
                // no adivinó
                puntajeTemp -= 5;
                jugadores.remove(turno -1 );
                jugadores.put(turno-1,puntajeTemp);
                areaPuntajes.setText(getStringPuntajes());
                cambiarTurno();
            }else{
                if(validarPalabraEnArchivoTXT()){
                    if (!validarPalabraEnHashSet()){
                        JOptionPane.showMessageDialog(null,"Palabra correcta !");
                        campoPalabra.setText("");
                        palabrasUsadasEnElTurno.add(palabra);
                        jugadorPalabrasUsadas.put(palabra,turno-1);
                        JOptionPane.showMessageDialog(null,"Puntaje de la palabra: " + palabra.obtejerPuntajePalabra());
                        flagJugadorAdivinoPalabra.set(1);
                        int puntajeTemp = jugadores.get(turno-1);
                        jugadores.remove(turno - 1);
                        puntajeTemp+=palabra.obtejerPuntajePalabra();
                        jugadores.put(turno - 1, puntajeTemp);
                        areaPuntajes.setText(getStringPuntajes());
                        palabrasUsadas.setText(getPalabrasUsadas());
                        cambiarTurno();

                    }else{
                        JOptionPane.showMessageDialog(null,"Palabra adivinada previamente");
                        campoPalabra.setText("");
                        int puntajeTemp = jugadores.get(turno-1);
                        jugadores.remove(turno - 1);
                        puntajeTemp-=5;
                        jugadores.put(turno-1, puntajeTemp);
                        int puntajeTurno = 0;
                        areaPuntajes.setText(getStringPuntajes());
                        cambiarTurno();
                    }
                }else if (!palabra.toString().equals("1")){
                    JOptionPane.showMessageDialog(null,"Palabra incorrecta");
                    // preguntar si quiere registrarla
                    int yesNoResponse = JOptionPane.showConfirmDialog(null,
                            "Registrar la palabra?","Registro",
                            JOptionPane.YES_NO_OPTION);
                    switch (yesNoResponse){
                        case JOptionPane.YES_OPTION:
                            if (!validarPalabraEnArchivoTXT()){
                                agregarPalabraADiccionario(palabra);
                                palabrasUsadasEnElTurno.add(palabra);
                                jugadorPalabrasUsadas.put(palabra, turno - 1);
                                JOptionPane.showMessageDialog(null,"\nPuntaje de la palabra: " + palabra.obtejerPuntajePalabra());
                                int puntajeTemp = jugadores.get(turno-1);
                                jugadores.remove(turno - 1);
                                puntajeTemp+=palabra.obtejerPuntajePalabra();
                                jugadores.put(turno - 1, puntajeTemp);
                                palabrasUsadas.setText(getPalabrasUsadas());
                            }
                            break;
                        case JOptionPane.NO_OPTION:
                            int puntajeTemp = jugadores.get(turno-1);
                            puntajeTemp-=5;
                            jugadores.remove(turno-1);
                            jugadores.put(turno-1, puntajeTemp);
                            break;
                    }
                    campoPalabra.setText("");
                    areaPuntajes.setText(getStringPuntajes());
                    cambiarTurno();
                }
            }
        });

        // pasar
        pasar.addActionListener(e -> {
            // actualizo el puntaje
            numPaso.add(turno - 1);
            int puntajeTemp = jugadores.get(turno - 1);
            // no adivinó
            puntajeTemp -= 5;
            jugadores.remove(turno - 1);
            jugadores.put(turno - 1, puntajeTemp);
            areaPuntajes.setText(getStringPuntajes());
            cambiarTurno();
        });
    }
    public String getPalabrasUsadas(){
        Iterator iterator = palabrasUsadasEnElTurno.iterator();
        String palabrasusadas = "";
        while (iterator.hasNext()){
            palabrasusadas += iterator.next().toString() + "\n";
        }
        return palabrasusadas;
    }
    public String getStringPuntajes(){
        String puntajesJugadores = "";
        for (int i = 0; i<numJugadores; i++){
            puntajesJugadores += "Jugador "+(i+1)+": "+jugadores.get(i)+"\n";
        }
        return puntajesJugadores;
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
                                System.out.println("\n¿Quieres agregar la palabra al diccionario? 1. Si | 2. No");
                                Scanner sc = new Scanner(System.in);
                                int agregar = sc.nextInt();
                                switch (agregar) {
                                    case 1:
                                        opcTurno = 2;
                                        agregarPalabraADiccionario(palabra);
                                        palabrasUsadasEnElTurno.add(palabra);
                                        jugadorPalabrasUsadas.put(palabra, turno - 1);
                                        System.out.println("\nPuntaje de la palabra: " + palabra.obtejerPuntajePalabra());
                                        flagJugadorAdivinoPalabra = 1;
                                        int puntajeTemp = jugadores.get(turno-1);
                                        jugadores.remove(turno - 1);
                                        puntajeTemp+=palabra.obtejerPuntajePalabra();
                                        jugadores.put(turno - 1, puntajeTemp);
                                        break;
                                    case 2:
                                        puntajeTemp = jugadores.get(turno - 1);
                                        // no adivinó
                                        puntajeTemp -= 5;
                                        jugadores.remove(turno - 1);
                                        jugadores.put(turno - 1, puntajeTemp);
                                        // sí adivinó
                                        break;
                                }
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
        turnoYRonda.setText(getTurnoYRonda());
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

    public void agregarPalabraADiccionario(Palabra palabra){
        String nombreArchivo;
        String palabraAAgregar = palabra.toString();
        if(modalidad.equals("Experto")){
            nombreArchivo ="C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\PRACTICA_6\\palabras.txt";
        } else {
            nombreArchivo ="C:\\Users\\RedBo\\OneDrive\\Escritorio\\POO\\PRACTICA_6\\palabrasNoAcentos.txt";
        }
        try{
            FileWriter fw = new FileWriter(nombreArchivo, true);
            fw.write("\n"+palabraAAgregar);
            fw.close();
            JOptionPane.showMessageDialog(null,"Palabra agregada al diccionario!");
        } catch (IOException e) {
            System.out.println("\nError al escribir en el archivo: " + e.getMessage());
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