package distsys.zerohunger.server;

import distsys.zerohunger.jmdns.JmDNSServiceRegistration;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 *
 * @author kubraveli
 */
/**
 * This class sets up and starts the gRPC server for FarmControlService. It
 * registers the service using jmDNS so clients can discover it dynamically.
 */
public class FarmControlServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50054;

        JmDNSServiceRegistration jmdnsService = new JmDNSServiceRegistration();
        jmdnsService.registerService("_farmcontrol._tcp.local.", "FarmControlService", port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            jmdnsService.unregisterService();
            System.out.println("FarmControlService unregistered");
        }));

        Server server = ServerBuilder.forPort(port)
                .addService(new FarmControlServiceImpl())
                .build()
                .start();

        System.out.println("FarmControlService is running on port " + port);
        server.awaitTermination();
    }
}
