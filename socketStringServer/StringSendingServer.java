import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.OutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
 
public class StringSendingServer extends java.lang.Thread {
 
    private boolean OutServer = false;
    private ServerSocket server;
    private final int ServerPort = 8765;
 
    public StringSendingServer() {
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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (!OutServer) {
            socket = null;
            try {
                synchronized (server) {
                    socket = server.accept();
                }
                System.out.println("Get Connection : InetAddress = "
                        + socket.getInetAddress());
                // TimeOut Time
                //socket.setSoTimeout(15000);
				java.io.InputStream in = socket.getInputStream();
				OutputStream os = socket.getOutputStream();
				while(true){
					String input = br.readLine();
					System.out.println(input);
					os.write(input.getBytes());
					os.flush();
				}
                //socket.close();
 
            } catch (java.io.IOException e) {
                System.out.println("Socket Connection Error !");
                System.out.println("IOException :" + e.toString());
            }
			catch(Exception e){
			}
 
        }
    }
 
    public static void main(String args[]) {
        (new StringSendingServer()).start();
    }
 
}
