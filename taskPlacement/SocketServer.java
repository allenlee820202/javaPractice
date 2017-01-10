import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.*; 

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
            	//MyClass object2;
 
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            	//object2 = (MyClass) in.readObject();
                Class myclass;
                myclass = (Class) in.readObject();
                in.close();
            	System.out.println("Get class: " + myclass.getName());
                in = null;
                socket.close();
                Class[] params = new Class[3];
                params[0] = String.class;
                params[1] = Integer.TYPE;
                params[2] = Double.TYPE;

                Constructor constructor = myclass.getConstructor(params);
                
                Object[] argObjs = new Object[3];
                argObjs[0] = "Mary";
                argObjs[1] = new Integer(10);
                argObjs[2] = new Double(2.0);

                Object obj = constructor.newInstance(argObjs);

                System.out.println(obj);
            
                Class[] funcParam = {String.class};
                Method setNameMethod = myclass.getMethod("setName", funcParam);
                Object[] funcArgObjs = {"John"};
                setNameMethod.invoke(obj, funcArgObjs);
                System.out.println(obj);
                
                Method setMyValueMethod = myclass.getDeclaredMethod("setMyValue", null);
                setMyValueMethod.setAccessible(true);
                setMyValueMethod.invoke(obj, null);

                System.out.println(obj);

                Method getMyValueMethod = myclass.getMethod("getMyValue", null);
                Object ret = getMyValueMethod.invoke(obj);
                double retVal = (Double) ret;
                System.out.println("Return value: "+ retVal);
     
            }  catch (ClassNotFoundException e) {
            System.out.println("Cannot find class");
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                System.out.println("No such method");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (java.io.IOException e) {
                System.out.println("Socket Connection Error !");
                System.out.println("IOException :" + e.toString());
            } catch(Exception e){
            }		
 
        }
    }
 
    public static void main(String args[]) {
        (new SocketServer()).start();
    }
 
}
