package comp3350.srsys.presentation;

import comp3350.srsys.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class EmployeeActivity extends Activity {
	
	protected Button updateInventoryButton;
	protected Button orderButton;
	protected Button checkOrderStatusButton;
	protected Button checkMessageButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee);
		
		
		updateInventoryButton = (Button)findViewById(R.id.updateInventoryButton);
		updateInventoryButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(EmployeeActivity.this, UpdateInventoryActivity.class);
				startActivity(intent);
			}
		});
		
		
		orderButton = (Button)findViewById(R.id.orderFromPublisherButton);
		orderButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(EmployeeActivity.this, OrderActivity.class);
				startActivity(intent);
			}
		});
		
		
		
		
		checkOrderStatusButton = (Button)findViewById(R.id.checkOrderStatusButton);
		checkOrderStatusButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(EmployeeActivity.this, CheckOrderStatusActivity.class);
				startActivity(intent);
			}
		});
		
		checkMessageButton = (Button)findViewById(R.id.checkMessageButton);
		checkMessageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(EmployeeActivity.this, CheckMessageActivity.class);
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.employee, menu);
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
