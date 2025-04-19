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
    //
}