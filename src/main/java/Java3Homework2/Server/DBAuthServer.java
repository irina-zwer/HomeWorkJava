package Java3Homework2.Server;

public class DBAuthServer implements AuthService {
    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return SQHandler.getNicknameByLoginAndPassword(login, password);
    }
    @Override
    public boolean registration(String login, String password, String nickname) {
        return SQHandler.registration(login, password, nickname);
    }
    @Override
    public boolean changeNick(String oldNickname, String newNickname) {
        return SQHandler.changeNick(oldNickname, newNickname);
    }
}
