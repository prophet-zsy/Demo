package netSocket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Inet_ {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress);
        InetAddress host = InetAddress.getByName("www.baidu.com");
        System.out.println(host);
        System.out.println(host.getHostName());
        System.out.println(host.getHostAddress());
    }
}
