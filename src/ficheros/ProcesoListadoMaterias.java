/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Santi
 */
public class ProcesoListadoMaterias {

    ArrayList<Persona> personas = new ArrayList<>();
    ArrayList<String> materiasInscritas;

    public ProcesoListadoMaterias(ArrayList<String> materiasInscritas) {
        this.materiasInscritas = materiasInscritas;
    }

    /**
     *
     * @param cadena será la linea con todos los datos del arreglo principal de
     * datos El método convertirá esa linea en un array por medio de la funcion
     * split, la cual será la encargada de devolvernos los datos del estudiante
     * @throws Exception lanzará excepción en caso tal de que el arreglo que nos
     * retornó la función no sea igual a 4, que serán los campos
     * id,nombre,idcurso,curso del estudiante.
     */
    public void validarSintaxisCadena(String cadena) throws Exception {
        String[] linea = cadena.split(",");
        if (!(linea.length == 4)) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     *
     * @param cadena será el arreglo individual de los datos del estudiante en
     * caso tal de que no el metodo no pueda parsear ni la cédula del estudiante
     * ni el identificador del curso, lanzará una excepción que capturamos en el
     * método que lo llamamos.
     */
    public void validarOrdenCadena(String[] cadena) {
        Double.parseDouble(cadena[0]);
        Double.parseDouble(cadena[2]);
    }

    /**
     *
     *
     * El método realizará un conteo de repetidos que inicializaremos en 0,
     * vamos a empezar a comparar una linea contra todas las lineas y, con un
     * condicional, preguntaremos si ambas lineas son iguales, en caso de que lo
     * sean sumaremos al contador. cuando termine de comparar una linea contra
     * todas las lineas, preguntaremos si el contador encontró más de 1
     * coincidencia, si fue asi, nos retornará false, sino, reiniciaremos el
     * contador para pasar a la siguiente linea y empezar a comparar conlas
     * demás nuevamente.
     *
     * @return falso en caso de encontrar repetidos y verdadero en caso de no
     * encontrarlos.
     *
     */
    public boolean validarRepetidos() {
        int contador = 0;
        for (int i = 0; i < materiasInscritas.size(); i++) {
            String[] linea = devolverLineaArray(materiasInscritas.get(i));
            for (int j = 0; j < materiasInscritas.size(); j++) {
                String[] linea2 = devolverLineaArray(materiasInscritas.get(j));
                if (Arrays.equals(linea, linea2)) {
                    contador++;
                }
            }
            if (contador > 1) {
                return false;
            } else {
                contador = 0;
            }
        }
        return true;
    }

    /**
     *
     *
     * @param cedula contará las materias comparando la cédula del estudiante
     * contra el array de datos que tenemos
     * @return el numero de materias
     */
    public int contarMaterias(int cedula) {
        int numMaterias = 0;
        for (int i = 0; i < materiasInscritas.size(); i++) {
            String[] linea = devolverLineaArray(materiasInscritas.get(i));
            if (cedula == Integer.parseInt(linea[0])) {
                numMaterias++;
            }
        }
        return numMaterias;
    }

    /**
     *
     * @param persona entrará la persona la cual queremos verificar que está en
     * el array de personas
     * @return true en caso ed que haya una persona en el array, false en caso
     * contrario.
     */
    public boolean estaEnElArray(Persona persona) {

        for (int i = 0; i < personas.size(); i++) {
            if (persona.getIdentificador() == personas.get(i).getIdentificador()) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param linea linea de strings
     * @return un array con sus datos separados anteriormente por (",")
     */
    public String[] devolverLineaArray(String linea) {
        String[] array = linea.split(",");
        return array;
    }

    /**
     * Nos verificará que la cedula sea unica para cada estudiante, validando que para cada apararición de una cédula
     * solo exista un nombre de estudiante.
     * @return 
     */
    public boolean cedulaUnica() {
        for (int i = 0; i < materiasInscritas.size(); i++) {
            String[] linea = devolverLineaArray(materiasInscritas.get(i));
            //Persona persona = new Persona(Integer.parseInt(linea[0]), linea[1]);
            for (int j = 0; j < materiasInscritas.size(); j++) {
                String[] linea2 = devolverLineaArray(materiasInscritas.get(j));
                
                if ((linea[0].equals(linea2[0]))&&(!linea[1].equals(linea2[1]))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * el metodo principal del listado de materias.
     *
     * @throws Exception
     */
    public void listarMaterias() throws Exception {
        try {
            for (int i = 0; i < materiasInscritas.size(); i++) {
                validarSintaxisCadena(materiasInscritas.get(i));
                validarOrdenCadena(devolverLineaArray(materiasInscritas.get(i)));
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sintaxis no válida");
        } catch (NumberFormatException e) {
            System.out.println("El orden de la cadena no es válido");
        }
        if (!cedulaUnica()) {
            System.out.println("Hay un registro donde se incumple el identificado unico por estudiante.");
        }
        else if (!validarRepetidos()) {
            System.out.println("Hay repetidos");
        } else {
            for (int i = 0; i < materiasInscritas.size(); i++) {
                String[] linea = devolverLineaArray(materiasInscritas.get(i));
                int cedula = Integer.parseInt(linea[0]);
                String nombre = linea[1];
                Persona persona = new Persona(cedula, nombre);
                int numMaterias = contarMaterias(cedula);
                persona.setNumMaterias(numMaterias);
                if (!estaEnElArray(persona)) {

                    personas.add(persona);
                }
            }

        }
        for (int i = 0; i < personas.size(); i++) {
            System.out.println(personas.get(i).nombre + " " + personas.get(i).numMaterias + " materias");
        }
    }
}
