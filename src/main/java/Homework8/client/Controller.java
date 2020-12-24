package Homework8.client;

    import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


    public class Controller {
        // связка с компонентами fxml
        @FXML
        TextArea textArea;

        @FXML
        TextField textField;

        @FXML
        Button btn1;

        @FXML
        HBox bottomPanel;

        @FXML
        HBox upperPanel;

        @FXML
        TextField loginField;

        @FXML
        PasswordField passwordField;

        Socket socket;

        DataInputStream in;
        DataOutputStream out;

        private boolean isAuthorized;

        final String IP_ADRESS = "localhost";
        final int PORT = 8189;

        public void setAthorized(boolean isAuthorized) {
            this.isAuthorized = isAuthorized;

            if (!isAuthorized) {
                upperPanel.setVisible(true);
                upperPanel.setManaged(true);
                bottomPanel.setVisible(false);
                bottomPanel.setManaged(false);
            } else {
                upperPanel.setVisible(false);
                upperPanel.setManaged(false);
                bottomPanel.setVisible(true);
                bottomPanel.setManaged(true);
            }
        }

        public void connect() {
            try {
                setAuthorized(false);
                socket = new Socket("localhost", 8189);
                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                Thread t = new Thread(() -> {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/authok ")) {
                                setAuthorized(true);
                                myNick = str.split("\\s")[1];
                                break;
                            }
                            textArea.appendText(str + "\n");
                        }

                            while (true) {
                                String str = in.readUTF();
                                if (str.equals("/serverClosed")) {

                                    setAthorized(false);
                                    break;
                                }
                                textArea.appendText(str + "\n");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                setAuthorized(false);
                        socket.close();
                        myNick = "";
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                });
                t.start();
            } catch (IOException e) {
                showAlert("Не удалось подключиться к серверу");
                e.printStackTrace();
            }
        }
        public void onAuthClick() {
            if (socket == null || socket.isClosed()) {
                start();
            }
            try {
                out.writeUTF("/auth " + loginField.getText() + " " + passField.getText());
                loginField.setText("");
                passField.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void sendMsg() {
            try {
                out.writeUTF(textField.getText());
                textField.clear();
                textField.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void tryToAuth(ActionEvent actionEvent) {
            if (socket == null || socket.isClosed()) {
                connect();
            }
            try {
                out.writeUTF("/auth " + loginField.getText() + " " + passwordField.getText());
                loginField.clear();
                passwordField.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
