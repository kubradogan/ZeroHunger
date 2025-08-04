package distsys.zerohunger.server;

import com.zerohunger.FarmControlServiceGrpc;
import com.zerohunger.DecisionRequest;
import com.zerohunger.AdvisoryResponse;
import io.grpc.stub.StreamObserver;
import distsys.zerohunger.jmdns.JmDNSDiscovery;
import javax.jmdns.ServiceInfo;
import java.util.List;

/**
 *
 * @author kubraveli
 */
/**
 * This service handles bidirectional communication between the farm control
 * center and the decision-making system. It processes incoming alerts or sensor
 * data and provides real-time operational advice.
 */
public class FarmControlServiceImpl extends FarmControlServiceGrpc.FarmControlServiceImplBase {

    @Override
    public StreamObserver<DecisionRequest> interact(StreamObserver<AdvisoryResponse> responseObserver) {

        return new StreamObserver<DecisionRequest>() {

            @Override
            public void onNext(DecisionRequest request) {
                String inputType = request.getInputType();
                String value = request.getValue();

                String advice;

                // Simple decision-making logic based on input type
                if (inputType.equalsIgnoreCase("WeatherAlert") && value.toLowerCase().contains("storm")) {
                    advice = "Secure all equipment and pause irrigation.";
                } else if (inputType.equalsIgnoreCase("SoilAlert") && value.toLowerCase().contains("dry")) {
                    advice = "Activate irrigation system immediately.";
                } else {
                    advice = "No action needed at this time.";
                }

                AdvisoryResponse response = AdvisoryResponse.newBuilder()
                        .setAdvice(advice)
                        .build();

                // Send the advice back to the client
                responseObserver.onNext(response);
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in bidirectional stream: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // Close the stream when the client is done
                responseObserver.onCompleted();
                List<ServiceInfo> soilMonitorServices = JmDNSDiscovery.discoverServices("_soilmonitor._tcp.local.");
                for (ServiceInfo info : soilMonitorServices) {
                    System.out.println("Discovered SoilMonitor Service: " + info.getName() + " at " + info.getInetAddresses()[0] + ":" + info.getPort());
                }
            }
        };
    }
}
