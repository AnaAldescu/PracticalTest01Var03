package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PracticalTest01Var03MainActivity extends Activity {
	private static final int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	
	private Button playButton = null;
	EditText editTextRiddle = null;
	EditText editTextAnswer = null;
	
	private PlayButtonClick playButtonClick = new PlayButtonClick();
	private class PlayButtonClick implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			String riddle = editTextRiddle.getText().toString();
			String answer = editTextAnswer.getText().toString();
			
			if (riddle.length() != 0 && answer.length() != 0) {
				Intent intent = new Intent(getApplicationContext(),
				 			PracticalTest01Var02PlayActivity.class);
	
				intent.putExtra("riddle", riddle);
				intent.putExtra("answer", answer);
				
				startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
			}
		}
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_main);
        
        playButton = (Button) findViewById(R.id.buttonPlay);
        editTextRiddle = (EditText) findViewById(R.id.editTextRiddle);
        editTextAnswer = (EditText) findViewById(R.id.editTextAnswer);
        
        playButton.setOnClickListener(playButtonClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.practical_test01_var03_main, menu);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
      if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
        if (resultCode == RESULT_OK) {
        	//Toast.makeText(this, "The answer is correct", Toast.LENGTH_LONG).show();
        } else if (resultCode == 11){
    	  //Toast.makeText(this, "The answer is not correct", Toast.LENGTH_LONG).show();
        }
      }
    }
    
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
    	savedInstanceState.putString("riddle", editTextRiddle.getText().toString());
    	savedInstanceState.putString("answer", editTextAnswer.getText().toString());
    }
    
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        
    	if (savedInstanceState.containsKey("riddle")) {
          editTextRiddle.setText(savedInstanceState.getString("riddle"));
        } else {
        	editTextRiddle.setText("");
        }
        if (savedInstanceState.containsKey("answer")) {
        	editTextAnswer.setText(savedInstanceState.getString("answer"));
        } else {
        	editTextAnswer.setText("");
        }
     }
}
