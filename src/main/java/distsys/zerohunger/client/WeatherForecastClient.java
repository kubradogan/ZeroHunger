package distsys.zerohunger.client;

import com.zerohunger.RegionRequest;
import com.zerohunger.WeatherForecastServiceGrpc;
import com.zerohunger.WeatherResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

/**
 *
 * @author kubraveli
 */
/**
 * This client connects to the WeatherForecastService and receives a weather
 * forecast stream for a given region.
 */
public class WeatherForecastClient {

    public static void main(String[] args) {
        // Connect to the gRPC server on localhost and port 50052
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        // Create a stub for the WeatherForecastService
        WeatherForecastServiceGrpc.WeatherForecastServiceStub asyncStub
                = WeatherForecastServiceGrpc.newStub(channel);

        // Build a request for a specific region
        RegionRequest request = RegionRequest.newBuilder()
                .setRegionName("Dublin")
                .build();

        // Define what to do with each response
        StreamObserver<WeatherResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(WeatherResponse response) {
                System.out.println("Forecast Date: " + response.getDate());
                System.out.println("Temperature: " + response.getTemperature() + "°C");
                System.out.println("Humidity: " + response.getHumidity() + "%");
                System.out.println("Rain Chance: " + response.getRainChance() + "%");
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error receiving weather forecast: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Weather forecast stream completed.");
            }
        };

        // Call the server-streaming method
        asyncStub.getForecast(request, responseObserver);

        //Allow some time for the stream to complete before shutting down
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        channel.shutdown();
    }
}
