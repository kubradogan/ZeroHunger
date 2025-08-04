package distsys.zerohunger.client;

import com.zerohunger.FertilizerInput;
import com.zerohunger.FertilizerAnalysis;
import com.zerohunger.FertilizerSubmissionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author kubraveli
 */
/**
 * Sends multiple fertilizer input records to the server and receives a single
 * analysis response in return.
 */
public class FertilizerSubmissionClient {

    public static void main(String[] args) throws InterruptedException {

        // Connect to the server on localhost port 50052
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053)
                .usePlaintext()
                .build();

        // Create an asynchronous stub for client streaming
        FertilizerSubmissionServiceGrpc.FertilizerSubmissionServiceStub stub
                = FertilizerSubmissionServiceGrpc.newStub(channel);

        // Synchronization aid to wait for server response
        CountDownLatch latch = new CountDownLatch(1);

        // Send request and handle response
        StreamObserver<FertilizerInput> requestObserver = stub.sendFertilizerHistory(
                new StreamObserver<FertilizerAnalysis>() {
            @Override
            public void onNext(FertilizerAnalysis analysis) {
                System.out.println("Server's Advice: " + analysis.getAdvice());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error receiving response: " + t.getMessage());
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                System.out.println("Fertilizer submission completed.");
                latch.countDown();
            }
        }
        );

        // Send multiple fertilizer inputs
        requestObserver.onNext(FertilizerInput.newBuilder()
                .setFertilizerType("Nitrogen")
                .setAmountKg(20.0f)
                .setApplicationDate("2025-08-01")
                .build());

        requestObserver.onNext(FertilizerInput.newBuilder()
                .setFertilizerType("Phosphorus")
                .setAmountKg(15.0f)
                .setApplicationDate("2025-08-03")
                .build());

        requestObserver.onNext(FertilizerInput.newBuilder()
                .setFertilizerType("Potassium")
                .setAmountKg(10.0f)
                .setApplicationDate("2025-08-04")
                .build());

        // Complete the stream
        requestObserver.onCompleted();

        // Wait for server to respond
        latch.await(3, TimeUnit.SECONDS);

        // Shutdown the channel
        channel.shutdown();
    }
}
