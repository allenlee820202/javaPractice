import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
 import java.lang.reflect.*; 

public class SocketClient {
    private String address = "192.168.0.103";
    private int port = 8765;
 
    public SocketClient() {
 
        Socket client = new Socket();
        InetSocketAddress isa = new InetSocketAddress(this.address, this.port);
        try {
            client.connect(isa, 10000);
            Class myclass = Class.forName("MyClass");
            System.out.println("myclass: " + myclass.getName());
            //MyClass object1 = new MyClass("Hello", 32139604, 2.7e10);
            //System.out.println("Object 1: " + object1);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(myclass);
            out.flush();
            out.close();
            out = null;
            client.close();
            client = null;
 
        } catch (java.io.IOException e) {
            System.out.println("Socket Connection Error !");
            System.out.println("IOException :" + e.toString());
        } catch (Exception e){
			System.out.println(e.toString());
		}
    }
 
    public static void main(String args[]) {
        new SocketClient();
    }
}
