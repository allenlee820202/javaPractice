import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
 
public class SocketServer extends java.lang.Thread {
 
    private boolean OutServer = false;
    private ServerSocket server;
    private final int ServerPort = 8765;
 
    public SocketServer() {
        try {
            server = new ServerSocket(ServerPort);
 
        } catch (java.io.IOException e) {
            System.out.println("Socket Start Error !");
            System.out.println("IOException :" + e.toString());
        }
    }
 
    public void run() {
        Socket socket;
 
        System.out.println("Server Started!");
        while (!OutServer) {
            socket = null;
            try {
                synchronized (server) {
                    socket = server.accept();
                }
                System.out.println("Get Connection : InetAddress = "
                        + socket.getInetAddress());
                // TimeOut Time
                socket.setSoTimeout(15000);
            	MyClass object2;
 
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            	object2 = (MyClass) in.readObject();
                in.close();
            	System.out.println("Object 2: " + object2);
                in = null;
                socket.close();
 
            } catch (java.io.IOException e) {
                System.out.println("Socket Connection Error !");
                System.out.println("IOException :" + e.toString());
            }
			catch(Exception e){
			}
 
        }
    }
 
    public static void main(String args[]) {
        (new SocketServer()).start();
    }
 
}
