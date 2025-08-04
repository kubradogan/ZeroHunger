package distsys.zerohunger.server;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author kubraveli
 */
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * This class starts a gRPC server to host the SoilMonitorService. It listens
 * for incoming requests and routes them to the service implementation.
 */
public class SoilMonitorServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        // The server will run on this port
        int port = 50051;

        // Build and start the gRPC server
        Server server = ServerBuilder.forPort(port)
                .addService(new SoilMonitorServiceImpl())
                .build()
                .start();

        System.out.println("Soil Monitor Server is running on port " + port);

        // Keep the server running
        server.awaitTermination();
    }
}