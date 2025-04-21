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

    public InterfazGrafica(int jugadores, String modalidad) {
        super("El Mago de las Palabras");
        this.juego = new MagoDeLasPalabras(jugadores, modalidad);
        //
        inicializarComponentes();
        configurarVentana();
        ejecucionDeEventos();
    }
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
        // campo para ingresar/adivinar palabra
        campoPalabra = new JTextField();
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
        registrarPalabra = new JButton("Registrar Palabra");
        registrarPalabra.setPreferredSize(new Dimension(200, 50));
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
        panelBotones.add(registrarPalabra);
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

    private void ejecucionDeEventos() {

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
