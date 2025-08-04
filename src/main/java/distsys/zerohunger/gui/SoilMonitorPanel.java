package distsys.zerohunger.gui;

import com.zerohunger.SoilMonitorServiceGrpc;
import com.zerohunger.SensorRequest;
import com.zerohunger.SoilStatusResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.jmdns.ServiceInfo;
import javax.swing.*;
import java.awt.*;
import java.time.Instant;

public class SoilMonitorPanel extends JPanel {

    private final String host;
    private final int port;

    public SoilMonitorPanel(ServiceInfo info) {
        String tempHost;
        int tempPort;

        String[] addresses = info.getHostAddresses();
        if (addresses.length == 0) {
            JOptionPane.showMessageDialog(this, "No IP address found for this service.");
            tempHost = "localhost"; // fallback
            tempPort = 50051;        //port
        } else {
            tempHost = addresses[0];
            tempPort = info.getPort();
        }

        this.host = tempHost;
        this.port = tempPort;

        setLayout(new BorderLayout());

        JButton checkBtn = new JButton("Check Soil Moisture");
        checkBtn.addActionListener(e -> {
            try {
                ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                        .usePlaintext()
                        .build();

                SoilMonitorServiceGrpc.SoilMonitorServiceBlockingStub stub
                        = SoilMonitorServiceGrpc.newBlockingStub(channel);

                SensorRequest request = SensorRequest.newBuilder()
                        .setSensorId("GUI_SENSOR_01")
                        .setTimestamp(Instant.now().toString())
                        .build();

                SoilStatusResponse response = stub.checkSoilMoisture(request);

                String result = "Moisture: " + response.getMoisture()
                        + "\nNitrogen Level: " + response.getNitrogenLevel();

                JOptionPane.showMessageDialog(this, result);
                channel.shutdown();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        add(checkBtn, BorderLayout.CENTER);
    }
}
