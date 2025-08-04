package distsys.zerohunger.gui;

import com.zerohunger.RegionRequest;
import com.zerohunger.WeatherForecastServiceGrpc;
import com.zerohunger.WeatherResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import javax.jmdns.ServiceInfo;
import javax.swing.*;
import java.awt.*;

/*
 * This panel is used to send a region name to the WeatherForecastService
 * and display the weather predictions streamed back from the server.
 */
public class WeatherForecastPanel extends JPanel {
    private final String host;
    private final int port;
    private JTextField regionField;
    private JTextArea forecastArea;

    // Constructor to set up GUI components and get host/port info from discovered service
    public WeatherForecastPanel(ServiceInfo info) {
        String[] addresses = info.getHostAddresses();
        this.host = "127.0.0.1";
        this.port = 50052;
//        if (addresses == null || addresses.length == 0) {
//            JOptionPane.showMessageDialog(this, "No IP address found for this service.");
//            this.host = "localhost";
//            this.port = 0;
//            return;
//        }
//
//        String ipv4 = null;
//        String ipv6WithScope = null;
//        System.out.println("ServiceInfo.getHostAddresses():");
//        for (String addr : addresses) {
//            System.out.println(addr);
//            if (addr.contains(".")) {
//                ipv4 = addr;
//                break;
//            } else if (addr.contains(":") && addr.contains("%")) {
//                ipv6WithScope = addr;
//            }
//        }
//
//        if (ipv4 != null) {
//            this.host = ipv4;
//        } else if (ipv6WithScope != null) {
//            this.host = ipv6WithScope;
//        } else {
//            this.host = addresses[0];  // fallback
//        }
//
//        this.port = info.getPort();
//        
        
        System.out.println(host + ":" + port);

        setLayout(new BorderLayout());

        // Top section for user input
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter Region:"));
        regionField = new JTextField(20);
        inputPanel.add(regionField);

        JButton fetchButton = new JButton("Get Forecast");
        inputPanel.add(fetchButton);

        // Area to show the weather forecast results
        forecastArea = new JTextArea(10, 40);
        forecastArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(forecastArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // When the button is clicked, start the gRPC call
        fetchButton.addActionListener(e -> fetchForecast());
    }

    // This method sends a region name to the gRPC server and receives forecast data as a stream
    private void fetchForecast() {
        String region = regionField.getText().trim();

        if (region.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please type a region name first.");
            return;
        }

        forecastArea.setText("Gettingg forecast for: " + region + "\n");

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();

        WeatherForecastServiceGrpc.WeatherForecastServiceStub stub
                = WeatherForecastServiceGrpc.newStub(channel);

        RegionRequest request = RegionRequest.newBuilder()
                .setRegionName(region)
                .build();

        stub.getForecast(request, new StreamObserver<WeatherResponse>() {
            @Override
            public void onNext(WeatherResponse response) {
                String data = String.format(
                        "%s  |  Temp: %.1f°C  |  Humidity: %.1f%%  |  Rain chance: %.1f%%\n",
                        response.getDate(),
                        response.getTemperature(),
                        response.getHumidity(),
                        response.getRainChance()
                );
                SwingUtilities.invokeLater(() -> forecastArea.append(data));
            }

            @Override
            public void onError(Throwable t) {
                SwingUtilities.invokeLater(() -> forecastArea.append("Error occurred: " + t.getMessage() + "\n"));
            }

            @Override
            public void onCompleted() {
                SwingUtilities.invokeLater(() -> forecastArea.append("Forecast finished.\n"));
                channel.shutdown();
            }
        });
    }
}
