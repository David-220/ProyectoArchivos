package mx.edu.itcelaya.proyectoarchivos;

import java.util.Scanner;

public class Menu 
{
    Scanner scan = new Scanner(System.in);
    Alumnos al = new Alumnos();
    Materias ma = new Materias();
    Inscripcion ins = new Inscripcion();
    int op1 = 0;
    int op2 = 0;
    
    public Menu() 
    {
        mainMenu();
    }
    
    public int recibeNum(){
        
        try 
        {
            //valida la entrada de un numero
            int numero = Integer.parseInt(scan.nextLine().trim());
            return numero; 
            
        } catch (NumberFormatException e) 
        {  
            //si da error regresara al menu y dara el mensaje default
            System.out.println("Error: Intetnta de nuevo con un numero entero.");
            return 0;
        }
        
    }

    private void mainMenu() 
    {        
        do
        {    
            System.out.println("Menú de opciones");
            System.out.println("1) Alumnos");
            System.out.println("2) Materias");
            System.out.println("3) Inscripcion");
            System.out.println("4) Salida");
            System.out.println();
            System.out.print("Ingresa la opcion:");
            
            op1=recibeNum();
            
            switch (op1)//Menu de  
            {
                case 1://Alumnos
                    menuAlumnos();
                    break;
                case 2://Materias
                    //menuMaterias();
                    break;
                case 3://Inscripcion
                    //procesoInscripcion();
                    break;
                case 4://Fin del programa
                    System.out.println("Fin del proceso");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    System.out.println();
            }            
        }while(op1!=4);         
    }

    private void menuAlumnos() 
    {
        do
        {  
            
            System.out.println("Menú de Alumnos");
            System.out.println("1) Altas");
            System.out.println("2) Consulta");
            System.out.println("3) Reporte");
            System.out.println("4) Modificacion");
            System.out.println("5) Salida");
            System.out.println();
            System.out.print("Ingresa la opcion:");
            
            op2=recibeNum();
            
            switch (op2) 
            {
                case 1: //Altas a alumnos
                    al.alta();
                    break;
                case 2: //Consultas de alumnos
                    al.ordenar();
                    al.busqueda();
                    break;
                case 3: //Reporte de alumnos
                    al.ordenar();
                    al.reporte();
                    break;
                case 4: //Modificar alumnos
                    al.ordenar();
                    al.modificaciones();
                    break;
                case 5: //Salir y dejamos el archivo ordenado
                    al.ordenar();
                    System.out.println("Regresando al menu principal.");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    System.out.println();
            } 
        }while(op2!=5);
    }

    private void menuMaterias() 
    {
        while(op2!=5)
        {  
            
            System.out.println("Menú de Alumnos");
            System.out.println("1) Altas");
            System.out.println("2) Consulta");
            System.out.println("3) Reporte");
            System.out.println("4) Modificacion");
            System.out.println("5) Salida");
            System.out.println();
            System.out.print("Ingresa la opcion:");
            
            op2=recibeNum();
            
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
                    //ordenar por si las moscas
                    System.out.println("Regresando al menu principal.");
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    System.out.println();
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
