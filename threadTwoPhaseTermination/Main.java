public class Main{
	public static void main(String args[]){
		try{
			SomeThread t = new SomeThread();
			t.start();
			System.out.println("Thread t terminated? "+t.isTerminated());
			Thread.sleep(5000);
			t.terminate();
			System.out.println("Thread t terminated? "+t.isTerminated());
			t.join();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("main: END");
	}
}
