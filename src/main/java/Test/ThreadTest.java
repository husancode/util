package Test;

public class ThreadTest {
	
	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			public final Object o = new Object();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("aaaaaa");
				try {
					synchronized (o) {
						o.wait(0);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("bbbbbbbbbb");
			}
		});
		t.start();
		System.out.println("dddd");
	}

}
