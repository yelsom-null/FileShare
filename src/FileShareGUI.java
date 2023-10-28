import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileShareGUI {
    private Peer peer;
    private JFrame frame;
    private JButton openButton;

    public FileShareGUI(Peer peer) {
        this.peer = peer;


        frame = new JFrame("File Share");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);


        JPanel panel = new JPanel();
        frame.add(panel);


        openButton = new JButton("Open File");
        panel.add(openButton);


        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(frame);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName();
                    System.out.println("Selected file: " + fileName);
                }
            }
        });



        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Peer peer = new Peer("Peer1", 850);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FileShareGUI(peer);
            }
        });
    }
}
