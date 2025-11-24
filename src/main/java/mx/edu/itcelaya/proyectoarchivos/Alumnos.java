package mx.edu.itcelaya.proyectoarchivos;


public class Alumnos extends RegistroAcademico{
    
    public Alumnos(String... formatos) {
        //definimos arreglos
        super
        (
                //los formatos
                new String[] {"%-8.8s","%-40.40s","%-2.2s"}, 
                //las instrucciones que se dan cuando damos de alta
                new String[] {"Ingresa num de control: ",
                              "Ingresa nombre del alumno: ",
                              "Ingresa semestre del alumno: "}
        );
        //tamaño rengistro dependiendo del formato
        tamReg = 56;
        //numero de archivo 0 porque es alumno
        nArch = 0;
    }

    @Override
    void alta() 
    {  
        super.alta();
    }
}
