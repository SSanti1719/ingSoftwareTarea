/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Santi
 */
public class LeerFichero {

    /**
     *
     * @param archivo contiene la ruta del fichero
     * @throws FileNotFoundException excepcion por si no encuentra el fichero
     * diferentes a un numero.
     * @throws IOException
     */
    public static void leerArchivo(String archivo) throws FileNotFoundException, IOException, Exception {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);

        if (!validarArchivo(archivo)) {
            throw new Exception("");
        }

        ArrayList<String> cadenas = new ArrayList<>();
        while ((cadena = b.readLine()) != null) {
            String[] chara = cadena.split("\n");
            for (int i = 0; i < chara.length; i++) {
                
                cadenas.add(chara[i]);
            }
        }
        
        b.close();
        
        ProcesoListadoMaterias listarMaterias=new ProcesoListadoMaterias(cadenas);
        listarMaterias.listarMaterias();
        
    }

    public static boolean validarArchivo(String archivo) {
        String fe = "";
        if (archivo.contains(".")) {
            fe = archivo.substring(archivo.lastIndexOf(".") + 1);
        }
        return fe.equals("txt");
    }

    /**
     *
     * @param args
     * @throws IOException Se invoca en el main el metodo para leer el fichero
     */
    public static void main(String[] args) throws IOException, Exception {
        String ruta = JOptionPane.showInputDialog("Ingrese la ruta: ");
        //C:\Users\Santi\OneDrive\Escritorio\Tools\s.ia.txt
        //C:\Users\Santi\Documents\ingSoftware\pruebas.txt
        try {
            leerArchivo(ruta);
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra la carpeta indicada");
        } catch (Exception e) {
            System.out.println("TXT no válido");
        }
    }

}
