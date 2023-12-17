package org.example.chatting;

import javax.swing.*;

public class ChatServer {

    public static void main(String[] args) {

        Object[] selectionValues = {"Server", "Client"};
        String initialSelection = "Server";

        Object selection = JOptionPane.showInputDialog(null, "Login as : ", "MyChatApp", JOptionPane.QUESTION_MESSAGE, null, selectionValues, initialSelection);

        if (selection != null) {
            if (selection.equals("Server")) {
                String[] arguments = new String[]{};
                new MultiThreadChatServerSync().main(arguments);
            } else if (selection.equals("Client")) {
                String IPServer = JOptionPane.showInputDialog("Enter the Server ip address");
                if (IPServer != null) {
                    String[] arguments = new String[]{IPServer};
                    new ChatClient().main(arguments);
                } else {
                    System.out.println("Client operation canceled.");
                }
            }
        } else {
            System.out.println("Operation canceled.");
        }
    }
}
