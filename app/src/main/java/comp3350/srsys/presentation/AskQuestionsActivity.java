package comp3350.srsys.presentation;

import comp3350.srsys.R;
import comp3350.srsys.objects.Message;
import comp3350.srsys.business.AccessMessages;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AskQuestionsActivity extends Activity {
	protected EditText question;
	protected Button sendButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ask_questions);
		
		question = (EditText)findViewById(R.id.askQuestionTextField);
		
		sendButton = (Button)findViewById(R.id.askQuestionButton2);
		sendButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String newQuestion = question.getText().toString().trim();
				
				if (newQuestion.isEmpty())
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(AskQuestionsActivity.this);
					builder.setMessage(R.string.request_books_error_message)
						.setTitle(R.string.request_books_error_title)
						.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}else{
					Message msg = new Message("Professor", "Employee", "Question", newQuestion);
					(new AccessMessages()).addMessage(msg);
				}
				
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ask_questions, menu);
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
