package distsys.zerohunger.client;

import com.zerohunger.SoilMonitorServiceGrpc;
import com.zerohunger.SoilStatusResponse;
import com.zerohunger.SensorRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.Status;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author kubraveli
 */
public class SoilMonitorClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        SoilMonitorServiceGrpc.SoilMonitorServiceBlockingStub stub
                = SoilMonitorServiceGrpc.newBlockingStub(channel)
                        .withDeadlineAfter(3, TimeUnit.SECONDS); //DEADLINE

        SensorRequest request = SensorRequest.newBuilder()
                .setSensorId("SENSOR_001")
                .setTimestamp("2025-08-04T09:00:00Z")
                .build();

        try {
            SoilStatusResponse response = stub.checkSoilMoisture(request);

            System.out.println("Soil Moisture Level: " + response.getMoisture());
            System.out.println("Nitrogen Level: " + response.getNitrogenLevel());

        } catch (StatusRuntimeException e) {
            if (e.getStatus().getCode() == Status.Code.DEADLINE_EXCEEDED) {
                System.err.println("Request timed out.");
            }
        }

        channel.shutdown();
    }
}
