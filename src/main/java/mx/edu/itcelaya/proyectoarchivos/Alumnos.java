package mx.edu.itcelaya.proyectoarchivos;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Alumnos extends RegistroAcademico
{    
    public Alumnos() {
        
        nomArch = "Alumnos.dat";
        
        nomRepo = "ReporteAlumnos.txt";
        
        tamReg = 58;
        
        String tituloCentrado = (String.format("%37s", nomArch));
            
        String subTitulos = "  " + String.format("%-8.8s","numCtrl") +"    " + String.format("%-40.40s","nombre") +"    "+ String.format("%-4.4s","sem")+"  ";
           
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
        canal.seek(nReg*tamReg);
        System.out.print("Ingresa num de control: ");
        canal.writeUTF(String.format("%-8.8s",scan.nextLine()));
        System.out.print("Ingresa nombre del alumno: ");
        canal.writeUTF(String.format("%-40.40s",scan.nextLine()));
        System.out.print("Ingresa semestre del alumno: ");
        canal.writeUTF(String.format("%-4.4s",scan.nextLine()));
    }
    
    String[] leerReg(RandomAccessFile canal, int nReg) throws IOException
    {
        String[] datosLeidos = new String[3];
        //posicionamos el cursor
        canal.seek(nReg*tamReg);

        //
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
                    System.out.print("  " + datos + "  ");
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
        String bus;
        System.out.print("ingresa numero de control: ");
        bus = scan.nextLine();
        li = 0;
        ls = (int)(canal.length() / tamReg)-1;
        do 
        {
            pm = (li+ls)/2;
            canal.seek(pm*tamReg);
            if (canal.readUTF().compareTo(bus) < 0) {
                li = pm+1;
            }
            else {
                ls = pm-1;
            }
            canal.seek(pm*tamReg);
        }while(!bus.equals(canal.readUTF()) && li<=ls);
        
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
                
                ingresaDatos(canal, nReg);
                
                System.out.println();
                
            }
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }

    @Override
    public void ordenar()
    {
        try (RandomAccessFile canal = new RandomAccessFile(nomArch, "rw")) 
        {
            boolean ban;
            String[] datos1;
            String[] datos2;
            for(int i=1;i< (int)(canal.length())/tamReg;i++)
            {
                //reinicio de bandera
                ban = true;
                for(int j=1;j<=((int)(canal.length()/tamReg)-i);j++)
                {
                    datos1 = leerReg(canal, j-1);
                    datos2 = leerReg(canal, j);
                    if(datos1[0].compareTo(datos2[0])>0)
                    {
                        // Se graban en los registros cambiados
                        canal.seek(j*tamReg);
                        for(int n = 0; i<3; i++)
                            {
                                canal.writeUTF(datos1[n]);
                            }
                        canal.seek(j-1*tamReg);
                        for(int n = 0; i<3; i++)
                            {
                                canal.writeUTF(datos2[n]);
                            }

                        //bandera de que hubo cambio
                        ban = false;
                    }
                }

                //rompe ciclo si no hubo cambio
                if(ban)
                {
                   break;
                }

            }  
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
        }
    }
}