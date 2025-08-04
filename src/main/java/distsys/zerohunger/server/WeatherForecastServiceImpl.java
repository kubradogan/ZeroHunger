package distsys.zerohunger.server;

import com.zerohunger.WeatherForecastServiceGrpc;
import com.zerohunger.RegionRequest;
import com.zerohunger.WeatherResponse;
import io.grpc.stub.StreamObserver;
import distsys.zerohunger.jmdns.JmDNSDiscovery;
import javax.jmdns.ServiceInfo;
import java.util.List;

/**
 *
 * @author kubraveli
 */
/**
 * This class implements the WeatherForecastService. It provides a stream of
 * weather forecast data for a given region.
 */
public class WeatherForecastServiceImpl extends WeatherForecastServiceGrpc.WeatherForecastServiceImplBase {

    @Override
    public void getForecast(RegionRequest request, StreamObserver<WeatherResponse> responseObserver) {
        String region = request.getRegionName();

        System.out.println("Received weather forecast request for region: " + region);

        // Simulate 3 days of forecast data
        String[] dates = {"2025-08-04", "2025-08-05", "2025-08-06"};
        float[] temperatures = {22.5f, 24.0f, 19.5f};
        float[] humidity = {60.0f, 55.0f, 70.0f};
        float[] rainChances = {20.0f, 10.0f, 50.0f};

        for (int i = 0; i < dates.length; i++) {
            WeatherResponse response = WeatherResponse.newBuilder()
                    .setDate(dates[i])
                    .setTemperature(temperatures[i])
                    .setHumidity(humidity[i])
                    .setRainChance(rainChances[i])
                    .build();

            responseObserver.onNext(response);
        }

        // Signal that the stream is complete
        responseObserver.onCompleted();

        List<ServiceInfo> fertilizerServices = JmDNSDiscovery.discoverServices("_fertilizersubmission._tcp.local.");
        for (ServiceInfo info : fertilizerServices) {
            System.out.println("Discovered FertilizerSubmission Service: " + info.getName() + " at " + info.getInetAddresses()[0] + ":" + info.getPort());
        }
    }
}
