import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
 
public class SocketClient {
    private String address = "140.112.42.76";
    private int port = 8765;
 
    public SocketClient() {
 
        Socket client = new Socket();
        InetSocketAddress isa = new InetSocketAddress(this.address, this.port);
        try {
            client.connect(isa, 10000);
            OutputStream out = client.getOutputStream();
            
			out.write("hello".getBytes());
            out.flush();
            out.close();
            out = null;
            client.close();
            client = null;
 
        } catch (java.io.IOException e) {
            System.out.println("Socket Connection Error !");
            System.out.println("IOException :" + e.toString());
        }
    }
 
    public static void main(String args[]) {
        new SocketClient();
    }
}
