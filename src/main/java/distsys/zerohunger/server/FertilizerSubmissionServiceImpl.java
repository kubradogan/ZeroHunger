package distsys.zerohunger.server;

import com.zerohunger.FertilizerSubmissionServiceGrpc;
import com.zerohunger.FertilizerInput;
import com.zerohunger.FertilizerAnalysis;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import distsys.zerohunger.jmdns.JmDNSDiscovery;
import javax.jmdns.ServiceInfo;

/**
 *
 * @author kubraveli
 */

/**
 * This class implements the client-streaming gRPC service for submitting
 * fertilizer history. It receives multiple FertilizerInput messages from the
 * client and returns a summary analysis.
 */
public class FertilizerSubmissionServiceImpl extends FertilizerSubmissionServiceGrpc.FertilizerSubmissionServiceImplBase {

    @Override
    public StreamObserver<FertilizerInput> sendFertilizerHistory(StreamObserver<FertilizerAnalysis> responseObserver) {

        return new StreamObserver<FertilizerInput>() {
            List<FertilizerInput> inputs = new ArrayList<>();

            @Override
            public void onNext(FertilizerInput input) {
                // Store each incoming fertilizer record
                inputs.add(input);
                System.out.println("Received fertilizer input: " + input.getFertilizerType()
                        + " - " + input.getAmountKg() + "kg on " + input.getApplicationDate());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error receiving fertilizer data: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                // Simulate analysis logic
                StringBuilder advice = new StringBuilder("Fertilizer Analysis Summary:\n");

                for (FertilizerInput input : inputs) {
                    advice.append("- ").append(input.getFertilizerType())
                            .append(" (").append(input.getAmountKg()).append("kg on ")
                            .append(input.getApplicationDate()).append(")\n");
                }

                // Create and send the analysis result
                FertilizerAnalysis result = FertilizerAnalysis.newBuilder()
                        .setAdvice(advice.toString())
                        .build();

                responseObserver.onNext(result);
                responseObserver.onCompleted();
                List<ServiceInfo> farmControlServices = JmDNSDiscovery.discoverServices("_farmcontrol._tcp.local.");
                for (ServiceInfo info : farmControlServices) {
                    System.out.println("Discovered FarmControl Service: " + info.getName() + " at " + info.getInetAddresses()[0] + ":" + info.getPort());
                }
            }

        };
    }
}
