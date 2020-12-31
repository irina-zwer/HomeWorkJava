package Java3Homework2.Server;

public interface AuthService {
    void start();
    String getNicknameByLoginAndPassword(String login, String password);
    void stop();

    boolean registration(String login, String password, String nickname);
    boolean changeNick(String oldNickname, String newNickname);
}
