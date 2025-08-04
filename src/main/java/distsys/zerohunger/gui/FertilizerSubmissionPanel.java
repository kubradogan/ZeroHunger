package distsys.zerohunger.gui;

import com.zerohunger.FertilizerAnalysis;
import com.zerohunger.FertilizerInput;
import com.zerohunger.FertilizerSubmissionServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.ServiceInfo;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/*
 * This panel lets the user enter multiple fertilizer records
 * and send them to the server as a stream.
 * The server returns one analysis message in response.
 */
public class FertilizerSubmissionPanel extends JPanel {

    private final String host;
    private final int port;

    private JTextField typeField;
    private JTextField amountField;
    private JTextField dateField;
    private JTextArea inputListArea;
    private JTextArea resultArea;

    private List<FertilizerInput> inputs;

    public FertilizerSubmissionPanel(ServiceInfo info) {
        this.host = info.getHostAddresses()[0];
        this.port = info.getPort();
        this.inputs = new ArrayList<>();

        setLayout(new BorderLayout());

        // Input area
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Fertilizer Type:"));
        typeField = new JTextField();
        inputPanel.add(typeField);

        inputPanel.add(new JLabel("Amount (kg):"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Application Date:"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        JButton addButton = new JButton("Add Entry");
        JButton sendButton = new JButton("Submit All");
        inputPanel.add(addButton);
        inputPanel.add(sendButton);

        // List of added entries
        inputListArea = new JTextArea(8, 40);
        inputListArea.setEditable(false);
        JScrollPane listScroll = new JScrollPane(inputListArea);

        // Result area
        resultArea = new JTextArea(4, 40);
        resultArea.setEditable(false);
        JScrollPane resultScroll = new JScrollPane(resultArea);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(new JLabel("Added Fertilizer Records:"), BorderLayout.NORTH);
        centerPanel.add(listScroll, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(new JLabel("Analysis Result:"), BorderLayout.NORTH);
        bottomPanel.add(resultScroll, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Add entry button action
        addButton.addActionListener(e -> {
            String type = typeField.getText().trim();
            String amountText = amountField.getText().trim();
            String date = dateField.getText().trim();

            if (type.isEmpty() || amountText.isEmpty() || date.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }

            float amount;
            try {
                amount = Float.parseFloat(amountText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Amount must be a number.");
                return;
            }

            FertilizerInput entry = FertilizerInput.newBuilder()
                    .setFertilizerType(type)
                    .setAmountKg(amount)
                    .setApplicationDate(date)
                    .build();

            inputs.add(entry);
            inputListArea.append("Type: " + type + ", Amount: " + amount + "kg, Date: " + date + "\n");

            typeField.setText("");
            amountField.setText("");
            dateField.setText("");
        });

        // Submit button action
        sendButton.addActionListener(e -> submitToServer());
    }

    private void submitToServer() {
        if (inputs.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No fertilizer records to send.");
            return;
        }

        resultArea.setText("Sending data to server...\n");

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        FertilizerSubmissionServiceGrpc.FertilizerSubmissionServiceStub stub
                = FertilizerSubmissionServiceGrpc.newStub(channel);

        StreamObserver<FertilizerAnalysis> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(FertilizerAnalysis analysis) {
                resultArea.append("Advice from server: " + analysis.getAdvice() + "\n");
            }

            @Override
            public void onError(Throwable t) {
                resultArea.append("Error: " + t.getMessage() + "\n");
            }

            @Override
            public void onCompleted() {
                resultArea.append("Submission completed.\n");
                inputs.clear();
            }
        };

        StreamObserver<FertilizerInput> requestObserver = stub.sendFertilizerHistory(responseObserver);

        for (FertilizerInput input : inputs) {
            requestObserver.onNext(input);
        }

        requestObserver.onCompleted();
    }
}
