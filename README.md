# FileShare

## Decentralized Peer-to-Peer File Sharing in Java

### Description

This is a simple Java project that demonstrates a decentralized Peer-to-Peer file sharing system. Currently, the project allows peers to share a list of file names with each other. It serves as a proof of concept and aims to 
provide a base for building more advanced features.

### Motivation

As someone who frequently relies on file sharing applications in my daily activities, I was driven by a desire to understand the underlying mechanisms that make these tools work. This project allowed me to explore the fundamentals of decentralized peer-to-peer networks and develop a practical solution for file sharing. By creating FileShareGUI, I aimed to gain deeper insights into the technologies I use regularly and enhance my skills in Java programming, networking, and user interface design.


### Features

- **Multiple Peer Support**: The application can support multiple peers.
- **File Name Sharing**: Peers can share a list of file names.
- **Console-based UI**: A simple text-based user interface.

### How To Run

1. Clone this repository.
2. Open the project in your favorite IDE.
3. Run the `MainApplication.java` file.
4. Follow the console prompts to interact with the peers.

### Usage

After starting the `MainApplication`, you will see messages indicating the files being shared by each peer. Each peer runs on a separate thread and listens on a specified port for incoming connections from other peers.

### Technologies Used

- Java
- Java Sockets API

### Future Improvements

- **Support for Transferring Actual Files**: To enable file transfers in addition to just sharing file names.

### Complete
- **Add GUI/Command-line Interface**: To allow for easier interaction with the application.

