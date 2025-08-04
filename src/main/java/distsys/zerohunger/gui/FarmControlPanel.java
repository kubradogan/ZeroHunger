package distsys.zerohunger.gui;

import com.zerohunger.AdvisoryResponse;
import com.zerohunger.DecisionRequest;
import com.zerohunger.FarmControlServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.ServiceInfo;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class FarmControlPanel extends JPanel {

    private final String host;
    private final int port;
    private JTextArea outputArea;
    private JTextField inputTypeField;
    private JTextField valueField;

    public FarmControlPanel(ServiceInfo info) {
        this.host = info.getHostAddresses()[0];
        this.port = info.getPort();

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputTypeField = new JTextField();
        valueField = new JTextField();

        inputPanel.add(new JLabel("Input Type:"));
        inputPanel.add(inputTypeField);
        inputPanel.add(new JLabel("Value:"));
        inputPanel.add(valueField);

        JButton sendButton = new JButton("Send Decision");

        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(sendButton, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        sendButton.addActionListener(e -> sendDecision());
    }

    private void sendDecision() {
        String inputType = inputTypeField.getText();
        String value = valueField.getText();

        if (inputType.isEmpty() || value.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Both fields are required.");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        FarmControlServiceGrpc.FarmControlServiceStub stub = FarmControlServiceGrpc.newStub(channel);

        StreamObserver<DecisionRequest> requestObserver = stub.interact(new StreamObserver<AdvisoryResponse>() {
            @Override
            public void onNext(AdvisoryResponse response) {
                SwingUtilities.invokeLater(() -> {
                    outputArea.append("Advice: " + response.getAdvice() + "\n");
                });
            }

            @Override
            public void onError(Throwable t) {
                SwingUtilities.invokeLater(() -> {
                    outputArea.append("Error: " + t.getMessage() + "\n");
                });
            }

            @Override
            public void onCompleted() {
                try {
                    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // input to stream
        DecisionRequest request = DecisionRequest.newBuilder()
                .setInputType(inputType)
                .setValue(value)
                .build();

        requestObserver.onNext(request);
        requestObserver.onCompleted();
    }
}
