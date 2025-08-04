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
 * This class starts the gRPC server for the WeatherForecastService. It
 * registers the service using jmDNS for network discovery.
 */
public class WeatherForecastServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50052;

        // Register the service for discovery
        JmDNSServiceRegistration jmdnsService = new JmDNSServiceRegistration();
        jmdnsService.registerService("_weatherforecast._tcp.local.", "WeatherForecastService", port);

        // Unregister service gracefully when application is stopped
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            jmdnsService.unregisterService();
            System.out.println("WeatherForecastService unregistered");
        }));

        // Start the gRPC server
        Server server = ServerBuilder.forPort(port)
                .addService(new WeatherForecastServiceImpl())
                .build()
                .start();

        System.out.println("WeatherForecastService is running on port " + port);

        // Keep the server running
        server.awaitTermination();
    }
}
