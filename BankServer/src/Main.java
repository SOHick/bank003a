
import ru.smak.net.Server;


public class Main {
    public static void main(String[] args) {
        Server srv = new Server(5003);
        srv.start();
    }
}
