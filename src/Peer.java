import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Peer implements Runnable{
    private String peerID;
    FileMetaData fileMetaData;
    private final List<FileMetaData> sharedFiles;
    private final int port;


    public Peer(String peerID, int port) {
        this.port = port;
        this.peerID = peerID;
        this.sharedFiles = new ArrayList<>();
    }

    public void shareFile(String fileName) {
        sharedFiles.add(new FileMetaData(fileName));
    }

    public void listFiles() {
        System.out.println("Files shared by " + peerID + ": ");
        for (FileMetaData file : sharedFiles) {
            System.out.println(file.fileName);
        }
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println(peerID + " is listening on port: " + port);

            while (true) {
                try (Socket socket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                    for (FileMetaData file : sharedFiles) {
                        out.println(file.fileName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
