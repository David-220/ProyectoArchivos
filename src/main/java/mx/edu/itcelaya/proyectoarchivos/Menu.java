package mx.edu.itcelaya.proyectoarchivos;

import java.util.Scanner;

public class Menu 
{
    Scanner scan = new Scanner(System.in);
    Alumnos al = new Alumnos();
    Materias ma = new Materias();
    Inscripcion ins = new Inscripcion();
    String insAl, insMa;
    int op1 = 0;
    int op2;
    
    public Menu() 
    {
        mainMenu();
    }

    private void mainMenu() 
    {        
        while(op1!=4)
        {    
            op2 = 0;
            // mostrar opciones
            
            switch (op1) 
            {
                case 1:
                    menuAlumnos();
                    break;
                case 2:
                    menuMaterias();
                    break;
                case 3:
                    procesoInscripcion();
                    break;
                case 4:
                    System.out.println("Fin del proceso");
                    break;
            }            
        }         
    }

    private void menuAlumnos() 
    {
        while(op2!=5)
        {  
            
            //mostrar opciones 
            
            switch (op2) 
            {
                case 1:
                    //altas
                    break;
                case 2:
                    //bus binaria
                    break;
                case 3:
                    //reporte
                    break;
                case 4:
                    //mod
                    break;
                case 5:
                    System.out.println("Regresando al menu principal.");
                    break;
            } 
        }
    }

    private void menuMaterias() 
    {
        while(op2!=5)
        {  
            
            // mostrar opciones
            
            switch (op2) 
            {
                case 1:
                    //altas
                    break;
                case 2:
                    //bus binaria
                    break;
                case 3:
                    //reporte
                    break;
                case 4:
                    //mod
                    break;
                case 5:
                    System.out.println("Regresando al menu principal.");
                    break;
            } 
        }
    }

    private void procesoInscripcion()
    {
        pasoUno();  
    }

    private void pasoUno() 
    {
        System.out.print("Desea inscribir alumno? [S/N]: ");
        if(true)
        {
            pasoDos();
        }else
        {
            System.out.println("Fin del proceso de inscripcion");
        }
    }

    private void pasoDos() 
    {
        System.out.print("Ingresa numero de control del alumno: ");
        //
        //if(al.bus(scan)!=null, cero o lo que sea)
            //guarda alumno en insAl
            pasoTres();
        //else
        //no se encontro ese alumno
        //Desea buscar otro alumno? [S/N]:
            //if true
            //pasoDos
            //else System.out.println("Fin del proceso de inscripcion");      
    }   

    private void pasoTres() 
    {
        //if(ma.bus(scan)!=null, cero o lo que sea)
            //guarda clase en insMa si es necesario
            //graba insAL e insMa en el archivo
            pasoCuatro();
        //else
        //no se encontro esa materia
        pasoCuatro();
    }

    private void pasoCuatro() 
    {
        //elegir otra materia [y/n]
        //if true
        pasoTres();
        //else
        pasoUno();
    }
}
