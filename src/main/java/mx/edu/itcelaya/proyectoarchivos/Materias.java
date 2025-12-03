package mx.edu.itcelaya.proyectoarchivos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;

public class Materias extends RegistroAcademico {

    public Materias() 
    {    
        nomArch = "Materias.dat";
        nomRepo = "ReporteMaterias.txt";
        tamReg = 55;
        tituloCentrado = (String.format("%35s", nomArch));   
        subTitulos = "  " + String.format("%-8.8s","Clave") +"    " + String.format("%-37.37s","Materia") +"    "+ String.format("%-8.8s","Creditos")+"  ";

    }
    
    @Override
    public void alta() 
    {
        try (RandomAccessFile canal = new RandomAccessFile(nomArch, "rw")) {
            //calculamos el renglon
            int nReg=(int)canal.length()/tamReg;
            
            ingresaDatos(canal,nReg);
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }    
    }
    
    void ingresaDatos(RandomAccessFile canal, int nReg) throws IOException 
    {
        String claveMat;
        boolean numCtrlValido=false;

        // Checar que el numero de control no se repita, si lo hace repetir hasta que sea valido
        do {
            System.out.print("Ingresa la clave de la materia: ");
            claveMat = scan.nextLine().trim();

            if(existeClaveMat(canal, claveMat)) {
                System.out.println("Error: La clave de la materia "+claveMat+" ya existe");
                System.out.println("Ingresa una clave diferente");
            } else {
                numCtrlValido = true;
            }
        } while (!numCtrlValido);
        //Como aseguramos que es valido el nroCtrl ya podemos escribir a gusto la modificacion
        canal.seek(nReg * tamReg);
        canal.writeUTF(String.format("%-8.8s", claveMat));
        System.out.print("Ingresa nombre de la materia: ");
        canal.writeUTF(String.format("%-40.40s", scan.nextLine()));
        System.out.print("Ingresa el numero de creditos: ");
        canal.writeUTF(String.format("%-1.1s", scan.nextLine()));
    }
    
    boolean existeClaveMat(RandomAccessFile canal, String claveMat) throws IOException 
    {
        int totalRegistros = (int)(canal.length() / tamReg);

        for(int i = 0; i < totalRegistros; i++) {
            canal.seek(i * tamReg);
            String numControlExistente = canal.readUTF().trim();
            if(numControlExistente.equals(claveMat)) {
                return true;  //Existe
            }
        }
        return false;  //No joven, no existe xd
    }
    
    @Override
    public void busqueda() {
        try (RandomAccessFile canal = new RandomAccessFile(nomArch, "r")) {
            System.out.println("Para iniciar la busqueda");
            int nReg = busRenglon(canal);
            
            if(nReg>=0)
            {
                String[] datos = leerReg(canal, nReg);
                System.out.println("Se encontro el registro");
                System.out.println();
                System.out.println(subTitulos);
                for(int j=0; j<3; j++)
                {
                    System.out.print("  " + datos[j] + "  ");
                }
                System.out.println();
                
            }
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
    
    int busRenglon(RandomAccessFile canal) throws IOException{
        int li, ls, pm;
        //boolean flag1; no la use
        System.out.print("ingresa clave de la materia: ");
        String bus = scan.nextLine();
        li = 0;
        String aux;
        ls = (int)(canal.length() / tamReg)-1;
        do 
        {
            pm = (li+ls)/2;
            canal.seek(pm*tamReg);
            aux = canal.readUTF().trim();
            
            if(bus.equals(aux))
            {
                return pm;
            }else if (aux.compareTo(bus) < 0) {
                
                li = pm+1;
            }
            else {
                ls = pm-1;
            }
            canal.seek(pm*tamReg);
        }while(li<=ls);
        
        {
            System.out.println(bus + " no existe");
            return -1;
        }
    }
    
    String[] leerReg(RandomAccessFile canal, int nReg) throws IOException
    {
        String[] datosLeidos = new String[3];
        //posicionamos el cursor
        canal.seek(nReg*tamReg);
        for(int i = 0; i<3; i++)
        {
            datosLeidos[i] = canal.readUTF();
        }  
        return datosLeidos;
    }

    @Override
    public void reporte() {
        try (RandomAccessFile entrada = new RandomAccessFile(nomArch, "r");
             PrintWriter salida = new PrintWriter(new FileWriter(nomRepo))
            ) 
        {
            String[] datosRepo;
            
            System.out.println(tituloCentrado);
            salida.println(tituloCentrado);
            
            System.out.println(subTitulos);
            salida.println(subTitulos);
            //ciclo que lee cada linea
            for(int i=0;i< (int)(entrada.length())/tamReg;i++)
            {
                datosRepo = leerReg(entrada, i);
                //ciclo que imprime cada linea
                for(int j=0; j<3; j++)
                {
                    System.out.print("  " + datosRepo[j] + "  ");
                    salida.print("  " + datosRepo[j] + "  ");
                }
                System.out.println();
                salida.println();
            }
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }

    @Override
    public void modificaciones() {
        try (RandomAccessFile canal = new RandomAccessFile(nomArch, "rw")) 
        {
            System.out.println("Para iniciar la modificacion");
            int nReg = busRenglon(canal);
            
            if(nReg>=0)
            {
                System.out.println("Se encontro el registro");
                System.out.println("listo para modificar");
                System.out.println();
                
                canal.seek(nReg * tamReg); 
                String cM=canal.readUTF().trim();  // Leer la clave de la materia
                
                ingresaModificaciones(canal, nReg,cM);
                System.out.println();
            }
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
    
    void ingresaModificaciones(RandomAccessFile canal, int nReg,String claveMat) throws IOException
    {//Metodo disenado para no cambiar la clave de la materia
        canal.seek(nReg*tamReg);
        canal.writeUTF(String.format("%-8.8s",claveMat));
        System.out.print("Ingresa la el nombre la materia: ");
        canal.writeUTF(String.format("%-40.40s",scan.nextLine()));
        System.out.print("Ingresa los creditos de la materia: ");
        canal.writeUTF(String.format("%-1.1s",scan.nextLine()));
    }

    @Override
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
                        for(int n = 0; n < 3; n++) {
                            canal.writeUTF(datos1[n]);
                        }

                        canal.seek((j-1) * tamReg);
                        for(int n = 0; n < 3; n++) {
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
    
}
