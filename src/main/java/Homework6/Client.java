package Homework6;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOExeption {
        new Client().start("localhost", 8189);
    }

    public void start(String host, int port) throws IOEception {
        Socket socket = null;
        Thread inputThread = null;
        try {
            socket = new Socket(host, port);
            System.out.println("Клиент запущен");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = DataOutputStream(socket.getOutputStream());
            inputThread = runInputThread(in);
            runOutputLoop(out);
        } catch (IOEception e) {
            e.printStackTrace();
        } finally {
            if (inputThread != null) inputThread.interrupt();
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
                    System.out.println("From Server: " + message);
                    if (message.equals("/end")) {
                        System.exit(0);
                    }
                } catch (IOExeption e) {
                    System.out.println(("Соединение было закрыто"));
                    break;
                }
            }
        });
        thread.start();
        return thread;
    }
}
