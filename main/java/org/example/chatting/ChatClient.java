package org.example.chatting;

import org.example.entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import org.example.Main;


public class ChatClient {
    static public String roomname = "";

    static class ChatAccess extends Observable {
        private Socket socket;
        private OutputStream outputStream;
        @Override
        public void notifyObservers(Object arg) {
            super.setChanged();
            super.notifyObservers(arg);
        }

        public void InitSocket(String server, int port) throws IOException {
            socket = new Socket(server, port);
            outputStream = socket.getOutputStream();

            Thread receivingThread = new Thread() {
                @Override
                public void run() {
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                        String line;
                        while ((line = reader.readLine()) != null)
                            notifyObservers(line);
                    } catch (IOException ex) {
                        notifyObservers(ex);
                    }
                }
            };
            receivingThread.start();
        }

        private static final String CRLF = "\r\n";

        public void send(String text) {
            try {
                outputStream.write((text + CRLF).getBytes("UTF-8"));
                outputStream.flush();
            } catch (IOException ex) {
                notifyObservers(ex);
            }
        }

        public void close() {
            try {
                socket.close();
            } catch (IOException ex) {
                notifyObservers(ex);
            }
        }
    }

    static class ChatFrame extends JFrame implements Observer {

        private JTextArea textArea;
        private JTextField inputTextField;
        private JButton sendButton;
        private ChatAccess chatAccess;

        public ChatFrame(ChatAccess chatAccess) {
            this.chatAccess = chatAccess;
            chatAccess.addObserver(this);
            buildGUI();
        }

        private void buildGUI() {
            textArea = new JTextArea(20, 50);
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            add(new JScrollPane(textArea), BorderLayout.CENTER);

            Box box = Box.createHorizontalBox();
            add(box, BorderLayout.SOUTH);
            inputTextField = new JTextField();
            sendButton = new JButton("전송");
            box.add(inputTextField);
            box.add(sendButton);

            // UI 수정
            textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
            inputTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
            sendButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

            textArea.setBackground(new Color(204,255,255));
            sendButton.setBackground(new Color(000,051,204));
//            sendButton.setForeground(Color.WHITE);


            ActionListener sendListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String str = inputTextField.getText();
                    if (str != null && str.trim().length() > 0)
                        chatAccess.send(str);
                    inputTextField.selectAll();
                    inputTextField.requestFocus();
                    inputTextField.setText("");
                }
            };
            inputTextField.addActionListener(sendListener);
            sendButton.addActionListener(sendListener);

            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    chatAccess.close();
                }
            });

        }

        public void update(Observable o, Object arg) {
            final Object finalArg = arg;
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    textArea.append(finalArg.toString());
                    textArea.append("\n");
                }
            });
        }
    }

    public static void main(String[] args) {
        String server = "192.168.45.55";

        int port = 9988;


        ChatAccess access = new ChatAccess();
        JFrame frame = new ChatFrame(access);


        frame.setTitle(roomname +"5층 - connected to " + server + ":" + port);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        try {
            access.InitSocket(server, port); //서버와의 소켓 연결 초기화
            //로그인한 사용자 정보를 서버에 전송
            if (Main.currentUser != null) {
                String username;
                User currentUser = Main.currentUser;
                access.send(currentUser.getUserName());
                username = currentUser.getUserName();
                System.out.println(username);
            }
        } catch (IOException ex) {
            System.out.println("Cannot connect to " + server + ":" + port);
            ex.printStackTrace();
            System.exit(0);
        }
    }
}



//package org.example.chatting;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.*;
//import java.net.Socket;
//import java.util.Observable;
//import java.util.Observer;
//
//public class ChatClient {
//
//    static class ChatAccess extends Observable {
//        private Socket socket;
//        private OutputStream outputStream;
//
//        @Override
//        public void notifyObservers(Object arg) {
//            super.setChanged();
//            super.notifyObservers(arg);
//        }
//
//        public void InitSocket(String server, int port) throws IOException {
//            socket = new Socket(server, port);
//            outputStream = socket.getOutputStream();
//
//            Thread receivingThread = new Thread() {
//                @Override
//                public void run() {
//                    try {
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
//                        String line;
//                        while ((line = reader.readLine()) != null)
//                            notifyObservers(line);
//                    } catch (IOException ex) {
//                        notifyObservers(ex);
//                    }
//                }
//            };
//            receivingThread.start();
//        }
//
//        private static final String CRLF = "\r\n";
//
//        public void send(String text) {
//            try {
//                outputStream.write((text + CRLF).getBytes("UTF-8"));
//                outputStream.flush();
//            } catch (IOException ex) {
//                notifyObservers(ex);
//            }
//        }
//
//        public void close() {
//            try {
//                socket.close();
//            } catch (IOException ex) {
//                notifyObservers(ex);
//            }
//        }
//    }
//
//    static class ChatFrame extends JFrame implements Observer {
//
//        private JTextArea textArea;
//        private JTextField inputTextField;
//        private JButton sendButton;
//        private ChatAccess chatAccess;
//
//        public ChatFrame(ChatAccess chatAccess) {
//            this.chatAccess = chatAccess;
//            chatAccess.addObserver(this);
//            buildGUI();
//        }
//
//        private void buildGUI() {
//            textArea = new JTextArea(20, 50);
//            textArea.setEditable(false);
//            textArea.setLineWrap(true);
//            add(new JScrollPane(textArea), BorderLayout.CENTER);
//
//            Box box = Box.createHorizontalBox();
//            add(box, BorderLayout.SOUTH);
//            inputTextField = new JTextField();
//            sendButton = new JButton("Send");
//            box.add(inputTextField);
//            box.add(sendButton);
//
//            ActionListener sendListener = new ActionListener() {
//                public void actionPerformed(ActionEvent e) {
//                    String str = inputTextField.getText();
//                    if (str != null && str.trim().length() > 0)
//                        chatAccess.send(str);
//                    inputTextField.selectAll();
//                    inputTextField.requestFocus();
//                    inputTextField.setText("");
//                }
//            };
//            inputTextField.addActionListener(sendListener);
//            sendButton.addActionListener(sendListener);
//
//            this.addWindowListener(new WindowAdapter() {
//                @Override
//                public void windowClosing(WindowEvent e) {
//                    chatAccess.close();
//                }
//            });
//
//            // 수정된 부분: 한글 입력을 위한 폰트 설정
//            inputTextField.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
//        }
//
//        public void update(Observable o, Object arg) {
//            final Object finalArg = arg;
//            SwingUtilities.invokeLater(new Runnable() {
//                public void run() {
//                    textArea.append(finalArg.toString());
//                    textArea.append("\n");
//                }
//            });
//        }
//    }
//
//    public static void main(String[] args) {
//        String server = "192.168.0.108";
//        int port = 9980;
//        ChatAccess access = new ChatAccess();
//
//        JFrame frame = new ChatFrame(access);
//        frame.setTitle("MyChatApp - connected to " + server + ":" + port);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
//        frame.setVisible(true);
//
//        try {
//            access.InitSocket(server, port);
//        } catch (IOException ex) {
//            System.out.println("Cannot connect to " + server + ":" + port);
//            ex.printStackTrace();
//            System.exit(0);
//        }
//    }
//}
