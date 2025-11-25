package mx.edu.itcelaya.proyectoarchivos;

import java.io.*;
import java.util.Scanner;

public class GestorDeArchivos {
    Scanner scan = new Scanner(System.in);
    
    //unicamente busca ultimo renglon, captura y escribe
    void altaReg(String nomArch, int tamReg, String[] formatos, String[] instruccionesAltas)
    {
        try (RandomAccessFile canal = new RandomAccessFile(nomArch, "rw")) {
            //calculamos el renglon
            int nReg=(int)canal.length()/tamReg;
            
            //llama escribir y se le envia el resultado de la captura
            escribeReg(canal, tamReg, nReg, formatos, ingresaDatos(instruccionesAltas));
            //este es nomas para probar 
            ordenar(canal, tamReg, formatos);
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
    
    //unicamente crea array de datos
    String[] ingresaDatos(String[] instruccionesAltas){
        String[] datos = new String[instruccionesAltas.length];
        
        for(int i =0; i<instruccionesAltas.length; i++)
            {
                //vamos pidiendo los datos
                System.out.print(instruccionesAltas[i]);
                datos[i] = scan.nextLine(); 
            }
        
        return datos;
    }
    
    //unicamente recibe renglon y escribe los datos dando formato
    void escribeReg(RandomAccessFile canal, int tamReg, int nReg, String[] formatos, String[] datos) throws IOException
    {
        //posicionamos el cursor
        canal.seek(nReg*tamReg);
        
        for(int i =0; i<formatos.length; i++)
            {
                canal.writeUTF(String.format(formatos[i],datos[i]));
            }
    }
    
    //unicamente crea array de datos de un renglon, ya con el formato
    String[] leerReg(RandomAccessFile canal, int tamReg, int nReg, String[] formatos) throws IOException
    {
        String[] datosLeidos = new String[formatos.length];
        //posicionamos el cursor
        canal.seek(nReg*tamReg);

        //
        for(int i = 0; i<formatos.length; i++)
        {
            datosLeidos[i] = canal.readUTF();
        }
            
        return datosLeidos;
        
    }
    
    
    public void ordenar(RandomAccessFile canal, int tamReg, String[] formatos) throws IOException
    {
        String[] datos1 = new String[formatos.length];
        String[] datos2 = new String[formatos.length];
        boolean ban;
        for(int i=1;i< (int)(canal.length())/tamReg;i++)
        {
            //reinicio de bandera
            ban = true;
            for(int j=1;j<=((int)(canal.length()/tamReg)-i);j++)
            {
                datos1 = leerReg(canal, tamReg, j-1, formatos);
                datos2 = leerReg(canal, tamReg, j, formatos);
                if(datos1[0].compareTo(datos2[0])>0)
                {
                    // Se graban en los registros cambiados
                    escribeReg(canal, tamReg, j, formatos, datos1);
                    escribeReg(canal, tamReg, j-1, formatos, datos2);
                    
                    //bandera de que hubo cambio
                    ban = false;
                }
            }
            
            //rompe ciclo si no hubo cambio
            if(ban)
            {
               break;
            }
        }
    }
    
    public void reporte(String nomArch,String nomRepo, String tituloCentrado, String subTitulos, int tamReg, String[] formatos)
    {
        try (
        RandomAccessFile entrada = new RandomAccessFile(nomArch, "r");
        PrintWriter salida = new PrintWriter(new FileWriter(nomRepo))
        ) {
            String[] datosRepo;
            
            System.out.println(tituloCentrado);
            salida.println(tituloCentrado);
            
            System.out.println(subTitulos);
            salida.println(subTitulos);
            //ciclo que lee cada linea
            for(int i=0;i< (int)(entrada.length())/tamReg;i++)
            {
                datosRepo = leerReg(entrada, tamReg, i, formatos);
                //ciclo que imprime cada linea
                for(int j=0; j<formatos.length; j++)
                {
                    System.out.print("  " + datosRepo[j] + "  ");
                    salida.print("  " + datosRepo[j] + "  ");
                }
                System.out.println();
                salida.println();
            }

        } catch (IOException e) {
            // Este catch captura errores de ambos archivos
            System.err.println("Error de entrada/salida: " + e.getMessage());
        }
    }
}
