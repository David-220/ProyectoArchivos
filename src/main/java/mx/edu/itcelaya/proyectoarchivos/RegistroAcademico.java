package mx.edu.itcelaya.proyectoarchivos;

public abstract class RegistroAcademico {
    GestorDeArchivos arch = new GestorDeArchivos();
    
    String nomAl = "Alumnos.dat";
    String nomMa = "Materias.dat";
    String nomIns = "Inscripciones.dat";
    int tamReg;
    
    
    
    void alta(){
        arch.escribe();
    }
    
}
