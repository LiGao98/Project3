import java.io.IOException;

public interface INetworkAdapter {
    public String send(String msg, String host, int port) throws Exception;

    public MessageModel send(MessageModel msg, String host, int port) throws Exception;

    public String exchange(String msg, String host, int port) throws Exception;

    public MessageModel exchange(MessageModel msg, String host, int port) throws Exception;

}
