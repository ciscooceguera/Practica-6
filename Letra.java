import javax.swing.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Letra {
    // atributos
    private ArrayList<Character> vocales;
    private ArrayList<Character> consonantes;
    private char letra;
    public Letra(char letra, String modalidad){
        this.letra = letra;
        // genero ArrayList de vocales
        vocales = new ArrayList<Character>();
        consonantes = new ArrayList<>();
        switch (modalidad) {
            case "Regular":
                vocales.add('a');
                vocales.add('e');
                vocales.add('i');
                vocales.add('o');
                vocales.add('u');
                break;
            case "Experto":
                vocales.add('a');
                vocales.add('e');
                vocales.add('i');
                vocales.add('o');
                vocales.add('u');
                vocales.add('á');
                vocales.add('é');
                vocales.add('í');
                vocales.add('ó');
                vocales.add('ú');
                break;
        }
        consonantes.add('b');
        consonantes.add('c');
        consonantes.add('d');
        consonantes.add('f');
        consonantes.add('g');
        consonantes.add('h');
        consonantes.add('j');
        consonantes.add('k');
        consonantes.add('l');
        consonantes.add('m');
        consonantes.add('n');
        consonantes.add('ñ');
        consonantes.add('p');
        consonantes.add('q');
        consonantes.add('r');
        consonantes.add('s');
        consonantes.add('t');
        consonantes.add('u');
        consonantes.add('v');
        consonantes.add('w');
        consonantes.add('x');
        consonantes.add('y');
        consonantes.add('z');
    }
    // evaluar si es consonante
    public boolean esConsonante(){
        // si es vocal, no es consontante; y viceversa
        return consonantes.stream().anyMatch(consonantes -> consonantes.equals(letra));
    }

    // evaluar si es vocal
    public boolean esVocal(){
        /* convierto el arraylist de caracteres a stream y uso metodo anyMatch()
        para comparar cada elemento con letra */
        return vocales.stream().anyMatch(vocal -> vocal.equals(letra));
    }

    public Character tomarLetra(){
        Random rnd = new Random();
        int tipo = new Random().nextInt(4)+1;
        Character letra = null;
        switch (tipo){
            case 1:
                letra =vocales.get(rnd.nextInt(vocales.size()));
                break;
            case 2:
                letra = consonantes.get(rnd.nextInt(consonantes.size()));
                break;
            case 3:
                letra = consonantes.get(rnd.nextInt(consonantes.size()));
                break;
            case 4:
                letra = consonantes.get(rnd.nextInt(vocales.size()));
                break;
        }
        return letra;
    }
    public String toString() {
        return letra+"";
    }
    public Character getLetra(){
        return letra;
    }
    // metodos usados por contains()
    public boolean equals(Object objeto){
        // si apuntan al mismo espacio en memoria son iguales
        if (this == objeto) return true;
        // si es null o no es de la misma clase no son iguales
        if (objeto == null || getClass() != objeto.getClass()) return false;
        // convierto a tipo letra
        Letra otra = (Letra)objeto;
        // compara sus atributos letra de ambos objetos
        return this.letra == otra.letra;
    }
    public int hashCode() {
        return Objects.hash(letra);
    }

    public void dibujarLetra(Character letra){
        Icon letraPantalla = null;
        switch(letra){
            case 'a': letraPantalla = new ImageIcon("src/Letras/letra_a.png");
            case 'b': letraPantalla = new ImageIcon("src/Letras/letra_b.png");
            case 'c': letraPantalla = new ImageIcon("src/Letras/letra_c.png");
            case 'd': letraPantalla = new ImageIcon("src/Letras/letra_d.png");
            case 'e': letraPantalla = new ImageIcon("src/Letras/letra_e.png");
            case 'f': letraPantalla = new ImageIcon("src/Letras/letra_f.png");
            case 'g': letraPantalla = new ImageIcon("src/Letras/letra_g.png");
            case 'h': letraPantalla = new ImageIcon("src/Letras/letra_h.png");
            case 'i': letraPantalla = new ImageIcon("src/Letras/letra_i.png");
            case 'j': letraPantalla = new ImageIcon("src/Letras/letra_j.png");
            case 'k': letraPantalla = new ImageIcon("src/Letras/letra_k.png");
            case 'l': letraPantalla = new ImageIcon("src/Letras/letra_l.png");
            case 'm': letraPantalla = new ImageIcon("src/Letras/letra_m.png");
            case 'n': letraPantalla = new ImageIcon("src/Letras/letra_n.png");
            case 'ñ': letraPantalla = new ImageIcon("src/Letras/letra_ñ.png");
            case 'o': letraPantalla = new ImageIcon("src/Letras/letra_o.png");
            case 'p': letraPantalla = new ImageIcon("src/Letras/letra_p.png");
            case 'q': letraPantalla = new ImageIcon("src/Letras/letra_q.png");
            case 'r': letraPantalla = new ImageIcon("src/Letras/letra_r.png");
            case 's': letraPantalla = new ImageIcon("src/Letras/letra_s.png");
            case 't': letraPantalla = new ImageIcon("src/Letras/letra_t.png");
            case 'u': letraPantalla = new ImageIcon("src/Letras/letra_u.png");
            case 'v': letraPantalla = new ImageIcon("src/Letras/letra_v.png");
            case 'w': letraPantalla = new ImageIcon("src/Letras/letra_w.png");
            case 'x': letraPantalla = new ImageIcon("src/Letras/letra_x.png");
            case 'y': letraPantalla = new ImageIcon("src/Letras/letra_y.png");
            case 'z': letraPantalla = new ImageIcon("src/Letras/letra_z.png");
            case 'á': letraPantalla = new ImageIcon("src/Letras/letra_a_acento.png");
            case 'é': letraPantalla = new ImageIcon("src/Letras/letra_e_acento.png");
            case 'í': letraPantalla = new ImageIcon("src/Letras/letra_i_acento.png");
            case 'ó': letraPantalla = new ImageIcon("src/Letras/letra_o_acento.png");
            case 'ú': letraPantalla = new ImageIcon("src/Letras/letra_u_acento.png");
        }
        JLabel label = new JLabel();
        label.setIcon(letraPantalla);
        label.show();
    }
    //
}