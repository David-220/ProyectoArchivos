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
    boolean op3 = true;
    
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
            System.out.println("Menu de opciones");
            System.out.println("1) Alumnos");
            System.out.println("2) Materias");
            System.out.println("3) Inscripcion");
            System.out.println("4) Salida");
            System.out.println();
            System.out.print("Ingresa la opcion: ");
            
            op1=recibeNum();
            

            System.out.println();
            
            switch (op1) 
                
            {
                case 1://Alumnos
                    menuAlumnos();
                    break;
                case 2://Materias
                    menuMaterias();
                    break;
                case 3://Inscripcion
                    System.out.println("Opcion no habilitada aun.");
                    //pasoUno();
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
            
            System.out.println("Menu de Alumnos");
            System.out.println("1) Altas");
            System.out.println("2) Consulta");
            System.out.println("3) Reporte");
            System.out.println("4) Modificacion");
            System.out.println("5) Salida");
            System.out.println();
            System.out.print("Ingresa la opcion: ");
            
            op2=recibeNum();
            
            System.out.println();
            
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
                    System.out.println();
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
            
            System.out.println("Menú de Materias");

            System.out.println("1) Altas");
            System.out.println("2) Consulta");
            System.out.println("3) Reporte");
            System.out.println("4) Modificacion");
            System.out.println("5) Salida");
            System.out.println();
            System.out.print("Ingresa la opcion: ");
            
            op2=recibeNum();
            
            System.out.println();
            
            switch (op2) 
            {
                case 1://Altas
                    ma.alta();
                    break;
                case 2://Consulta
                    ma.ordenar();
                    ma.busqueda();
                    break;
                case 3://Reporte
                    ma.ordenar();
                    ma.reporte();
                    break;
                case 4://Modificacion
                    ma.ordenar();
                    ma.modificaciones();
                    break;
                case 5://Salida
                    //ordenar por si las moscas
                    ma.ordenar();
                    System.out.println("Regresando al menu principal.");
                    System.out.println();
                    break;
                default:
                    System.out.println("Ingrese una opcion valida");
                    System.out.println();
            } 
        }
    }

    
    boolean decision(String indicacion)
    {
        String siONo = "";
        while(true){
            System.out.println();
            System.out.print(indicacion);
            siONo = scan.nextLine().trim().toLowerCase();

            if (siONo.equals("s")) {
                return true;
            } else if (siONo.equals("n")) {
                return false;
            } else {
                System.out.println("️Ingrese una opcion valida");
            }
        }
    }

    private void pasoUno() 
    {
        if(decision("Desea inscribir alumno? [S/N]: "))
        {
            pasoDos();
        }else
        {
            System.out.println("Fin del proceso de inscripcion");
        }
    }

    private void pasoDos() 
    {
        /*
        necesitamos un metodo booleano que regrese true si se encontro el numCtrl
        recordar poner un mensaje de encontrado o no encontrado
        if(inscri.busNumCtrl){
            pasoTres();
        }     
        else if(decision("Desea buscar otro numero de control? [S/N]: "))
        {
            pasoDos();
        }else
        {
            System.out.println("Fin del proceso de inscripcion");
        }
        */
    }   

    private void pasoTres() 
    {
        /*
        necesitamos un metodo booleano que regrese true si se encontro la materia
        recordar poner un mensaje de encontrado o no encontrado
        if(inscri.busMateria){
            inscri.guardaReg
            pasoCuatro();
        }else {
            pasoCuatro();
        }
        */
    }

    private void pasoCuatro() 
    {
        if(decision("Desea elegir otra materia [S/N]: ")){
            pasoTres();
        }else{
            pasoUno();
        }
    }
}
