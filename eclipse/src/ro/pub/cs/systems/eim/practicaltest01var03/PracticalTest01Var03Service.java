package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var03Service extends Service {

	private ProcessingThread processingThread = null;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		String answer = intent.getStringExtra("answer");
		
		processingThread = new ProcessingThread(this, answer);
		processingThread.start();
		
		return Service.START_REDELIVER_INTENT;
	}
	
	@Override
	public void onDestroy() {
	    processingThread.stopThread();
	}

}
