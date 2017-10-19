package automated;
public class Automated {

    public static void main(String[] args) 
    {
        System.out.println("conectando..");
        wsSSH.sshSession("192.168.5.6", "root", "tecacc", "cd /root/update_flex_android; ./patch_flex.sh uftpd-cnq lista_cnq");
    }
    
}
