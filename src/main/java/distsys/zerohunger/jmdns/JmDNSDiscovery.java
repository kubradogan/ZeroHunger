package distsys.zerohunger.jmdns;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Enumeration;

public class JmDNSDiscovery {

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
            System.err.println("Wi-Fi IPv4 didnt get: " + e.getMessage());
        }
        return null;
    }

    public static List<ServiceInfo> discoverServices(String serviceType) {
        List<ServiceInfo> discovered = new ArrayList<>();
        try {
            InetAddress addr = getWiFiIPv4();
            JmDNS jmdns = (addr != null) ? JmDNS.create(addr) : JmDNS.create();
            jmdns.addServiceListener(serviceType, new ServiceListener() {
                @Override
                public void serviceAdded(ServiceEvent event) {
                    jmdns.requestServiceInfo(event.getType(), event.getName(), true);
                }

                @Override
                public void serviceRemoved(ServiceEvent event) {}

                @Override
                public void serviceResolved(ServiceEvent event) {
                    ServiceInfo info = event.getInfo();

                    String[] rawAddresses = info.getHostAddresses();
                    List<String> fixedAddresses = new ArrayList<>();

                    for (String addr : rawAddresses) {
                        if (addr.startsWith("fe80:") && !addr.contains("%")) {
                            try {
                                NetworkInterface ni = NetworkInterface.getByName("en0");
                                if (ni != null) {
                                    addr = addr + "%" + ni.getName();
                                }
                            } catch (SocketException e) {
                                System.err.println("Scope ID didnt add: " + e.getMessage());
                            }
                        }
                        fixedAddresses.add(addr);
                    }

                    System.out.println("Resolved: " + info.getName() + " at " + fixedAddresses + ":" + info.getPort());
                    discovered.add(info);
                }
            });
            Thread.sleep(8000);
            jmdns.close();
        } catch (Exception e) {
            System.err.println("Discovery error: " + e.getMessage());
        }
        return discovered;
    }
}