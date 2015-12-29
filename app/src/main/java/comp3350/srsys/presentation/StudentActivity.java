package comp3350.srsys.presentation;

import comp3350.srsys.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class StudentActivity extends Activity {
	protected Button browseInventoryButton;
	protected Button previewBooksButton;
	protected Button viewCourseListButton;
	protected Button askQuestionButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student);
		
		browseInventoryButton = (Button)findViewById(R.id.browseInventoryButton);
		browseInventoryButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(StudentActivity.this, BrowseInventoryActivity.class);
				startActivity(intent);
			}
		});
		
		previewBooksButton = (Button)findViewById(R.id.previewBooksButton);
		previewBooksButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(StudentActivity.this, PreviewBooksActivity.class);
				startActivity(intent);
			}
		});
		
		
		viewCourseListButton = (Button)findViewById(R.id.viewCourseListButton);
		viewCourseListButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(StudentActivity.this, CourseListActivity.class);
				startActivity(intent);
			}
		});
		
		askQuestionButton = (Button)findViewById(R.id.askQuestionButton);
		askQuestionButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(StudentActivity.this, AskQuestionsActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.student, menu);
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
