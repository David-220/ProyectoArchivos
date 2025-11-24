package mx.edu.itcelaya.proyectoarchivos;

public class RegistroAcademico {
    GestorDeArchivos arch = new GestorDeArchivos();
    
    //Los nombres de todos los archivos juntos
    //porque inscripcion tiene que acceder a su informacion
    String[] nomArch = {"Alumnos.dat","Materias.dat","Inscripciones.dat"};
    //dependiendo a que archivo te refieras
    int nArch;
    
    //tamaño registro todas las clases lo usan
     int tamReg;
    
    //Aqui se guardaran los formatos de los datos
    //es array porque hay distintos numeros de datos
    String[] formatos;
    
    String[] instruccionesAltas;
    //en el constructor de las clases hijas se definen los formatos
    public RegistroAcademico(String[] formatos, String[] instruccionesAltas) {
        this.formatos = formatos;
        this.instruccionesAltas = instruccionesAltas;
    }
    
    
    
    void alta()
    {
        arch.altaReg(nomArch[nArch], tamReg, formatos, instruccionesAltas);
    }
    
}
