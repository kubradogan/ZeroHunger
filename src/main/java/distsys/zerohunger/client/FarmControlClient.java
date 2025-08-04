package distsys.zerohunger.client;

import com.zerohunger.FarmControlServiceGrpc;
import com.zerohunger.DecisionRequest;
import com.zerohunger.AdvisoryResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;

/**
 *
 * @author kubraveli
 */
/**
 * This client connects to the FarmControlService using bidirectional streaming.
 * It sends multiple decisions to the server and receives advisory responses in
 * real-time.
 */
public class FarmControlClient {

    public static void main(String[] args) {
        // Build the gRPC channel to connect to the server
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        // Create an asynchronous stub to enable bidirectional communication
        FarmControlServiceGrpc.FarmControlServiceStub asyncStub
                = FarmControlServiceGrpc.newStub(channel);

        // Handle server responses
        StreamObserver<DecisionRequest> requestObserver = asyncStub.interact(
                new StreamObserver<AdvisoryResponse>() {
            @Override
            public void onNext(AdvisoryResponse response) {
                System.out.println("Server advice: " + response.getAdvice());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error receiving response: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("All server responses received.");
            }
        });

        // Read multiple decisions from the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter decisions (type 'done' to finish):");

        while (true) {
            System.out.print("Input type: ");
            String inputType = scanner.nextLine();
            if (inputType.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Value: ");
            String value = scanner.nextLine();

            // Send decision to server
            DecisionRequest request = DecisionRequest.newBuilder()
                    .setInputType(inputType)
                    .setValue(value)
                    .build();

            requestObserver.onNext(request);
        }

        // Tell the server we are done sending
        requestObserver.onCompleted();

        // Wait for a bit before shutting down to ensure all responses are received
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Clean shutdown
        channel.shutdown();
    }
}
