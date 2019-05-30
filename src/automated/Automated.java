package automated;

import java.util.ArrayList;
import java.util.List;
import utiles.ManejoArchivos;
import utiles.ManejoArchivos;

public class Automated {

    public static void main(String[] args) 
    {
        String ip = "10.253.9.56";
        System.out.println("ping (" + ip + "): " + wsSSH.ping(ip)); 
        
        List arrComandos = new ArrayList<String>();
        arrComandos.add("cat /etc/config/net");
        wsSSH.sshSession(ip, "root", "tecacc",2200 ,arrComandos,"eee");
       
        
        /*
        List<String> lineasEntrada = ManejoArchivos.read("C:\\update-masivo\\lista-parche");
        List<String> lineasError = new ArrayList<>();
        
        if(lineasEntrada != null)
        {
            if(lineasEntrada.size() > 0)
            {
                for(String strLinea : lineasEntrada)
                {
                    System.out.println("------- Linea: " +  strLinea);
                    
                    List arrComandos = new ArrayList<String>();
                    arrComandos.add("cd /root/update_flex_android");
                    arrComandos.add("./patch_flex.sh usb-4g " + strLinea);
                    wsSSH.sshSession("192.168.5.6", "root", "tecacc",arrComandos,"LISTO !!!");
                    
                    System.out.println("------- FIN Linea: " +  strLinea);
                    
                }
            }
        }*/
        
        
        //System.out.println("conectando..");
        //wsSSH.sshSession("192.168.5.6", "root", "tecacc", "cd /root/update_flex_android; ./patch_flex.sh uftpd-cnq lista_cnq");
        //wsSSH.sshSession("192.168.5.4", "root", "tecacc", "cd /root/update_flex_android; ./patch_flex.sh uftpd-cnq lista_cnq");
    }
    
}
