package mx.edu.itcelaya.proyectoarchivos;


public class Alumnos extends RegistroAcademico{
    
    public Alumnos() {
        //definimos arreglos
        super
        (   
                //nombres de los atributos que se ingresaran
                new String[] {"nroCtrl","nombre","sem"}, 
                //sus formatos
                new String[] {"%-8.8s","%-40.40s","%-4.4s"}, 
                //las instrucciones que se dan cuando damos de alta
                new String[] {"Ingresa num de control: ",
                              "Ingresa nombre del alumno: ",
                              "Ingresa semestre del alumno: "}
        );
        //tamaño rengistro dependiendo del formato
        tamReg = 58;
        //numero de archivo 0 porque es alumno
        nArch = 0;
    }
    
}
