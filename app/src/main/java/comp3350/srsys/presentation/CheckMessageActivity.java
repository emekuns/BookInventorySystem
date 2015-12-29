package comp3350.srsys.presentation;

import comp3350.srsys.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CheckMessageActivity extends Activity {
	
	protected EditText response;
	protected Button submitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_message);
		
		response = (EditText)findViewById(R.id.respondMessageTextField);
		
		submitButton = (Button)findViewById(R.id.sendMessageButton);
		submitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String newResponse = response.getText().toString().trim();
				
				if (newResponse.isEmpty())
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(CheckMessageActivity.this);
					builder.setMessage(R.string.request_books_error_message)
						.setTitle(R.string.request_books_error_title)
						.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}	
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.check_message, menu);
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
}
