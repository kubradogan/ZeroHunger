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
 * This class launches a gRPC server to run the FertilizerSubmissionService. It
 * registers the service with jmDNS for network discovery and handles incoming
 * fertilizer data.
 */
public class FertilizerSubmissionServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50053;

        // Register service with jmDNS so clients can find it
        JmDNSServiceRegistration jmdnsService = new JmDNSServiceRegistration();
        jmdnsService.registerService("_fertilizersubmission._tcp.local.", "FertilizerSubmissionService", port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            jmdnsService.unregisterService();
            System.out.println("SoilMonitorService unregistered");
        }));

        // Start gRPC server and bind it to the FertilizerSubmissionService
        Server server = ServerBuilder.forPort(port)
                .addService(new FertilizerSubmissionServiceImpl())
                .build();

        server.start();
        System.out.println("FertilizerSubmissionService is running on port " + port);

        server.awaitTermination();
    }
}
