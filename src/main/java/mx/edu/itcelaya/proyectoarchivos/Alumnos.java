package mx.edu.itcelaya.proyectoarchivos;

import java.io.*;

public class Alumnos extends RegistroAcademico
{    
    
    public Alumnos() {
        
        nomArch = "Alumnos.dat";
        
        nomRepo = "ReporteAlumnos.txt";
        
        tamReg = 58;
        
        tituloCentrado = (String.format("%37s", nomArch));
            
        subTitulos = "  " + String.format("%-8.8s","numCtrl") +"    " + String.format("%-40.40s","nombre") +"    "+ String.format("%-4.4s","sem")+"  ";
           
    }
    

    @Override
    public void alta() 
    {
        try (RandomAccessFile canal = new RandomAccessFile(nomArch, "rw")) {
            
            int nReg=(int)canal.length()/tamReg;
            
            ingresaDatos(canal,nReg);
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }    
    }
    void ingresaDatos(RandomAccessFile canal, int nReg) throws IOException 
    {
        String numControl;
        boolean numCtrlValido=false;

        // Checar que el numero de control no se repita, si lo hace repetir hasta que sea valido
        do {
            System.out.print("Ingresa el numero de control: ");
            numControl = scan.nextLine().trim();

            if(existeNumeroControl(canal, numControl)) {
                System.out.println("Error: El numero de control "+numControl+" ya existe");
                System.out.println("Ingresa un numero de control diferente");
            } else {
                numCtrlValido = true;
            }
        } while (!numCtrlValido);
        //Como aseguramos que es valido el nroCtrl ya podemos escribir a gusto la modificacion
        canal.seek(nReg * tamReg);
        canal.writeUTF(String.format("%-8.8s", numControl));
        System.out.print("Ingresa nombre del alumno: ");
        canal.writeUTF(String.format("%-40.40s", scan.nextLine()));
        System.out.print("Ingresa semestre del alumno: ");
        canal.writeUTF(String.format("%-4.4s", scan.nextLine()));
    }
    
    void ingresaModificaciones(RandomAccessFile canal, int nReg,String nroCtrl) throws IOException
    {//Metodo disenado para no cambiar el numero de control del alumno
        canal.seek(nReg*tamReg);
        canal.writeUTF(String.format("%-8.8s",nroCtrl));
        System.out.print("Ingresa nombre del alumno: ");
        canal.writeUTF(String.format("%-40.40s",scan.nextLine()));
        System.out.print("Ingresa semestre del alumno: ");
        canal.writeUTF(String.format("%-4.4s",scan.nextLine()));
        System.out.println();
    }
    
    boolean existeNumeroControl(RandomAccessFile canal, String numCtrl) throws IOException 
    {
        int totalRegistros = (int)(canal.length() / tamReg);

        for(int i = 0; i < totalRegistros; i++) {
            canal.seek(i * tamReg);
            String numControlExistente = canal.readUTF().trim();
            if(numControlExistente.equals(numCtrl)) {
                return true;  //Existe
            }
        }
        return false;  //No joven, no existe xd
    }
    
    String[] leerReg(RandomAccessFile canal, int nReg) throws IOException
    {
        String[] datosLeidos = new String[3];
        
        canal.seek(nReg*tamReg);

        for(int i = 0; i<3; i++)
        {
            datosLeidos[i] = canal.readUTF();
        }
            
        return datosLeidos;
        
    }

    @Override
    public void busqueda() 
    {
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
                System.out.println();
            }
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
    
    int busRenglon(RandomAccessFile canal) throws IOException{
        int li, ls, pm;
        //boolean flag1; no la use
        System.out.print("ingresa numero de control: ");
        String bus = scan.nextLine();
        System.out.println("bus es: "+bus);
        li = 0;
        String aux;
        System.out.println("li "+li);
        ls = (int)(canal.length() / tamReg)-1;
        System.out.println("ls "+ls);
        do 
        {
            pm = (li+ls)/2;
            System.out.println("pm "+pm);
            canal.seek(pm*tamReg);
            aux = canal.readUTF();
            System.out.println("aux " + aux);
            if (aux.compareTo(bus) < 0) {
                
                li = pm+1;
                System.out.println("li "+li);
            }
            else {
                ls = pm-1;
                System.out.println("ls "+ls);
            }
            canal.seek(pm*tamReg);
        }while(!bus.equals(aux) && li<=ls);
        
        if(bus.equals(canal.readUTF()))
        {
            return pm;
        }
        else
        {
            System.out.println(bus + " no existe");
            return -1;
        }
    }

    @Override
    public void reporte() 
    {
        try (RandomAccessFile entrada = new RandomAccessFile(nomArch, "r");
             PrintWriter salida = new PrintWriter(new FileWriter(nomRepo))
                ) {
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
            
            System.out.println();
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }

    @Override
    public void modificaciones() 
    {
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
                String nC=canal.readUTF().trim();  // Leer el numero de control
                
                ingresaModificaciones(canal, nReg,nC);
                
                System.out.println();
                
            }
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }

    @Override
    public void ordenar() 
    {
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