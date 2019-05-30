package automated;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SCP
{
    public static void main(String args[])
    {
        String usuario = "root";
        String pass = "tecacc";
        String host = "192.168.5.4";
        int port = 22;

        String rutaLocal = "C:\\transformer\\xx.txt";
        String dirRemoto = "/tmp/";
        enviar(host, usuario, pass, port, rutaLocal, dirRemoto);
    }
    public static boolean enviar(String host , String usuario , String pass , int port, String rutaLocal, String dirRemoto) 
    {
        boolean ok = false;
        try
        {
            
            JSch jsch = new JSch();
            Session session = null;
            session = jsch.getSession(usuario,host,port);
            session.setPassword(pass);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            
            ChannelSftp channel = null;
            channel = (ChannelSftp)session.openChannel("sftp");
            channel.connect();
            
            File localFile = new File(rutaLocal);
            
            //If you want you can change the directory using the following line.
            channel.cd(dirRemoto);
            channel.put(new FileInputStream(localFile),localFile.getName());
            channel.disconnect();
            session.disconnect();
            ok = true;
        }
        catch (Exception e)
        {
            ok = false;
            e.printStackTrace();
        }
        
        return ok;
    }
}
