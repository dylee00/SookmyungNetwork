package org.example.chatting;

import org.example.Main;
import org.example.entity.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {
    private String clientName = null;
    private BufferedReader is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
    private final ClientThread[] threads;
    private int maxClientsCount;

    private Main main;




    public ClientThread(Socket clientSocket, ClientThread[] threads, Main main) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        this.main = main;
        maxClientsCount = threads.length;
    }

    public void run() {
        int maxClientsCount = this.maxClientsCount;
        ClientThread[] threads = this.threads;

        try {
            /* Create input and output streams for this client. */
            //is = new DataInputStream(clientSocket.getInputStream());
            is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
            os = new PrintStream(clientSocket.getOutputStream());
            String name;

            /* Welcome the new the client. */
            // 사용자 이름을 불러오는 코드 추가
            User newUser = new User("2110072", "이다연"); // User 객체 생성
            String userName = newUser.getUserName();
            name = userName;
            os.println(name + "님이 " + "강의실 분실물 채팅에 입장하셨습니다.\n이 채팅방을 나가려면 새로운 줄에 '/나가기' 를 입력하세요.");
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] == this) {
                        clientName = "@" + name;
                        break;
                    }
                }
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] != this) {
                        threads[i].os.println("*** " + name
                                + "님이 강의실 채팅방에 입장했습니다!!! ***");
                    }
                }
            }

            /* Start the conversation. */
            while (true) {
                String line = is.readLine();
                if (line.startsWith("/나가기")) { //한글로 변경
                    break;
                }

                /* If the message is private sent it to the given client. */
                if (line.startsWith("@")) {
                    String[] words = line.split("\\s", 2);
                    if (words.length > 1 && words[1] != null) {
                        words[1] = words[1].trim();
                        if (!words[1].isEmpty()) {
                            synchronized (this) {
                                for (int i = 0; i < maxClientsCount; i++) {
                                    if (threads[i] != null && threads[i] != this
                                            && threads[i].clientName != null
                                            && threads[i].clientName.equals(words[0])) {
                                        threads[i].os.println("<" + name + "> " + words[1]);
                                        /* Echo this message to let the client know the private message was sent. */
                                        this.os.println(">" + name + "> " + words[1]);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    /* The message is public, broadcast it to all other clients. */
                    synchronized (this) {
                        for (int i = 0; i < maxClientsCount; i++) {
                            if (threads[i] != null && threads[i].clientName != null) {
                                threads[i].os.println("<" + name + "> " + line);
                            }
                        }
                    } // end of synchronized
                }
            }
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] != null && threads[i] != this
                            && threads[i].clientName != null) {
                        threads[i].os.println("*** " + name
                                + "님이 강의실 채팅방을 나갔습니다!!! ***");
                    }
                }
            }// end of synchronized
            os.println("*** 안녕히가세요 " + name + " 님***");

            /* Clean up. Set the current thread variable to null so that a new client could be accepted by the server.*/
            synchronized (this) {
                for (int i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == this) {
                        threads[i] = null;
                    }
                }
            }// end of synchronized

            /* Close the output stream, close the input stream, close the socket. */
            is.close();
            os.close();
            clientSocket.close();
        } catch (IOException e) {
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}