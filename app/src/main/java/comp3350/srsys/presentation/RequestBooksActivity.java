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

public class RequestBooksActivity extends Activity {
	protected EditText bookName;
	protected EditText author;
	protected EditText course;
	protected EditText category;
	protected EditText quantity;
	protected Button submitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request_books);
		
		bookName = (EditText)findViewById(R.id.bookNameTextField);
		author = (EditText)findViewById(R.id.authorTextField);
		course = (EditText)findViewById(R.id.courseTextField);
		category = (EditText)findViewById(R.id.categoryTextField);
		quantity = (EditText)findViewById(R.id.quantityTextField);
		
		submitButton = (Button)findViewById(R.id.submitRequestButton);
		submitButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String newBookName = bookName.getText().toString().trim();
				String newAuthor = author.getText().toString().trim();
				String newCourse = course.getText().toString().trim();
				String newCategory = category.getText().toString().trim();
				String newQuantity = quantity.getText().toString().trim();
				
				if (newBookName.isEmpty() || newCourse.isEmpty() || newAuthor.isEmpty()
						|| newCategory.isEmpty() || newQuantity.isEmpty())
				{
					AlertDialog.Builder builder = new AlertDialog.Builder(RequestBooksActivity.this);
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
		getMenuInflater().inflate(R.menu.request_books, menu);
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
