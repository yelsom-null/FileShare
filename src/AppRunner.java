import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class AppRunner {
    private Peer peer1;
    private Peer peer2;
    private FileShareGUI gui;
    private final ArrayList<String> receivedFiles;

    public AppRunner() {
        System.out.println("AppRunner constructor called.");
        this.receivedFiles = new ArrayList<>();
    }

    public void initialize() throws IOException {
        System.out.println("Initialize method called.");

        peer1 = new Peer("Peer1", 850);
        peer2 = new Peer("Peer2", 851);

        SwingUtilities.invokeLater(() -> {
            System.out.println("Creating GUI.");
            gui = new FileShareGUI(peer1, peer2, receivedFiles);
        });

        peer2.addFileReceivedObserver(fileName -> {
            System.out.println("File received: " + fileName);
            receivedFiles.add(fileName);
            if (gui != null) {
                gui.updateReceivedFilesList(receivedFiles);
            }
        });


        System.out.println("Starting peer threads.");
        new Thread(peer1).start();
        new Thread(peer2).start();
    }
}
