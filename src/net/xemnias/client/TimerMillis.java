package net.xemnias.client;

public class TimerMillis
{
	private Thread thread;
	private long time;
	private Action action;
	
	public TimerMillis(long t, Action a)
	{
		thread = new Thread(new millisRun(t, a));
		time = t;
		action = a;
	}
	
	public void start()
	{
		thread = new Thread(new millisRun(time, action));
		thread.start();
	}
	
	class millisRun implements Runnable
	{
		
		private long time;
		private Action action;

		public millisRun(long t, Action a)
		{
			time = t;
			action = a;
			
		}
		
		public void run() 
		{
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			action.actionPerformed();
		}
		
	}
}
