 public class SomeThread extends Thread {
    private boolean isTerminated = false;
	/*stop 為deprecated的方法，如果thread正在存取critical section可能缺乏安全性，為了使thread能安全結束，應使用Two Phase Termination方法*/
    public void terminate() {	//自行實作thread的terminate方式，使得thread能夠安全的結束
        isTerminated = true;
        interrupt();	// 程式可能在sleep or wait的狀態，只把isTerminated設為true可能無法馬上終止，因此呼叫interrupt()來改變thread的狀態
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    private void doWorkBeforeShutdown() {
        // .... do some work before shutdown
		System.out.println("do something before shutdown");
    }

    public void run() {
        try {
            while(!isTerminated) {
                // do work ...
				System.out.println("thread is executing...");
				Thread.sleep(1000);
            }
        }
        catch(InterruptedException e) {
			System.out.println("Thread is interrupted!");
        }
        finally {
            doWorkBeforeShutdown();
        }
    }
}
