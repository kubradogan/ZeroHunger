package distsys.zerohunger.jmdns;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class JmDNSServiceRegistration {

    private JmDNS jmdns;

    // Yardımcı fonksiyon: Wi-Fi IPv4 adresini alır
    private static InetAddress getWiFiIPv4() {
        try {
            NetworkInterface ni = NetworkInterface.getByName("en0"); // Wi-Fi: en0
            if (ni != null) {
                Enumeration<InetAddress> addrs = ni.getInetAddresses();
                while (addrs.hasMoreElements()) {
                    InetAddress addr = addrs.nextElement();
                    if (addr.getHostAddress().contains(".")) {
                        System.out.println("IPv4: " + addr.getHostAddress());
                        return addr;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Wi-Fi IPv4 alınamadı: " + e.getMessage());
        }
        return null;
    }

    public void registerService(String serviceType, String serviceName, int port) {
        try {
            InetAddress addr = getWiFiIPv4();
            jmdns = (addr != null) ? JmDNS.create(addr) : JmDNS.create();

            ServiceInfo serviceInfo = ServiceInfo.create(
                    serviceType,
                    serviceName,
                    port,
                    "Zero Hunger gRPC Service"
            );

            jmdns.registerService(serviceInfo);
            System.out.println(serviceName + " registered on port " + port);
        } catch (IOException e) {
            System.err.println("Failed to register service: " + e.getMessage());
        }
    }

    public void unregisterService() {
        if (jmdns != null) {
            try {
                jmdns.unregisterAllServices();
                jmdns.close();
            } catch (IOException e) {
                System.err.println("Error during jmDNS shutdown: " + e.getMessage());
            }
        }
    }
}