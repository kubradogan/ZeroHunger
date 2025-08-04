package distsys.zerohunger.gui;

import distsys.zerohunger.jmdns.JmDNSDiscovery;

import javax.jmdns.ServiceInfo;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;

public class MainGUI extends JFrame {

    private final DefaultListModel<String> listModel;
    private final JList<String> serviceList;
    private final Map<String, ServiceInfo> discoveredServices;

    public MainGUI() {
        setTitle("Smart Farm Service Discovery Panel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        serviceList = new JList<>(listModel);
        discoveredServices = new HashMap<>();

        JButton discoverBtn = new JButton("Discover Services");
        discoverBtn.addActionListener(e -> discoverServices());

        add(new JScrollPane(serviceList), BorderLayout.CENTER);
        add(discoverBtn, BorderLayout.SOUTH);

        serviceList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selected = serviceList.getSelectedValue();
                    if (selected == null) {
                        return;
                    }

                    ServiceInfo info = discoveredServices.get(selected);
                    if (info == null) {
                        JOptionPane.showMessageDialog(MainGUI.this, "Service info not found. Please rediscover.");
                        return;
                    }

                    String name = info.getName();

                    if (name.contains("SoilMonitor")) {
                        JFrame frame = new JFrame("Soil Monitor Control");
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setContentPane(new SoilMonitorPanel(info));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    } else if (name.contains("WeatherForecast")) {
                        JFrame frame = new JFrame("Weather Forecast Panel");
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setContentPane(new WeatherForecastPanel(info));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    } else if (name.contains("FertilizerSubmission")) {
                        JFrame frame = new JFrame("Fertilizer Submission Panel");
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setContentPane(new FertilizerSubmissionPanel(info));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    } else if (name.contains("FarmControl")) {
                        JFrame frame = new JFrame("Farm Control Panel");
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame.setContentPane(new FarmControlPanel(info));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                }
            }
        });
    }

    private void discoverServices() {
        listModel.clear();
        discoveredServices.clear();
        listModel.addElement("Searching services...");

        new SwingWorker<Void, String>() {
            private final Set<String> seenServices = new HashSet<>();

            @Override
            protected Void doInBackground() {
                checkService("_soilmonitor._tcp.local.");
                checkService("_weatherforecast._tcp.local.");
                checkService("_fertilizersubmission._tcp.local.");
                checkService("_farmcontrol._tcp.local.");
                return null;
            }

            private void checkService(String type) {
                List<ServiceInfo> services = JmDNSDiscovery.discoverServices(type);
                for (ServiceInfo info : services) {
                    String key = info.getName() + " - " + info.getHostAddresses()[0] + ":" + info.getPort();
                    if (seenServices.add(key)) {
                        discoveredServices.put(key, info);
                        publish(key);
                    }
                }
            }

            @Override
            protected void process(List<String> chunks) {
                listModel.clear();
                chunks.forEach(listModel::addElement);
            }

            @Override
            protected void done() {
                if (listModel.isEmpty()) {
                    listModel.addElement("No services found");
            }
            }
        }.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainGUI().setVisible(true));
    }
}