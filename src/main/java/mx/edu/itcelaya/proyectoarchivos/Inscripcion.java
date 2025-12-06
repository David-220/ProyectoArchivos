package mx.edu.itcelaya.proyectoarchivos;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Inscripcion {
    private String numCtrl, claveMat, nomArch, nomRepo, tituloCentrado, subTitulos;
    private int tamReg;
    private Alumnos al = new Alumnos();
    private Materias mat = new Materias();
    private Scanner scan = new Scanner(System.in);

    public Inscripcion(){
        nomArch = "Inscripciones.dat";
        nomRepo = "ReporteInscripciones.txt";
        tamReg = 20;
        tituloCentrado = (String.format("%12s", nomArch));
        subTitulos = "  " + String.format("%-8.8s","numCtrl") +"    " + String.format("%-8.8s","claveMat") +"    ";
    }

    public boolean busNumCtrl(){
        try (RandomAccessFile canal = new RandomAccessFile(al.nomArch, "r")){
            System.out.print("Ingresa el numero de control del alumno: ");
            numCtrl = scan.nextLine();
            return al.existeNumeroControl(canal, numCtrl);
        } catch (Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return false;
    }

    //Metodo que busca si existe la clave
    public boolean busClaMat(){
        try (RandomAccessFile canal = new RandomAccessFile(mat.nomArch, "r")){
            System.out.print("Ingresa la clave de la materia: ");
            claveMat = scan.nextLine();
            return mat.existeClaveMat(canal, claveMat);
        } catch (Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }
        return false;
    }

    
   public void reporte() {
        try (RandomAccessFile entradaIns = new RandomAccessFile(nomArch, "r");
             PrintWriter salida = new PrintWriter(new FileWriter(nomRepo));
             RandomAccessFile entradaAl = new RandomAccessFile(al.nomArch, "r");
             RandomAccessFile entradaMat = new RandomAccessFile(mat.nomArch, "r")
            )
        {
            String[] datosIns;
            String[] datosMat;
            String[] datosAlumn;

            boolean flag;

            int numRegMat = (int) entradaMat.length()/mat.tamReg;
            int numRegIns = (int) entradaIns.length()/tamReg;
            int numRegAl = (int) entradaAl.length()/al.tamReg;

            System.out.println(tituloCentrado);
            salida.println(tituloCentrado);

            //ciclo que lee cada linea
            for(int i = 0; i < numRegAl; i++)
            {
                flag = true;
                //Imprimir datos del alumno
                datosAlumn = al.leerReg(entradaAl, i);

                for(int j = 0; j < numRegIns; j++){
                    datosIns = leerReg(entradaIns, j);
                    if(datosIns[0].equals(datosAlumn[0]) && flag){
                        flag = false;
                    }
                }


                if(!flag) {

                    //Imprimir datos del alumno
                    System.out.println("Numero de control: " + datosAlumn[0]);
                    salida.println("Numero de control: " + datosAlumn[0]);
                    System.out.println("Nombre del alumno: " + datosAlumn[1]);
                    salida.println("Nombre del alumno: " + datosAlumn[1]);

                    System.out.println();
                    salida.println();

                    //imprime los subtitulos de materias
                    System.out.println(mat.subTitulos);
                    salida.println(mat.subTitulos);

                //ciclo que imprime cada Materia
                    for(int j = 0; j < numRegIns; j++)
                    {
                        datosIns = leerReg(entradaIns, j);
                        for(int k = 0; k < numRegMat; k++){
                            datosMat = mat.leerReg(entradaMat, k);
                            if(datosIns[1].equals(datosMat[0]) && datosIns[0].equals(datosAlumn[0])){
                                for(int l = 0; l < 3; l++){
                                    System.out.print("  " + datosMat[l] + "  ");
                                    salida.print("  " + datosMat[l] + "  ");

                                }
                            System.out.println();
                            salida.println();
                            }
                        }
                    }
                }
                System.out.println();
                salida.println();
            }
            entradaIns.close();
            entradaMat.close();
            entradaAl.close();

        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }

    //inscribir el alumno con sus materias
    public void guardaReg(){

        try (RandomAccessFile canal = new RandomAccessFile(nomArch, "rw")) {
            int nReg=(int)canal.length()/tamReg;
            canal.seek(nReg * tamReg);
            canal.writeUTF(String.format("%-8.8s", numCtrl));
            canal.writeUTF(String.format("%-8.8s", claveMat));
            canal.close();
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }

    }

    public void ordenar() {
        try (RandomAccessFile canal = new RandomAccessFile(nomArch, "rw")) {
            boolean ban;
            String[] datos1;
            String[] datos2;
            int totalRegistros = (int)(canal.length() / tamReg);

            for(int i = 1; i < totalRegistros; i++) {
                ban = true;
                for(int j = 1; j <= (totalRegistros - i); j++) {
                    datos1 = leerReg(canal, j-1);
                    datos2 = leerReg(canal, j);

                    if(datos1[0].compareTo(datos2[0]) > 0) {
                        // Intercambiar registros
                        canal.seek(j * tamReg);
                        for(int n = 0; n < 2; n++) {
                            canal.writeUTF(datos1[n]);
                        }

                        canal.seek((j-1) * tamReg);
                        for(int n = 0; n < 2; n++) {
                            canal.writeUTF(datos2[n]);
                        }
                        ban = false;
                    }
                }
                if(ban) break;
            }
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }

        String[] leerReg(RandomAccessFile canal, int nReg) throws IOException
    {
        String[] datosLeidos = new String[2];
        //posicionamos el cursor
        canal.seek(nReg*tamReg);
        for(int i = 0; i<2; i++)
        {
            datosLeidos[i] = canal.readUTF();
        }
        return datosLeidos;
    }


}
