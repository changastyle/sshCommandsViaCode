package automated;

import java.util.ArrayList;
import java.util.List;

public class ExportDB
{
    public static void main(String args[])
    {
        String ip = "viewdevscompany.com";
        System.out.println("ping (" + ip + "): " + wsSSH.ping(ip));
        
        
        List arrComandos = new ArrayList<String>();
        arrComandos.add("ls");
        arrComandos.add("cd /root/BC");
        arrComandos.add("mkdir may-2019");
        arrComandos.add("cd  may-2019");
        arrComandos.add("ls");
        arrComandos.add("mysqldump -u root -p itplanding > kk.sql");
        wsSSH.sshSession(ip, "root", "descargar",22 ,arrComandos,"#");
        wsSSH.exit();
    }
}
