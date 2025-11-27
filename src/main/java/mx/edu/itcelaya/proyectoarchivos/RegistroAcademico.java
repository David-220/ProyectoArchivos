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

}
