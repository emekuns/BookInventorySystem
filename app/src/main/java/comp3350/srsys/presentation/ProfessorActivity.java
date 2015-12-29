package comp3350.srsys.presentation;

import comp3350.srsys.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ProfessorActivity extends Activity {
	protected Button requestBooksButton;
	protected Button checkStockButton;
	protected Button checkStatusButton;
	protected Button createBookListButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_professor);
		
		requestBooksButton = (Button)findViewById(R.id.requestBooksButton);
		requestBooksButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ProfessorActivity.this, RequestBooksActivity.class);
				startActivity(intent);
			}
		});
		
		checkStockButton = (Button)findViewById(R.id.checkStockButton);
		checkStockButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ProfessorActivity.this, CheckStockActivity.class);
				startActivity(intent);
			}
		});
		
		checkStatusButton = (Button)findViewById(R.id.checkStatusButton);
		checkStatusButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ProfessorActivity.this, CheckStatusActivity.class);
				startActivity(intent);
			}
		});
		
		createBookListButton = (Button)findViewById(R.id.createBookListButton);
		createBookListButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ProfessorActivity.this, CreateBookListActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.professor, menu);
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
