package automated;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static java.lang.Compiler.command;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

public class wsSSH 
{
    public static List<String> ssh( String host, String user, String pass, String comando)
    {
        List<String> lineasDeSalida = new ArrayList<String>();
        
        String password = pass;
        int port=22;

        
        try
        {
            JSch jsch = new JSch();  
            //System.getProperty("user.name")
            
            Session session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            
            //FINGERPRINT
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            
            session.connect();
            
            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(comando);
            
            //channel.setInputStream(System.in);
            channel.setInputStream(null);
            
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();

            channel.connect();
            
            
            byte[] tmp=new byte[1024];
            while(true)
            {
                while(in.available()>0)
                {
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)
                    {
                        break;
                    }
                    
                    String aux = new String(tmp,0,i);
                    lineasDeSalida.add(aux);
                    System.out.print(aux);
                }
                if(channel.isClosed())
                {
                    if(in.available()>0)
                    {
                        continue;
                    } 
                    //System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try
                {
                    Thread.sleep(1000);
                }
                catch(Exception ee)
                {
                    ee.printStackTrace();
                }
            }
            
        channel.disconnect();
        session.disconnect();
            
    }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return lineasDeSalida;
    }
    
    public static List<String> sshSession( String host, String user, String pass, String comando)
    {
        List<String> lineasDeSalida = new ArrayList<String>();
        
        String password = pass;
        int port=22;

        
        try
        {
            JSch jsch = new JSch();  

            Session session=jsch.getSession(user, host, 22);
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            
            session.setPassword(pass);

           // session.setUserInfo(ui);

            session.connect(30000);   // making a connection with timeout.

            Channel channel = session.openChannel("shell");

            channel.setInputStream(System.in);
            
            InputStream in=channel.getInputStream();

            channel.connect();
            List arrComandos1 = new ArrayList<String>();
            arrComandos1.add("cd /root/update_flex_android");
            arrComandos1.add("./patch_flex.sh uftpd-cnq lista_cnq");
            arrComandos1.add("tecacc");
            sendCommands(channel,arrComandos1 );
            
            byte[] tmp=new byte[1024];
            while(true)
            {
                while(in.available()>0)
                {
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)
                    {
                        break;
                    }
                    
                    String aux = new String(tmp,0,i);
                    lineasDeSalida.add(aux);
                    
                    if(aux.contains("password:"))
                    {
                        System.out.println(aux);
                        List arrComandos = new ArrayList<String>();
                        arrComandos.add("tecacc");
                        sendCommands(channel,arrComandos );
                    }
                    else
                    {
                        System.out.print(aux);
                    }
                    
                }
                if(channel.isClosed())
                {
                    if(in.available()>0)
                    {
                        continue;
                    } 
                    //System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try
                {
                    Thread.sleep(1000);
                }
                catch(Exception ee)
                {
                    ee.printStackTrace();
                }
            }
            
           // channel.setOutputStream(System.out);

            //channel.connect();
            channel.connect(3*1000);

            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return lineasDeSalida;
    }
    private static void sendCommands(Channel channel, List<String> commands){

    try{
        PrintStream out = new PrintStream(channel.getOutputStream());

        //out.println("#!/bin/bash");
        for(String command : commands){
            out.println(command);
            //out.println(command);
        }
        //out.println("exit");

        out.flush();
    }catch(Exception e){
        System.out.println("Error while sending commands: "+ e);
    }
    }
    
    public static boolean ping(String direccionIP )
    {
        boolean vivo = false;
        long inicio = System.currentTimeMillis();
        List<String> arrSalida = ssh("192.168.5.4","root","tecacc","ping " + direccionIP + " -c 1");
        
        //1 - GUARDO TODA LA SALIDA DEL SSH EN UNA VARIABLE ACUMULADOR:
        String acumulador = "";
        for(String linea : arrSalida)
        {
            acumulador += linea;
        }
        
        if(arrSalida != null)
        {
            if( arrSalida.size() > 0 )
            {
                String lineaRespuesta = arrSalida.get(0);
                
                if(lineaRespuesta.contains("1 received"))
                {
                    vivo = true;
                }
            }
        }
        
        return vivo;
    }
}
