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

public class CreateBookListActivity extends Activity {
	
	protected EditText bookName;
	protected EditText author;
	
	protected Button addButton;
	protected Button updateButton;
	protected Button deleteButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_book_list);
		
		bookName = (EditText)findViewById(R.id.cbUsingNameTextField);
		author = (EditText)findViewById(R.id.cbUsingAuthorTextField);
		
		addButton = (Button)findViewById(R.id.addBookButton);
		addButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String newBookName = bookName.getText().toString().trim();
				String newAuthor = author.getText().toString().trim();
				
				if (newBookName.isEmpty() || newAuthor.isEmpty())
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(CreateBookListActivity.this);
					builder.setMessage(R.string.request_books_error_message)
						.setTitle(R.string.request_books_error_title)
						.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}	
			}
		});
		
		updateButton = (Button)findViewById(R.id.updateButton);
		updateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String newBookName = bookName.getText().toString().trim();
				String newAuthor = author.getText().toString().trim();
				
				if (newBookName.isEmpty() || newAuthor.isEmpty())
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(CreateBookListActivity.this);
					builder.setMessage(R.string.request_books_error_message)
						.setTitle(R.string.request_books_error_title)
						.setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}	
			}
		});
		
		deleteButton = (Button)findViewById(R.id.deleteButton);
		deleteButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String newBookName = bookName.getText().toString().trim();
				String newAuthor = author.getText().toString().trim();
				
				if (newBookName.isEmpty() || newAuthor.isEmpty())
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(CreateBookListActivity.this);
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
		getMenuInflater().inflate(R.menu.create_book_list, menu);
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
