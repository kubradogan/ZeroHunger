/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.zerohunger.client;

import com.zerohunger.SoilMonitorServiceGrpc;
import com.zerohunger.SoilStatusResponse;
import com.zerohunger.SensorRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 *
 * @author kubraveli
 */
public class SoilMonitorClient {

    public static void main(String[] args) {

        // Build the gRPC channel to connect to server running on localhost at port 50051
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext() // No SSL for local testing
                .build();

        // Create a blocking stub to call unary RPC method
        SoilMonitorServiceGrpc.SoilMonitorServiceBlockingStub stub
                = SoilMonitorServiceGrpc.newBlockingStub(channel);

        // Create a request object with sample data
        SensorRequest request = SensorRequest.newBuilder()
                .setSensorId("SENSOR_001")
                .setTimestamp("2025-08-04T09:00:00Z")
                .build();

        // Send the request and get the response
        SoilStatusResponse response = stub.checkSoilMoisture(request);

        // Print the received moisture and nitrogen level
        System.out.println("Soil Moisture Level: " + response.getMoisture());
        System.out.println("Nitrogen Level: " + response.getNitrogenLevel());

        // Close the channel after use
        channel.shutdown();
    }
}
