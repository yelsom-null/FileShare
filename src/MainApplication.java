import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MainApplication {
    public static void main(String[] args) throws InterruptedException, IOException {
        Peer peer1 = new Peer("Peer1", 850);
        Peer peer2 = new Peer("Peer2", 851);

        peer1.shareFilesFromDirectory("/path/to/directory");

        FileShareGUI gui = new FileShareGUI(peer1);
        Thread thread1 = new Thread(peer1);
        Thread thread2 = new Thread(peer2);

        thread1.start();
        thread2.start();


        try (Socket socket = new Socket("localhost", 850);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Peer2 received the list of files from Peer1:");
            String receivedFile;
            while ((receivedFile = in.readLine()) != null) {
                System.out.println(receivedFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
