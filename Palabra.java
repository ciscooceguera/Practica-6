import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
public class Palabra {
    // atributos
    private String palabra;
    private int puntaje;
    private String modalidad;
    // constructor, recibe palabra
    public Palabra(String palabra, String modalidad){


        if (palabra == null || palabra.isEmpty()) {
            throw new IllegalArgumentException("La palabra no puede ser nula o vacía.");
        }
        this.palabra = palabra;
        puntaje = 0;
        this.modalidad = modalidad;
    }
    // obtiene el puntaje de la palabra
    public int obtejerPuntajePalabra(){
        puntaje = 0;
        // itero cada elemento del string
        for (int i = 0; i<palabra.length(); i++){
            // creo objeto de la palabra letra
            Letra letra = new Letra(palabra.charAt(i),modalidad);
            // es vocal
            if (letra.esVocal()){
                puntaje+=5;
                // es consonante
            }else if (letra.esConsonante()){
                puntaje+=3;
            }
        }
        return puntaje;
    }

    // separar palabra en arraylist de caracteres
    public ArrayList<Letra> obtenerLetras(){
        ArrayList<Letra> letras = new ArrayList<>();
        for (int i = 0; i<palabra.length(); i++){
            Letra letra = new Letra(palabra.charAt(i),modalidad);
            letras.add(letra);
        }
        return letras;
    }
    public boolean palabraContieneLasLetras(HashSet<Letra> letrasUsadas){
        ArrayList<Letra> palabraUsadaArrayList = obtenerLetras();
        int contadorLetrasUsadas = 0;
        for (Letra letra : palabraUsadaArrayList) {
            if (letrasUsadas.contains(letra)) {
                contadorLetrasUsadas++;

            }
        }
        return contadorLetrasUsadas == palabraUsadaArrayList.size();
    }
    public String toString() {
        return palabra;
    }
    // metodos usados por contains()
    public boolean equals(Object objeto){
        // si apuntan al mismo espacio en memoria son iguales
        if (this == objeto) return true;
        // si es null o no es de la misma clase no son iguales
        if (objeto == null || getClass() != objeto.getClass()) return false;
        // convierto a tipo letra
        Palabra otra = (Palabra)objeto;
        // compara sus atributos letra de ambos objetos
        return this.palabra.equals(otra.palabra);
    }
    public int hashCode() {
        return Objects.hash(palabra);
    }
}