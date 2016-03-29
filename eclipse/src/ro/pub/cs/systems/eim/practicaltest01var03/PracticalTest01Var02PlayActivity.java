package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var02PlayActivity extends Activity {
	private Button checkButton = null;
	private Button backButton = null;
	private EditText editTextRiddle2 = null;
	private EditText editTextAnswer2 = null;
	
	private String theRiddle = null;
	private String theAnswer = null;
	
	private static int SERVICE_STOPPED = 0;
	private static int SERVICE_STARTED = 1;
	int serviceStatus = SERVICE_STOPPED;
	
	private CheckButtonClick checkButtonClick = new CheckButtonClick();
	private class CheckButtonClick implements View.OnClickListener {

		private static final int RESULT_NOT_OK = 11;

		@Override
		public void onClick(View v) {
			String answer = editTextAnswer2.getText().toString();
			
			if (theAnswer.contentEquals(answer)) {
				Toast.makeText(getApplicationContext(), "The answer is correct", Toast.LENGTH_LONG).show();
				setResult(RESULT_OK, null);
			} else {
				setResult(RESULT_NOT_OK, null);
				Toast.makeText(getApplicationContext(), "The answer is not correct", Toast.LENGTH_LONG).show();
			}
		}
	}
	
	private BackButtonClick backButtonClick = new BackButtonClick();
	private class BackButtonClick implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			setResult(RESULT_CANCELED, null);
			finish();
		}
	}
	
	private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
	private class MessageBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(getApplicationContext(), "Hint: " + 
							intent.getStringExtra("message"), Toast.LENGTH_LONG).show();
		}
	}
	
	private IntentFilter intentFilter = new IntentFilter();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var02_play);
		
		editTextRiddle2 = (EditText) findViewById(R.id.editTextRiddle2);
        editTextAnswer2 = (EditText) findViewById(R.id.editTextAnswer2);
		
		Intent intent = getIntent();
	    
	    if (intent != null && intent.getExtras().containsKey("riddle") &&
	    	intent.getExtras().containsKey("answer")) {
	      theRiddle = intent.getStringExtra("riddle");
	      theAnswer = intent.getStringExtra("answer");
	      
	      editTextRiddle2.setText(theRiddle);
	    }
        
        backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setOnClickListener(backButtonClick);
        checkButton = (Button) findViewById(R.id.buttonCheck);
        checkButton.setOnClickListener(checkButtonClick);
        
        if (editTextAnswer2.getText().toString().length() != 0) {
			Intent intentS = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
			intentS.putExtra("answer", theAnswer);
			
			getApplicationContext().startService(intent);
			serviceStatus = SERVICE_STARTED;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var02_play, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
    protected void onDestroy() {
      Intent intent = new Intent(this, PracticalTest01Var03Service.class);
      stopService(intent);
      super.onDestroy();
    }
	
	@Override
    protected void onResume() {
      super.onResume();
      registerReceiver(messageBroadcastReceiver, intentFilter);
    }
   
    @Override
    protected void onPause() {
      unregisterReceiver(messageBroadcastReceiver);
      super.onPause();
    }
}
