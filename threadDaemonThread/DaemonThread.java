public class DaemonThread { 
    public static void main(String[] args) {

        Thread thread = new Thread(
            new Runnable() {
                public void run() { 
                    while(true) { 
                        System.out.print("T"); 
                    } 
                }        
            }); 
        thread.setDaemon(true); // a daemon thread terminates automatically when the main function terminates.
		//thread.setDaemon(false); // a non-daemon thread doesn't terminate even when the main function finishes all its operations.
        thread.start(); 
		//System.exit()	// this function can terminate all the threads including non-daemon threads.
    }
}
