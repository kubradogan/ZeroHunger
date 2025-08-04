/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.zerohunger.server;

/**
 *
 * @author kubraveli
 */
import com.zerohunger.SoilMonitorServiceGrpc;
import com.zerohunger.SensorRequest;
import com.zerohunger.SoilStatusResponse;
import io.grpc.stub.StreamObserver;

/**
 * This class handles requests related to soil monitoring. It processes sensor
 * data and returns current moisture and nitrogen levels.
 */
public class SoilMonitorServiceImpl extends SoilMonitorServiceGrpc.SoilMonitorServiceImplBase {

    @Override
    public void checkSoilMoisture(SensorRequest request, StreamObserver<SoilStatusResponse> responseObserver) {
        String sensorId = request.getSensorId();
        String timestamp = request.getTimestamp();

        // Simulated logic - in a real system, this would read from hardware or a database
        String moisture = "Low"; // could be "Medium" or "High" depending on real sensor values
        float nitrogenLevel = 30.5f; // sample value

        SoilStatusResponse response = SoilStatusResponse.newBuilder()
                .setMoisture(moisture)
                .setNitrogenLevel(nitrogenLevel)
                .build();

        // Send the response back to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}