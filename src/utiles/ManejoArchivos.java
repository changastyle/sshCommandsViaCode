package utiles;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManejoArchivos
{
    public static List<String> read(String ruta)
    {
        List<String> lineas = new ArrayList<>();
        if(ruta != null)
        {
            File file = new File(ruta);
            if(file != null)
            {
                if(file.exists() && file.isFile() && file.canRead())
                {
                    try 
                    {
                        Scanner sc = new Scanner(file);
                        
                        while (sc.hasNextLine())
                        { 
                            String linea = sc.nextLine();
                            lineas.add(linea);
                            //System.out.println(sc.nextLine());
                        }
                    }
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        return lineas;
    }
  
}
