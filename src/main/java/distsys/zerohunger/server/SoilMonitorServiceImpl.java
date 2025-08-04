package distsys.zerohunger.server;

import com.zerohunger.SoilMonitorServiceGrpc;
import com.zerohunger.SensorRequest;
import com.zerohunger.SoilStatusResponse;
import io.grpc.stub.StreamObserver;
import distsys.zerohunger.jmdns.JmDNSDiscovery;
import javax.jmdns.ServiceInfo;
import java.util.List;

/**
 *
 * @author kubraveli
 */
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

        // Discover other services (example: WeatherForecast)
        List<ServiceInfo> weatherServices = JmDNSDiscovery.discoverServices("_weatherforecast._tcp.local.");
        for (ServiceInfo info : weatherServices) {
            System.out.println("Discovered WeatherForecast Service: " + info.getName() + " at " + info.getInetAddresses()[0] + ":" + info.getPort());
        }

    }
}
