package Homework6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOExeption {
        ServerSocket socket = null;
        Socket clientSocket = null;
        Thread inputThread = null;
        try {
            socket = new ServerSocket(port);
            System.out.println("Server is working");
            clientSocket = socket.sccept();
            System.out.println("Client is online now");
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            inputThread = runInputThread(in);
            runOutputLoop(out);
        } catch (IOExeption e) {
            e.printStackTrace();
        } finally {
            if (inputThread != null) inputThread.interrupt();
            if (clientSocket != null) clientSocket.close();
            if (socket != null) socket.close();
        }
    }

    private void runOutputLoop(DataOutputStream out) throws IOExeption {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.next();
            out.writeUTF(message);
            if (message.equals("/end")) {
                break;
            }
        }
    }

    private Thread runInputThread(DataInputStream in) {
        Thread thread = new Thread(() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String message = in.readUTF();
                    System.out.println(("From client: " + message));
                    if (message.equals("/end")) {
                        System.exit(0);
                    }
                } catch (IOExeption e) {
                    System.out.println("Соединение завершено");
                    break;
                }
            }
        });
        thread.start();
        return thread;
    }
}
