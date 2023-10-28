import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Peer implements Runnable {
    private final String peerID;
    private final int port;
    private final List<String> sharedFiles;
    private final List<FileReceivedObserver> observers;

    public interface FileReceivedObserver {
        void onFileReceived(String fileName);
    }

    public Peer(String peerID, int port) {
        this.peerID = peerID;
        this.port = port;
        this.sharedFiles = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addFileReceivedObserver(FileReceivedObserver observer) {
        observers.add(observer);
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    receiveFile(socket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void shareFile(String filePath) {
        try (Socket socket = new Socket("localhost", 851);
             OutputStream os = socket.getOutputStream()) {

            Path path = Paths.get(filePath);
            byte[] data = Files.readAllBytes(path);


            String fileName = path.getFileName().toString();
            os.write(fileName.length());
            os.write(fileName.getBytes());


            os.write(data);

            System.out.println("Sharing file: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveFile(Socket socket) {
        try (InputStream is = socket.getInputStream()) {

            int fileNameLength = is.read();
            byte[] fileNameBytes = new byte[fileNameLength];
            is.read(fileNameBytes);
            String fileName = new String(fileNameBytes);


            byte[] fileContent = is.readAllBytes();

            Path path = Paths.get(fileName);
            Files.write(path, fileContent);

            System.out.println("Received file: " + fileName);

            for (FileReceivedObserver observer : observers) {
                observer.onFileReceived(fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
