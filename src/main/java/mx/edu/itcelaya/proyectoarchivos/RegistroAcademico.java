package mx.edu.itcelaya.proyectoarchivos;

import java.io.*;
import java.util.Scanner;

public abstract class RegistroAcademico {
    
    Scanner scan = new Scanner(System.in);
    //nombres archivos
    //"Alumnos.dat","Materias.dat","Inscripciones.dat"
    //nombres reportes
    //"ReporteAlumnos.txt","ReporteMaterias.txt","ReporteInscripciones.txt"
    String nomArch;
    
    String nomRepo;
    
    int tamReg;
    
    String tituloCentrado;
    
    String subTitulos;
    
    public abstract void alta();
    public abstract void busqueda();
    public abstract void reporte();
    public abstract void modificaciones();
    public abstract void ordenar();
    
    /*
    void reporte()
    {
        String subTitulos = "";
        String tituloCentrado = (String.format("%"+((tamReg/2) + (nomArch[nArch].length()/2) + (formatos.length*2))+"s", nomArch[nArch]));
        for(int i =0; i<formatos.length; i++)
        {
            subTitulos = subTitulos + "  " + String.format(formatos[i], atriCapturables[i]) + "  ";
        }
        
        arch.reporte(nomArch[nArch], nomRepo[nArch], tituloCentrado, subTitulos, tamReg, formatos);
    }
    */
}
