public class Main {
    public static void main(String args[])
    {
        if(args.length==0)
            System.out.println("Start \"server\" or \"client\" ?");
        if(args[0].equals("server"))
            (new SocketServer()).start();
        else
            new SocketClient();
    }
}
