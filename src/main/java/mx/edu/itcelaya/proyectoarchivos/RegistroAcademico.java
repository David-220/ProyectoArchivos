package mx.edu.itcelaya.proyectoarchivos;

public class RegistroAcademico {
    GestorDeArchivos arch = new GestorDeArchivos();
    
    //Los nombres de todos los archivos juntos
    //porque inscripcion tiene que acceder a su informacion
    String[] nomArch = {"Alumnos.dat","Materias.dat","Inscripciones.dat"};
    String[] nomRepo = {"ReporteAlumnos.txt","ReporteMaterias.txt","ReporteInscripciones.txt"};
    //dependiendo a que archivo te refieras
    int nArch;
    
    String[] atriCapturables;
    
    //tamaño registro todas las clases lo usan
     int tamReg;
    
    //Aqui se guardaran los formatos de los datos
    //es array porque hay distintos numeros de datos
    String[] formatos;
    
    String[] instruccionesAltas;
    //en el constructor de las clases hijas se definen los formatos
    public RegistroAcademico(String[] atriCapturables, String[] formatos, String[] instruccionesAltas) {
        this.atriCapturables = atriCapturables;
        this.formatos = formatos;
        this.instruccionesAltas = instruccionesAltas;
    }
    
    
    
    void alta()
    {
        arch.altaReg(nomArch[nArch], tamReg, formatos, instruccionesAltas);
    }
    
    
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
}
