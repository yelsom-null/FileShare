import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class FileShareGUI {
    private final Peer peer1, peer2;
    private final JFrame frame;
    private final JList<String> receivedFileList;
    private final JButton openFileButton;

    public FileShareGUI(Peer peer1, Peer peer2, ArrayList<String> receivedFiles) {
        this.peer1 = peer1;
        this.peer2 = peer2;

        frame = new JFrame("File Share");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 200);

        JPanel mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);

        JPanel peer1Panel = new JPanel();
        peer1Panel.setBorder(BorderFactory.createTitledBorder("Peer 1"));
        mainPanel.add(peer1Panel, BorderLayout.WEST);

        openFileButton = new JButton("Open File");
        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open File button clicked.");
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    System.out.println("Selected file: " + filePath);

                    peer1.shareFile(filePath);
                } else {
                    System.out.println("File selection was cancelled.");
                }
            }
        });

        peer1Panel.add(openFileButton);

        JPanel peer2Panel = new JPanel();
        peer2Panel.setBorder(BorderFactory.createTitledBorder("Peer 2"));
        mainPanel.add(peer2Panel, BorderLayout.EAST);

        receivedFileList = new JList<>(receivedFiles.toArray(new String[0]));
        peer2Panel.add(new JScrollPane(receivedFileList));

        frame.setVisible(true);
    }

    public void updateReceivedFilesList(ArrayList<String> receivedFiles) {
        System.out.println("Updating received files list");
        SwingUtilities.invokeLater(() -> {
            receivedFileList.setListData(receivedFiles.toArray(new String[0]));
            receivedFileList.revalidate();
            receivedFileList.repaint();
        });
    }
}
