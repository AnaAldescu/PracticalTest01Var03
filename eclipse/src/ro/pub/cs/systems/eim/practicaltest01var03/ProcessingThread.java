package ro.pub.cs.systems.eim.practicaltest01var03;

import java.util.Random;

import android.content.Context;
import android.content.Intent;

public class ProcessingThread extends Thread {
	private static final String ACTION_TYPE_1 = "ro.pub.cs.systems.eim.practicaltest01var03.actionType1";
	
	private Context context = null;
	private boolean isRunning = true;
	 
	private Random random = new Random();
	 
	private String theAnswer;
	
	public ProcessingThread(Context context, String answer) {
	    this.context = context;
	    theAnswer = answer;
	}
	
	@Override
	public void run() {
		
		while (isRunning) {
			sleep();
			sendMessage();
	    }
	  }

	private void sleep() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void sendMessage() {
		Intent intent = new Intent();
		
		int r = random.nextInt(theAnswer.length() - 1);
		String hint = "";
		
		for (int i = 0; i < r; i++) {
			hint += "*";
		}
		
		hint += theAnswer.charAt(r);
		
		for (int i = r + 1; i < theAnswer.length(); i++) {
			hint += "*";
		}
		
	    intent.setAction(ACTION_TYPE_1);
	    intent.putExtra("message", hint);
	    context.sendBroadcast(intent);
	}
	
	public void stopThread() {
	    isRunning = false;
	}
}
