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

public class UpdateInventoryActivity extends Activity {
	protected EditText bookName;
	protected EditText author;
	protected EditText course;
	protected EditText category;
	protected EditText quantity;
	protected Button updateButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_inventory);
		
		bookName = (EditText)findViewById(R.id.emBookNameTextField);
		author = (EditText)findViewById(R.id.emAuthorTextField);
		course = (EditText)findViewById(R.id.emCourseTextField);
		category = (EditText)findViewById(R.id.emCategoryTextField);
		quantity = (EditText)findViewById(R.id.emQuantityTextField);
		
		updateButton = (Button)findViewById(R.id.emUpdateButton);
		updateButton.setOnClickListener(new View.OnClickListener() {
			
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
					AlertDialog.Builder builder = new AlertDialog.Builder(UpdateInventoryActivity.this);
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
		getMenuInflater().inflate(R.menu.update_inventory, menu);
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
